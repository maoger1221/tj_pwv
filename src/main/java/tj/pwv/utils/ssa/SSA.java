package tj.pwv.utils.ssa;

import java.util.List;

import Jama.Matrix;

public class SSA {
	private Matrix y;//各个分量rc（或滤波后信后）
	private Matrix z;//总趋势信号
	private Matrix r;//残差
	private int p_filter;//滤波的裁取长度
	private List<Integer> trends;//趋势信号是rc中的哪几项
	private List<Double> per;//趋势项的贡献率
	private Matrix S_Dialog;//排序后的奇异值
	List<Integer> p;//周期项是rc中的哪几项
	List<Double> ffk;//该周期的频率在fk到fk1之间，周期即为1/f
	List<Double> ffk1;
	
	
	public Matrix getR() {
		return r;
	}
	public void setR(Matrix r) {
		this.r = r;
	}
	public int getP_filter() {
		return p_filter;
	}
	public void setP_filter(int p_filter) {
		this.p_filter = p_filter;
	}
	public Matrix getY() {
		return y;
	}
	public void setY(Matrix y) {
		this.y = y;
	}
	public Matrix getZ() {
		return z;
	}
	public void setZ(Matrix z) {
		this.z = z;
	}
	public List<Integer> getTrends() {
		return trends;
	}
	public void setTrends(List<Integer> trends) {
		this.trends = trends;
	}
	public List<Double> getPer() {
		return per;
	}
	public void setPer(List<Double> per) {
		this.per = per;
	}
	public Matrix getS_Dialog() {
		return S_Dialog;
	}
	public void setS_Dialog(Matrix s_Dialog) {
		S_Dialog = s_Dialog;
	}
	public List<Integer> getP() {
		return p;
	}
	public void setP(List<Integer> p) {
		this.p = p;
	}
	public List<Double> getFfk() {
		return ffk;
	}
	public void setFfk(List<Double> ffk) {
		this.ffk = ffk;
	}
	public List<Double> getFfk1() {
		return ffk1;
	}
	public void setFfk1(List<Double> ffk1) {
		this.ffk1 = ffk1;
	}
	
}
