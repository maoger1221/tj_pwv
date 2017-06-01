package tj.pwv.utils.ssa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Jama.Matrix;
import Jama.SingularValueDecomposition;

public class SSAUtils {
	
	/**
	 * 第一步，计算轨迹矩阵
	 * @param timeSeries
	 * @param L
	 * @param N
	 * @param K
	 * @return
	 */
	private static Matrix getXMatrix(double[] timeSeries, int L, int N, int K) {
		Matrix x = new Matrix(timeSeries, N);//一维列向量
		Matrix X = new Matrix(L, K);
		for (int i = 0; i < L; i++) {
			for (int j = 0; j < K; j++) {
				X.set(i, j, x.get(i+j, 0));
			}
		}
		return X;
	}
	/**
	 * //第二步，奇异值分解
	 * @param X
	 * @return
	 */
	private static Matrix[] getSVD(Matrix X){
		//!!!!jama的svd只能处理行数大于列数的矩阵
		SingularValueDecomposition svd = X.transpose().svd();
		//Matrix U = svd.getU();
		//Matrix V = svd.getV();
		Matrix S = svd.getS();
		//Matrix qq = U.times(S).times(V.transpose());
		//Matrix qqq = V.times(S.transpose()).times(U.transpose());
		Matrix U = svd.getV();//!!
		Matrix V = svd.getU();//!!
		//Matrix qq = U.times(S).times(V.transpose());
		//获取主对角线元素
		Matrix S_Dialog = getDialog(S);
		//将S从大到小排序，返回值为列向量
		Matrix[] sortUVS = sortUVS(S_Dialog);
		Matrix sigma2 = sortUVS[0];
		Matrix t = sortUVS[1];
		Matrix lam = sigma2.arrayTimes(sigma2);
		//矩阵X经svd分解后所得，S的平方是XX'和X'X的特征值，其对应的特征向量分别是U和V的列向量
		return new Matrix[]{U,V,S_Dialog,sigma2,t,lam};
	}
	
	/**
	 * //第三步，重构
	 * @param L
	 * @param N
	 * @param K
	 * @param U
	 * @param V
	 * @param sigma2
	 * @param t
	 * @return
	 */
	private static Matrix reConstruct(int L, int N, int K, Matrix U, Matrix V, Matrix sigma2, Matrix t) {
		Matrix y = new Matrix(N,sigma2.getRowDimension());//全为0的矩阵
		int L2 = Math.min(L, K);
		int K2 = Math.max(L, K);
		for (int i = 0; i < sigma2.getRowDimension(); i++) {
			Matrix U1 = U.getMatrix(0, U.getRowDimension()-1,(int)t.get(i, 0),(int)t.get(i, 0));
			Matrix V1 = V.getMatrix(0, V.getRowDimension()-1,(int)t.get(i, 0),(int)t.get(i, 0)).transpose();
			Matrix Z = U1.times(V1).times(sigma2.get(i, 0));
			for (int k = 1; k <= N; k++) {
				if(k >= 1 && k <= L2){
					for (int q = 1; q <= k; q++) {
						y.set(k-1, i, y.get(k-1, i) + Z.get(q-1, k-q));
					}
					y.set(k-1, i, y.get(k-1, i)/k);
				}else if(k >= L2 && k <= K2){
					for (int q = 1; q <= L2; q++) {
						y.set(k-1, i, y.get(k-1, i) + Z.get(q-1, k-q));
					}
					y.set(k-1, i, y.get(k-1, i)/L2);
				}else if(k >= K2 && k <= N){
					for (int q = k-K2+1; q <= N-K2+1; q++) {
						y.set(k-1, i, y.get(k-1, i) + Z.get(q-1, k-q));
					}
					y.set(k-1, i, y.get(k-1, i)/(N-k+1));
				}
			}
		}
		return y;
	}
	
	
	/**
	 * //获取主对角线元素
	 * @param s
	 * @return
	 */
	private static Matrix getDialog(Matrix s) {
		double[] d = new double[s.getRowDimension()];
		for (int i = 0; i < s.getRowDimension(); i++) {
			d[i] = s.get(i, i);
		}
		return new Matrix(d,d.length);
	}

	/**
	 * 将S从大到小排序
	 * @param S
	 * @return 返回值为列向量
	 */
	private static Matrix[] sortUVS(Matrix S) {
		double[] a = S.transpose().getArray()[0];
		//t记录排序后元素，原来的位置；S.transpose().getArray()[0]转成行向量，并取第一行
		double[] t = QuickSort.quickSort(a, S.getRowDimension());
		Matrix SS = new Matrix(a,a.length);
		Matrix TT = new Matrix(t,t.length);
		return new Matrix[]{SS,TT};
	}
	
	/**
	 * 求两个列向量的协方差
	 * @param a
	 * @param b
	 * @return
	 */
	private static double getCov(Matrix a,Matrix b){
		double suma = 0;
		double sumb = 0;
		for (int i = 0; i < a.getRowDimension(); i++) {
			suma += a.get(i, 0);
			sumb += b.get(i, 0);
		}
		double Ea = suma / a.getRowDimension();//均值
		double Eb = sumb / b.getRowDimension();
		
		double sumC = 0;
		for (int i = 0; i < a.getRowDimension(); i++) {
			sumC += (a.get(i, 0)-suma) * (b.get(i, 0)-sumb);
		}
		double Sa = sumC / (a.getRowDimension()-1);//协方差
		return sumC;
	}
	
	/**
	 * 获取最小值的位置
	 * @param c2
	 * @return
	 */
	private static int getMin(List<Double> c2) {
		int pos = 0;
		for (int i = 0; i < c2.size(); i++) {
			if(c2.get(i) < c2.get(pos))
				pos = i;
		}
		return pos;
	}
	
	/**
	 * 奇异谱分析滤波，与matlab计算结果一致
	 * @param timeSeries
	 * @param L
	 * @return
	 */
	public static SSA ssaFilter(double[] timeSeries, int L) {
		int N = timeSeries.length;
		// 第一步，计算轨迹矩阵
		int K = N - L + 1;
		Matrix X = getXMatrix(timeSeries, L, N, K);
		
		//第二步，奇异值分解
		Matrix[] mySVD = getSVD(X);
		Matrix U = mySVD[0];
		Matrix V = mySVD[1];
		Matrix S_Dialog = mySVD[2];
		Matrix sigma2 = mySVD[3];
		Matrix t = mySVD[4];
		Matrix lam = mySVD[5];
		
		
		//第三步，分组
		List<Double> c2 = new ArrayList<>();
		//循环求解最佳p值
		int sig2num = sigma2.getRowDimension();
		for (int p = 0; p < sig2num-1; p++) {
			Matrix si22 = sigma2.getMatrix(p+1, sig2num-1, 0, 0);
			double[][] array = si22.getArray();
			double[] array1 = new double[si22.getRowDimension()+1];
			for (int i = 0; i < array1.length-1; i++) {
				array1[i] = array[i][0];
			}
			array1[array1.length-1] = array[si22.getRowDimension()-1][0];
			Matrix si2 = new Matrix(array1,array1.length);
			Matrix si3 = sigma2.getMatrix(p, sig2num-1, 0, 0);
			double c = getCov(si2,si3)/Math.sqrt(getCov(si2,si2)*getCov(si3,si3));
			c2.add(c);
		}
		int p = getMin(c2);//返回最小值的位置
		
		Matrix Z = new Matrix(L,K);
		for (int i = 0; i < p; i++) {
			Matrix U1 = U.getMatrix(0, U.getRowDimension()-1,(int)t.get(i, 0),(int)t.get(i, 0));
			Matrix V1 = V.getMatrix(0, V.getRowDimension()-1,(int)t.get(i, 0),(int)t.get(i, 0)).transpose();
			Z.plusEquals(U1.times(V1).times(sigma2.get(i, 0)));
		}
		//第四步，重构
		Matrix y = new Matrix(N,1);//全为0的矩阵
		int L2 = Math.min(L, K);
		int K2 = Math.max(L, K);
		
		for (int k = 1; k <= N; k++) {
			if(k >= 1 && k <= L2){
				for (int q = 1; q <= k; q++) {
					y.set(k-1,0, y.get(k-1, 0) + Z.get(q-1, k-q));
				}
				y.set(k-1, 0, y.get(k-1, 0)/k);
			}else if(k >= L2 && k <= K2){
				for (int q = 1; q <= L2; q++) {
					y.set(k-1, 0, y.get(k-1, 0) + Z.get(q-1, k-q));
				}
				y.set(k-1, 0, y.get(k-1, 0)/L2);
			}else if(k >= K2 && k <= N){
				for (int q = k-K2+1; q <= N-K2+1; q++) {
					y.set(k-1, 0, y.get(k-1, 0) + Z.get(q-1, k-q));
				}
				y.set(k-1, 0, y.get(k-1, 0)/(N-k+1));
			}
		}
		Matrix r = new Matrix(timeSeries, N).minus(y);//残差
		SSA ssa = new SSA();
		ssa.setP_filter(p);//滤波裁取长度
		ssa.setY(y);//滤波后信号
		ssa.setR(r);//残差
		ssa.setS_Dialog(S_Dialog);//奇异值
		return ssa;
	}
	
	
	/**
	 * 趋势项提取，与matlab计算结果一致
	 * @param timeSeries 一维时间序列
	 * @param L 窗口长度，一般取三分之一
	 * @return 
	 */
	public static SSA ssaTrends(double[] timeSeries, int L) {
		int N = timeSeries.length;
		// 第一步，计算轨迹矩阵
		int K = N - L + 1;
		Matrix X = getXMatrix(timeSeries, L, N, K);
		//第二步，奇异值分解
		Matrix[] mySVD = getSVD(X);
		Matrix U = mySVD[0];
		Matrix V = mySVD[1];
		Matrix S_Dialog = mySVD[2];
		Matrix sigma2 = mySVD[3];
		Matrix t = mySVD[4];
		Matrix lam = mySVD[5];
		//第三步，重构
		Matrix y = reConstruct(L, N, K, U, V, sigma2, t);
		//第四步，趋势项提取
		//保存趋势项的序号
		List<Integer> trends = new ArrayList<>();
		for (int m = 0; m < sigma2.getRowDimension(); m++) {
			double Kr = 0;
			for (int i = 0; i < N-1; i++) {
				for (int j = i+1; j < N; j++) {
					if(Math.signum(y.get(j, m)-y.get(i, m)) == -1.0){
						Kr = (int) (Kr + Math.signum(y.get(j, m)-y.get(i, m)) + 1);
					}else{
						Kr = (int) (Kr + Math.signum(y.get(j, m)-y.get(i, m)));
					}
				}
			}
			double tt = -1+4*Kr/(N*(N-1));
			double ss = Math.sqrt(2*(2.0*N+5)/(9*N*(N-1)));//转化为double类型，注意保留位数
			if (tt > 1.96 * ss || tt < -1.96 * ss) {
				trends.add(m);
			}
		}
		double slam = 0;
		for (int i = 0; i < lam.getRowDimension(); i++) {
			slam += lam.get(i, 0);
		}
		List<Double> per = new ArrayList<>();
		//z为总的趋势信号
		Matrix z = new Matrix(N,1);
		for (int i = 0; i < trends.size(); i++) {
			//计算趋势项的贡献率
			per.add(lam.get(trends.get(i), 0)/slam);
			z.plusEquals(y.getMatrix(0, y.getRowDimension()-1,trends.get(i),trends.get(i)));
		}
		
		SSA ssa = new SSA();
		ssa.setPer(per);
		ssa.setTrends(trends);
		ssa.setY(y);
		ssa.setZ(z);
		ssa.setS_Dialog(S_Dialog);
		return ssa;
		
	}
	
	
	
	/**
	 * 周期项提取，与matlab计算结果一致
	 * @param timeSeries 一维时间序列
	 * @param L 窗口长度，一般取三分之一
	 * @return
	 */
	public static SSA ssaPeriod(double[] timeSeries, int L) {
		int N = timeSeries.length;
		// 第一步，计算轨迹矩阵
		int K = N - L + 1;
		Matrix X = getXMatrix(timeSeries, L, N, K);
		
		//第二步，奇异值分解
		Matrix[] mySVD = getSVD(X);
		Matrix U = mySVD[0];
		Matrix V = mySVD[1];
		Matrix S_Dialog = mySVD[2];
		Matrix sigma2 = mySVD[3];
		Matrix t = mySVD[4];
		Matrix lam = mySVD[5];
		
		//第三步，周期判断
		List<Integer> p = new ArrayList<>();
		List<Double> ffk = new ArrayList<>();
		List<Double> ffk1 = new ArrayList<>();
		//通过一个复数类，傅里叶变换
		Complex m_i = new Complex(0, 1);
		for (int k = 0; k < sigma2.getRowDimension()-1; k++) {
			Complex Ek = new Complex(0,0);
			Complex Ek1 = new Complex(0,0);
			double fk = 0;
			double fk1 = 0;
			for (double f = 0; f <= 0.5; f+=0.001) {
				Complex Ekt = new Complex(0,0);
				Complex Ek1t = new Complex(0,0);
				for (int j = 0; j < sigma2.getRowDimension(); j++) {
					Ekt = Ekt.plus(m_i.times(2*Math.PI*j*f).exp().times(U.get(j, k)));
					Ek1t = Ek1t.plus(m_i.times(2*Math.PI*j*f).exp().times(U.get(j, k+1)));
				}
				if(Ekt.abs()*Ekt.abs() > Ek.abs()*Ek.abs()){
					Ek = Ekt;
					fk = f;
				}
				if(Ek1t.abs()*Ek1t.abs() > Ek1.abs()*Ek1.abs()){
					Ek1 = Ek1t;
					fk1 = f;
				}
			}
			double cri1 = 2*sigma2.getRowDimension()*Math.abs(fk-fk1);
			double cri2 = (Ek.abs()*Ek.abs() + Ek1.abs()*Ek1.abs())/sigma2.getRowDimension();
			if(cri1 < 0.75 && cri2 > (2.0/3.0)){
				p.add(k);//周期项重构序列rc的序号
				ffk.add(fk);//该周期的频率在fk到fk1之间，周期即为1/f
				ffk1.add(fk1);
			}
			
		}
		
		//第四步，重构
		Matrix y = reConstruct(L, N, K, U, V, sigma2, t);
		
		double slam = 0;
		for (int i = 0; i < lam.getRowDimension(); i++) {
			slam += lam.get(i, 0);
		}
		List<Double> per = new ArrayList<>();
		//z为周期信号之和
		Matrix z = new Matrix(N,1);
		for (int i = 0; i < p.size(); i++) {
			//计算趋势项的贡献率，有rck和rck+1共同构成
			per.add((lam.get(p.get(i), 0)+lam.get(p.get(i)+1, 0))/slam);
//			z.plusEquals(y.getMatrix(0, y.getRowDimension()-1,p.get(i),p.get(i)));
//			if(!p.contains(p.get(i)+1) && p.get(i)+1 <y.getColumnDimension()){
//				z.plusEquals(y.getMatrix(0, y.getRowDimension()-1,p.get(i)+1,p.get(i)+1));
//			}
		}

		SSA ssa = new SSA();
		ssa.setPer(per);
		ssa.setFfk(ffk);
		ssa.setFfk1(ffk1);
		ssa.setP(p);
		ssa.setY(y);
		ssa.setS_Dialog(S_Dialog);
//		ssa.setZ(z);//所有周期信号之和
		return ssa;
	}





	public void foo1() {
		double[] x = {-0.0300000000000000,0.200000000000000,0.430000000000000,0.700000000000000,0.890000000000000,1.02000000000000,1.47000000000000,1.69000000000000,2.09000000000000,2.24000000000000,1.88000000000000,1.27000000000000,1.17000000000000,1.43000000000000,1.29000000000000,1.44000000000000,0.860000000000000,1.87000000000000,1.87000000000000,2.09000000000000,1.48000000000000,1.37000000000000,1.01000000000000,0.720000000000000,0.620000000000000,0.300000000000000,0.480000000000000,0.820000000000000,1.45000000000000,1.86000000000000,1.83000000000000,1.86000000000000,2.62000000000000,2.66000000000000,2.68000000000000,2.94000000000000,2.32000000000000,2.32000000000000,2.31000000000000,2.34000000000000,2.03000000000000,3.05000000000000,3.52000000000000,4.31000000000000,4.34000000000000,6.30000000000000,5.57000000000000,3.68000000000000,5.46000000000000,5.80000000000000,6.79000000000000,7.57000000000000,8.01000000000000,6.87000000000000,6.30000000000000,6.45000000000000,7.17000000000000,6.02000000000000,5.62000000000000,5.21000000000000,5.31000000000000,5.28000000000000,5.58000000000000,6.41000000000000,6.49000000000000,6.76000000000000,6.52000000000000,7.31000000000000,6.58000000000000,5.74000000000000,5.43000000000000,4.94000000000000,4.74000000000000,4.87000000000000,5.10000000000000,6.03000000000000,6.75000000000000,6.39000000000000,7.32000000000000,7.87000000000000,8.68000000000000,8.07000000000000,8.69000000000000,8.44000000000000,7.82000000000000,7.59000000000000,6.74000000000000,7.03000000000000,7.41000000000000,10.5800000000000,11.1800000000000,14.1300000000000,15.0200000000000,16.7300000000000,17.7600000000000,16.2800000000000,14.8900000000000,15.4800000000000,17.1100000000000,20.4300000000000,21.0700000000000,21.8800000000000,25.0800000000000,23.8800000000000,23.5800000000000,25.6800000000000,25.5800000000000,26.9600000000000,27.8500000000000,26.8900000000000,24.7800000000000,23.0400000000000,21.1600000000000,20.6900000000000,21.4800000000000,23.4500000000000,21.9400000000000,20.8700000000000,19.0500000000000,18.5600000000000,18.5400000000000,18.0800000000000,18.4500000000000,
				18.4100000000000,18.6500000000000,18.3400000000000,17.7600000000000,16.6500000000000,18.2100000000000,18.8700000000000,18.8500000000000,18.1500000000000,17.3500000000000,15.5300000000000,14.5200000000000,14.3700000000000,13.0300000000000,12.8900000000000,11.9300000000000,11.4700000000000,9.98000000000000,9.71000000000000,8.70000000000000,7.80000000000000,8.22000000000000,7.24000000000000,7.14000000000000,6.88000000000000,6.70000000000000,6.70000000000000,6.63000000000000,6.87000000000000,7.21000000000000,7.07000000000000,7,7.51000000000000,5.98000000000000,6.01000000000000,5.11000000000000,5.40000000000000,4.40000000000000,4.25000000000000,4.55000000000000,4.91000000000000,4.01000000000000,3.54000000000000,3.09000000000000,2.25000000000000,2.98000000000000,2.28000000000000,2.91000000000000,3.37000000000000,2.99000000000000,2.66000000000000,2.88000000000000,2.80000000000000,3.16000000000000,3.12000000000000,3.57000000000000,3.79000000000000,3.44000000000000,3.75000000000000,3.91000000000000,4.82000000000000,4.49000000000000,4.96000000000000,4.95000000000000,6.02000000000000,5.34000000000000,5.83000000000000,6.06000000000000,5.42000000000000,5.70000000000000,4.73000000000000,6.16000000000000,6.60000000000000,6.64000000000000,7.08000000000000,6.32000000000000,6.50000000000000,7.32000000000000,7.17000000000000,7.48000000000000,7.98000000000000,7.24000000000000,7.07000000000000,6.51000000000000,6.45000000000000,5.56000000000000,6.48000000000000,6.31000000000000,7.49000000000000,6.95000000000000,7.17000000000000,6.39000000000000,6,6.06000000000000,5.69000000000000,6.41000000000000,6.92000000000000,5.49000000000000,5.60000000000000,5.25000000000000,5.06000000000000,4.76000000000000,4.87000000000000,4.48000000000000,4.45000000000000,3.37000000000000,3.68000000000000,3.82000000000000,3.53000000000000,3.03000000000000,3.21000000000000,2.91000000000000,3.82000000000000,2.91000000000000,3.24000000000000,2.87000000000000,3,4.01000000000000,3.38000000000000,4.09000000000000,4.45000000000000,3.90000000000000,3.28000000000000,3.98000000000000,5.09000000000000,
				5.10000000000000,4.45000000000000,4.45000000000000,4.41000000000000,4.17000000000000,4.59000000000000,5,5.40000000000000,4.45000000000000,4.70000000000000,4.87000000000000,5.37000000000000,4.91000000000000,5.65000000000000,5.27000000000000,5.45000000000000,6.53000000000000,6.99000000000000,7.28000000000000,7.77000000000000,7.58000000000000,7.35000000000000,7.54000000000000,8.02000000000000,8.52000000000000,8.86000000000000,9.32000000000000,10.2700000000000,10.1300000000000,11.0300000000000,12.0900000000000,13.5800000000000,13.6900000000000,13.3800000000000,13.6900000000000,14.4700000000000,14.5000000000000,14.0400000000000,14.7300000000000,15.0500000000000,15.2900000000000,16.6800000000000,17.4400000000000,17.3500000000000,19.7100000000000,20.6900000000000,21.0400000000000};
//		SSA ssa = ssaTrends(x, 90);
//		SSA ssa = ssaPeriod(x, 90);
		SSA ssa = ssaFilter(x, 90);
		System.out.println(ssa);
	}
}


class QuickSort {
    private static void swap(double[] A, int a, int b){
    	double temp = A[a];
        A[a] = A[b];
        A[b] = temp;
    }
    public static double[] quickSort(double[] A, int n) {
    	 double[] t = new double[A.length];
		 for (int i = 0; i < A.length; i++) {
		 	t[i] = i;
		 }
         quick(A, t, 0, n-1);
         return t;
    }
    private static void quick(double[] A, double[] t, int start, int end){
        if(start >= end) return;
        int mid = partition(A, t, start, end);
        quick(A, t, start , mid);
        quick(A, t, mid+1, end);      
    }
    private static int partition(double[] A, double[] t, int start, int end){
        Random rad = new Random();
        int n = end - start + 1;
        int r = start + rad.nextInt(n);       
        swap(A, end, r);
        swap(t, end, r);
        int j = start;        
        for(int i=start; i<end; i++){
        	//从大到小排序
            if(A[i] > A[end]){
                swap(A, i, j++);
                swap(t, i, j-1);
            }
        }       
        swap(A, end, j);  
        swap(t, end, j);  
        return j;
    }
}
