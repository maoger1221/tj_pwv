package tj.pwv.utils.reanalysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;
import ucar.nc2.dataset.NetcdfDataset;
import ucar.nc2.grib.grib1.Grib1RecordScanner;
import ucar.unidata.io.RandomAccessFile;

public class ncUtils {
	// TJCH站信息
	// 不加.0运算会损失精度
	private final double B = 31.0 + 17.0 / 60 + 5.84123 / 3600;
	private final double L = 121.0 + 29.0 / 60 + 51.84707 / 3600;
	// H=36.7305;
	private final double H = 3.54048420;

	private final String startDate = "2015-2-2";
	private final String endDate = "2015-2-15";
	private final String originalDate = "1800-1-1";
	private final String originalDate2 = "1900-1-1";

	// 周围格网点
	double[] B_era = { 30.75, 31.5 };
	double[] L_era = { 120.75, 121.5 };
	double[] B_necp = { 30, 32.5 };
	double[] L_necp = { 120, 122.5 };

	private final double G = 9.80665;


	public double[] getJRA() {
		// (31.25,121.25) 1 (31.25,122.5) 2
		// (32.5,121.25) 3 (32.5,122.5) 4
		// e=0:1.25:360;
		// n=90:-1.25:-90;
		// 121.25E122.5E所对应的行号98和99
		// 31.25N32.5N所对应的行号48和47
		// 测站点周围四个点所对应的行号
		int lon1 = 0;// 数组从0开始
		int lon2 = 0;
		int lat1 = 0;
		int lat2 = 0;

		double[] pwv_jra = null;

		// 经度的行号,分辨率为1.25
		float ee = 0;
		for (int i = 0; ee <= 360; i++, ee += 1.25) {
			if (ee <= L && ee + 1.25 >= L) {
				lon1 = i;
				lon2 = i + 1;
				lon1++;
				lon2++;
				break;
			}
		}
		float nn = 90;
		for (int i = 0; nn >= -90; i++, nn -= 1.25) {
			if (nn >= B && nn - 1.25 <= B) {
				lat1 = i + 1;
				lat2 = i;
				lat1++;
				lat2++;
				break;
			}
		}
		System.out.println();
		try {
			ArrayList<ArrayList<Float>> press_jra = new ArrayList<>();
			ArrayList<ArrayList<Float>> temp_jra = new ArrayList<>();
			String[] filename = { "0200", "0206", "0212", "0218", "0300", "0306", "0312", "0318", "0400", "0406",
					"0412", "0418", "0500", "0506", "0512", "0518", "0600", "0606", "0612", "0618", "0700", "0706",
					"0712", "0718", "0800", "0806", "0812", "0818", "0900", "0906", "0912", "0918", "1000", "1006",
					"1012", "1018", "1100", "1106", "1112", "1118", "1200", "1206", "1212", "1218", "1300", "1306",
					"1312", "1318", "1400", "1406", "1412", "1418", "1500" };
			for (int k = 0; k < filename.length; k++) {

				// http://www.unidata.ucar.edu/software/thredds/current/netcdf-java/reference/formats/GribFiles.html
				// 读取grib1文件，使用netcdfAll-4.6的jar包

				RandomAccessFile raf = new RandomAccessFile(
						"C:/Users/mao/Desktop/同济大学/毕业论文/三种常用再分析资料/JRA数据/jra_data/anl_surf125.201502" + filename[k],
						"r");
				Grib1RecordScanner reader = new Grib1RecordScanner(raf);
				int i = -1;// 第一组数据是气压，第三组数据是温度
				while (reader.hasNext()) {
					ucar.nc2.grib.grib1.Grib1Record gr1 = reader.next();
					i++;
					if (i != 0 && i != 2)
						continue;

					float[] data = gr1.readData(raf);
					if (i == 0) {
						ArrayList<Float> p = new ArrayList<>();
						p.add(data[(lat1 - 1) * 288 + lon1 - 1]);// 分辨率1.25
						p.add(data[(lat1 - 1) * 288 + lon2 - 1]);// 分辨率1.25
						p.add(data[(lat2 - 1) * 288 + lon1 - 1]);// 分辨率1.25
						p.add(data[(lat2 - 1) * 288 + lon2 - 1]);// 分辨率1.25
						press_jra.add(p);
					}
					if (i == 2) {
						ArrayList<Float> t = new ArrayList<>();
						t.add(data[(lat1 - 1) * 288 + lon1 - 1]);// 分辨率1.25
						t.add(data[(lat1 - 1) * 288 + lon2 - 1]);// 分辨率1.25
						t.add(data[(lat2 - 1) * 288 + lon1 - 1]);// 分辨率1.25
						t.add(data[(lat2 - 1) * 288 + lon2 - 1]);// 分辨率1.25
						temp_jra.add(t);
					}
				}
				raf.close();
			}
			double[][] press = new double[4][press_jra.size()];
			double[][] temp = new double[4][temp_jra.size()];
			int k = 0;
			for (ArrayList<Float> array : press_jra) {
				int i = 0;
				for (Float float1 : array) {
					press[i++][k] = float1;
				}
				k++;
			}
			k = 0;
			for (ArrayList<Float> array : temp_jra) {
				int i = 0;
				for (Float float1 : array) {
					temp[i++][k] = float1;
				}
				k++;
			}

			// 读取hgt文件
			RandomAccessFile raf = new RandomAccessFile(
					"C:/Users/mao/Desktop/同济大学/毕业论文/三种常用再分析资料/JRA数据/jra_data/LL125.grib", "r");
			Grib1RecordScanner reader = new Grib1RecordScanner(raf);
			ucar.nc2.grib.grib1.Grib1Record gr1 = reader.next();
			float[] hgt = gr1.readData(raf);
			raf.close();
			double[] height = new double[4];
			height[0] = hgt[(lat1 - 1) * 288 + lon1 - 1] / G;
			height[1] = hgt[(lat1 - 1) * 288 + lon2 - 1] / G;
			height[2] = hgt[(lat2 - 1) * 288 + lon1 - 1] / G;
			height[3] = hgt[(lat2 - 1) * 288 + lon2 - 1] / G;

			// 气压垂直插值
			double[][] pv = new double[4][press[0].length];
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < press[0].length; j++) {
					pv[i][j] = press[i][j] * Math.exp(-(H - height[i]) * 9.8 * 0.0289 / 8.31 / temp[i][j]);
				}

			}

			// 温度垂直插值
			double[][] tv = new double[4][temp[0].length];
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < temp[0].length; j++) {
					tv[i][j] = temp[i][j] + 0.98 * (H - height[i]) / 100;
				}

			}

			// 化为弧度
			double arcB = B * Math.PI / 180;
			double arcL = L * Math.PI / 180;
			double[] Bj = { B_era[0] * Math.PI / 180, B_era[0] * Math.PI / 180, B_era[1] * Math.PI / 180,
					B_era[1] * Math.PI / 180 };
			double[] Lj = { L_era[0] * Math.PI / 180, L_era[1] * Math.PI / 180, L_era[0] * Math.PI / 180,
					L_era[1] * Math.PI / 180 };

			double[] wj = new double[4];
			double sumwj = 0;
			for (int i = 0; i < 4; i++) {
				wj[i] = Math.pow((Math.acos(Math.sin(Bj[i])) * Math.sin(arcB)
						+ Math.cos(Bj[i]) * Math.cos(arcB) * Math.cos(Lj[i] - arcL) * 6371004), -2);
				sumwj += wj[i];
			}
			for (int i = 0; i < 4; i++) {
				wj[i] /= sumwj;
			}

			double[] p = new double[press[0].length];
			for (int j = 0; j < pv[0].length; j++) {
				for (int i = 0; i < 4; i++) {
					p[j] += pv[i][j] * wj[i];
				}
				p[j] /= 100;// 单位化成hPa
			}
			double[] t = new double[temp[0].length];
			for (int j = 0; j < tv[0].length; j++) {
				for (int i = 0; i < 4; i++) {
					t[j] += tv[i][j] * wj[i];
				}
			}
			double[] x1 = new double[p.length];
			for (int i = 0; i < p.length; i++) {
				x1[i] = i * 6 + 1;// 时间间隔6小时
			}
			int[] xx = new int[(int) x1[x1.length - 1]];
			for (int i = 0; i < xx.length; i++) {
				xx[i] = i + 1;
			}
			SplineUtils sp1 = new SplineUtils(x1, p, x1.length);
			double[] yp = sp1.s(xx);// 插值结果与matlab相比相差很小
			SplineUtils sp2 = new SplineUtils(x1, t, x1.length);
			double[] yt = sp2.s(xx);

			double[] ZHD = new double[yp.length];
			for (int i = 0; i < yp.length; i++) {
				ZHD[i] = 2.2779 * yp[i] / (1 - 0.00266 * Math.cos(2 * arcB) - 0.00028 * H);
			}
			double[] Bigpi = new double[yt.length];
			for (int i = 0; i < yp.length; i++) {
				Bigpi[i] = Math.pow(10, 5) / (174073600 / (0.72 * (yt[i] - 273.15) + 266.868) + 7597.28);
			}

			// 从doy33 2点到doy45 14点
			double[] ZHD_copy = Arrays.copyOfRange(ZHD, 2, 304);// 包括前面，不包括后面
			double[] Bigpi_copy = Arrays.copyOfRange(Bigpi, 2, 304);

			// 读取ZTD
			ArrayList<Double> ZTD = new ArrayList<>();
			File file = new File("C:/Users/mao/Desktop/同济大学/毕业论文/三种常用再分析资料/ncep数据/ncep_surface_2015/ztd.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String read;
			while ((read = bufferedReader.readLine()) != null) {
				ZTD.add(Double.parseDouble(read));
			}

			pwv_jra = new double[ZHD_copy.length];
			for (int i = 0; i < pwv_jra.length; i++) {
				pwv_jra[i] = (ZTD.get(i) - ZHD_copy[i]) * Bigpi_copy[i];
			} // 最终结果与matlab几乎相同

			System.out.println();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pwv_jra;
	}

	public double[] getERA() {
		// (30.75,120.75) 1 (30.75,121.5) 2
		// (31.5,120.75) 3 (31.5,121.5) 4
		// 120.75E121.5E所对应的行号162和163
		// 30.75N31.5N所对应的行号80和79

		// 测站点周围四个点所对应的行号
		int lon1 = 0;// 数组从0开始
		int lon2 = 0;
		int lat1 = 0;
		int lat2 = 0;

		double[] pwv_era = null;

		NetcdfFile tfile = null;
		NetcdfFile hfile = null;
		try {
			tfile = NetcdfDataset
					.open("C:/Users/mao/Desktop/同济大学/毕业论文/三种常用再分析资料/ERA-Interim数据/era_feb_2015_0.75_4times.nc");
			hfile = NetcdfDataset.open("C:/Users/mao/Desktop/同济大学/毕业论文/三种常用再分析资料/ERA-Interim数据/era_hgt.nc");

			// 获取测站四周四个点的行号
			Variable lonvar = tfile.findVariable("longitude");
			Variable latvar = tfile.findVariable("latitude");
			float[] lon = (float[]) lonvar.read().copyToNDJavaArray();
			float[] lat = (float[]) latvar.read().copyToNDJavaArray();
			// 经度的行号
			for (int i = 0; i < lon.length - 1; i++) {
				if (lon[i] <= L && lon[i + 1] >= L) {
					lon1 = i;
					lon2 = i + 1;
					break;
				}
			}
			// 纬度的行号
			for (int i = 0; i < lat.length - 1; i++) {
				if (lat[i] >= B && lat[i + 1] <= B) {// 纬度是从90到-90
					lat1 = i + 1;
					lat2 = i;
					break;
				}
			}

			// era的时间从1900.1.1开始(时间均为UTC),一维
			Variable tvar = tfile.findVariable("time");
			int[] time = (int[]) tvar.read().copyToNDJavaArray();
			// 2015.2.2(doy33)距1900.1.1的小时数
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date d1 = format.parse(startDate);
			Date d0 = format.parse(originalDate2);
			long hours1 = (d1.getTime() - d0.getTime()) / (1000 * 60 * 60);
			// 2015.2.15(doy46)距1900.1.1的小时数
			Date d2 = format.parse(endDate);
			long hours2 = (d2.getTime() - d0.getTime()) / (1000 * 60 * 60);
			int rows1 = 0;
			int rows2 = 0;
			for (int i = 0; i < time.length; i++) {
				if ((long) time[i] == hours1)
					rows1 = i;// 不用加1
				if ((long) time[i] == hours2)
					rows2 = i;
			}
			Variable temp_era = tfile.findVariable("skt");// 维度：时间、纬度、经度（间隔都是0.75）
			double t_scale_factor = (Double) temp_era.findAttribute("scale_factor").getValue(0);
			double t_add_offset = (Double) temp_era.findAttribute("add_offset").getValue(0);

			short[][][] tempvar = (short[][][]) temp_era.read().copyToNDJavaArray();

			double[][] temp = new double[4][rows2 - rows1 + 1];
			for (int i = rows1; i <= rows2; i++) {
				temp[0][i - rows1] = tempvar[i][lat1][lon1] * t_scale_factor + t_add_offset;
				temp[1][i - rows1] = tempvar[i][lat1][lon2] * t_scale_factor + t_add_offset;
				temp[2][i - rows1] = tempvar[i][lat2][lon1] * t_scale_factor + t_add_offset;
				temp[3][i - rows1] = tempvar[i][lat2][lon2] * t_scale_factor + t_add_offset;
			}

			Variable presvar = tfile.findVariable("sp");
			double p_scale_factor = (Double) presvar.findAttribute("scale_factor").getValue(0);
			double p_add_offset = (Double) presvar.findAttribute("add_offset").getValue(0);
			short[][][] pres = (short[][][]) presvar.read().copyToNDJavaArray();
			double[][] press = new double[4][rows2 - rows1 + 1];
			for (int i = rows1; i <= rows2; i++) {
				press[0][i - rows1] = pres[i][lat1][lon1] * p_scale_factor + p_add_offset;
				press[1][i - rows1] = pres[i][lat1][lon2] * p_scale_factor + p_add_offset;
				;
				press[2][i - rows1] = pres[i][lat2][lon1] * p_scale_factor + p_add_offset;
				;
				press[3][i - rows1] = pres[i][lat2][lon2] * p_scale_factor + p_add_offset;
				;
			}

			Variable hgtvar = hfile.findVariable("z");
			double z_scale_factor = (Double) hgtvar.findAttribute("scale_factor").getValue(0);
			double z_add_offset = (Double) hgtvar.findAttribute("add_offset").getValue(0);
			short[][][] hgt = (short[][][]) hgtvar.read().copyToNDJavaArray();
			double[] height = new double[4];
			// 进行尺度转换，并将将位势高转换成位势米
			height[0] = (hgt[0][lat1][lon1] * z_scale_factor + z_add_offset) / G;
			height[1] = (hgt[0][lat1][lon2] * z_scale_factor + z_add_offset) / G;
			height[2] = (hgt[0][lat2][lon1] * z_scale_factor + z_add_offset) / G;
			height[3] = (hgt[0][lat2][lon2] * z_scale_factor + z_add_offset) / G;

			// 气压垂直插值
			double[][] pv = new double[4][rows2 - rows1 + 1];
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < press[0].length; j++) {
					pv[i][j] = press[i][j] * Math.exp(-(H - height[i]) * 9.8 * 0.0289 / 8.31 / temp[i][j]);
				}

			}

			// 温度垂直插值
			double[][] tv = new double[4][rows2 - rows1 + 1];
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < press[0].length; j++) {
					tv[i][j] = temp[i][j] + 0.98 * (H - height[i]) / 100;
				}

			}

			// 化为弧度
			double arcB = B * Math.PI / 180;
			double arcL = L * Math.PI / 180;
			double[] Bj = { B_era[0] * Math.PI / 180, B_era[0] * Math.PI / 180, B_era[1] * Math.PI / 180,
					B_era[1] * Math.PI / 180 };
			double[] Lj = { L_era[0] * Math.PI / 180, L_era[1] * Math.PI / 180, L_era[0] * Math.PI / 180,
					L_era[1] * Math.PI / 180 };

			double[] wj = new double[4];
			double sumwj = 0;
			for (int i = 0; i < 4; i++) {
				wj[i] = Math.pow((Math.acos(Math.sin(Bj[i])) * Math.sin(arcB)
						+ Math.cos(Bj[i]) * Math.cos(arcB) * Math.cos(Lj[i] - arcL) * 6371004), -2);
				sumwj += wj[i];
			}
			for (int i = 0; i < 4; i++) {
				wj[i] /= sumwj;
			}

			double[] p = new double[rows2 - rows1 + 1];
			for (int j = 0; j < pv[0].length; j++) {
				for (int i = 0; i < 4; i++) {
					p[j] += pv[i][j] * wj[i];
				}
				p[j] /= 100;// 单位化成hPa
			}
			double[] t = new double[rows2 - rows1 + 1];
			for (int j = 0; j < tv[0].length; j++) {
				for (int i = 0; i < 4; i++) {
					t[j] += tv[i][j] * wj[i];
				}
			}
			double[] x1 = new double[p.length];
			for (int i = 0; i < p.length; i++) {
				x1[i] = i * 6 + 1;// 时间间隔6小时
			}
			int[] xx = new int[(int) x1[x1.length - 1]];
			for (int i = 0; i < xx.length; i++) {
				xx[i] = i + 1;
			}
			SplineUtils sp1 = new SplineUtils(x1, p, x1.length);
			double[] yp = sp1.s(xx);// 插值结果与matlab相比相差很小
			SplineUtils sp2 = new SplineUtils(x1, t, x1.length);
			double[] yt = sp2.s(xx);

			double[] ZHD = new double[yp.length];
			for (int i = 0; i < yp.length; i++) {
				ZHD[i] = 2.2779 * yp[i] / (1 - 0.00266 * Math.cos(2 * arcB) - 0.00028 * H);
			}
			double[] Bigpi = new double[yt.length];
			for (int i = 0; i < yp.length; i++) {
				Bigpi[i] = Math.pow(10, 5) / (174073600 / (0.72 * (yt[i] - 273.15) + 266.868) + 7597.28);
			}

			// 从doy33 2点到doy45 14点
			double[] ZHD_copy = Arrays.copyOfRange(ZHD, 2, 304);// 包括前面，不包括后面
			double[] Bigpi_copy = Arrays.copyOfRange(Bigpi, 2, 304);

			// 读取ZTD
			ArrayList<Double> ZTD = new ArrayList<>();
			File file = new File("C:/Users/mao/Desktop/同济大学/毕业论文/三种常用再分析资料/ncep数据/ncep_surface_2015/ztd.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String read;
			while ((read = bufferedReader.readLine()) != null) {
				ZTD.add(Double.parseDouble(read));
			}

			pwv_era = new double[ZHD_copy.length];
			for (int i = 0; i < pwv_era.length; i++) {
				pwv_era[i] = (ZTD.get(i) - ZHD_copy[i]) * Bigpi_copy[i];
			} // 最终结果与matlab几乎相同
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (null != tfile)
					tfile.close();
				if (null != hfile)
					hfile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return pwv_era;

	}

	public double[] getNCEP() {
		// 返回值时间范围doy33 0点到doy45 24点，共53个时间（13天*一天4个+1（doy46 0点））
		// (30,120) 1 (30,122.5) 2
		// (32.5,120) 3 (32.5,122.5) 4
		// 120E122.5E所对应的行号49和50
		// 30N32.5N所对应的行号25和24
		// 测站点周围四个点所对应的行号
		int lon1 = 0;// 数组从0开始
		int lon2 = 0;
		int lat1 = 0;
		int lat2 = 0;

		double[] pwv_ncep = null;

		NetcdfFile tfile = null;
		NetcdfFile pfile = null;
		NetcdfFile hfile = null;
		try {
			tfile = NetcdfDataset
					.open("C:/Users/mao/Desktop/同济大学/毕业论文/三种常用再分析资料/ncep数据/ncep_surface_2015/air.sig995.2015.nc");
			pfile = NetcdfDataset
					.open("C:/Users/mao/Desktop/同济大学/毕业论文/三种常用再分析资料/ncep数据/ncep_surface_2015/pres.sfc.2015.nc");
			hfile = NetcdfDataset.open("C:/Users/mao/Desktop/同济大学/毕业论文/三种常用再分析资料/ncep数据/ncep_surface_2015/hgt.sfc.nc");

			// 获取测站四周四个点的行号
			Variable lonvar = tfile.findVariable("lon");
			Variable latvar = tfile.findVariable("lat");
			float[] lon = (float[]) lonvar.read().copyToNDJavaArray();
			float[] lat = (float[]) latvar.read().copyToNDJavaArray();
			// 经度的行号
			for (int i = 0; i < lon.length - 1; i++) {
				if (lon[i] <= L && lon[i + 1] >= L) {
					lon1 = i;
					lon2 = i + 1;
					break;
				}
			}
			// 纬度的行号
			for (int i = 0; i < lat.length - 1; i++) {
				if (lat[i] >= B && lat[i + 1] <= B) {// 纬度是从90到-90
					lat1 = i + 1;
					lat2 = i;
					break;
				}
			}

			// ncep的时间从1800.1.1开始（时间均为utc）,一维
			Variable tvar = tfile.findVariable("time");
			double[] time = (double[]) tvar.read().copyToNDJavaArray();
			// 2015.2.2(doy33)距1800.1.1的小时数
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date d1 = format.parse(startDate);
			Date d0 = format.parse(originalDate);
			long hours1 = (d1.getTime() - d0.getTime()) / (1000 * 60 * 60);
			// 2015.2.15(doy46)距1800.1.1的小时数
			Date d2 = format.parse(endDate);
			long hours2 = (d2.getTime() - d0.getTime()) / (1000 * 60 * 60);
			int rows1 = 0;
			int rows2 = 0;
			for (int i = 0; i < time.length; i++) {
				if ((long) time[i] == hours1)
					rows1 = i;// 不用加1
				if ((long) time[i] == hours2)
					rows2 = i;
			}
			Variable airvar = tfile.findVariable("air");// 维度：时间、纬度、经度（间隔都是2.5）
			float[][][] air = (float[][][]) airvar.read().copyToNDJavaArray();

			double[][] temp = new double[4][rows2 - rows1 + 1];
			for (int i = rows1; i <= rows2; i++) {
				temp[0][i - rows1] = air[i][lat1][lon1];
				temp[1][i - rows1] = air[i][lat1][lon2];
				temp[2][i - rows1] = air[i][lat2][lon1];
				temp[3][i - rows1] = air[i][lat2][lon2];
			}

			Variable presvar = pfile.findVariable("pres");
			float[][][] pres = (float[][][]) presvar.read().copyToNDJavaArray();
			double[][] press = new double[4][rows2 - rows1 + 1];
			for (int i = rows1; i <= rows2; i++) {
				press[0][i - rows1] = pres[i][lat1][lon1];
				press[1][i - rows1] = pres[i][lat1][lon2];
				press[2][i - rows1] = pres[i][lat2][lon1];
				press[3][i - rows1] = pres[i][lat2][lon2];
			}

			Variable hgtvar = hfile.findVariable("hgt");
			float[][][] hgt = (float[][][]) hgtvar.read().copyToNDJavaArray();
			double[] height = new double[4];
			height[0] = hgt[0][lat1][lon1];
			height[1] = hgt[0][lat1][lon2];
			height[2] = hgt[0][lat2][lon1];
			height[3] = hgt[0][lat2][lon2];

			// 气压垂直插值
			double[][] pv = new double[4][rows2 - rows1 + 1];
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < press[0].length; j++) {
					pv[i][j] = press[i][j] * Math.exp(-(H - height[i]) * 9.8 * 0.0289 / 8.31 / temp[i][j]);
				}

			}

			// 温度垂直插值
			double[][] tv = new double[4][rows2 - rows1 + 1];
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < press[0].length; j++) {
					tv[i][j] = temp[i][j] + 0.98 * (H - height[i]) / 100;
				}

			}

			// 化为弧度
			double arcB = B * Math.PI / 180;
			double arcL = L * Math.PI / 180;
			double[] Bj = { B_necp[0] * Math.PI / 180, B_necp[0] * Math.PI / 180, B_necp[1] * Math.PI / 180,
					B_necp[1] * Math.PI / 180 };
			double[] Lj = { L_necp[0] * Math.PI / 180, L_necp[1] * Math.PI / 180, L_necp[0] * Math.PI / 180,
					L_necp[1] * Math.PI / 180 };

			double[] wj = new double[4];
			double sumwj = 0;
			for (int i = 0; i < 4; i++) {
				wj[i] = Math.pow((Math.acos(Math.sin(Bj[i])) * Math.sin(arcB)
						+ Math.cos(Bj[i]) * Math.cos(arcB) * Math.cos(Lj[i] - arcL) * 6371004), -2);
				sumwj += wj[i];
			}
			for (int i = 0; i < 4; i++) {
				wj[i] /= sumwj;
			}

			double[] p = new double[rows2 - rows1 + 1];
			for (int j = 0; j < pv[0].length; j++) {
				for (int i = 0; i < 4; i++) {
					p[j] += pv[i][j] * wj[i];
				}
				p[j] /= 100;// 单位化成hPa
			}
			double[] t = new double[rows2 - rows1 + 1];
			for (int j = 0; j < tv[0].length; j++) {
				for (int i = 0; i < 4; i++) {
					t[j] += tv[i][j] * wj[i];
				}
			}
			double[] x1 = new double[p.length];
			for (int i = 0; i < p.length; i++) {
				x1[i] = i * 6 + 1;// 时间间隔6小时
			}
			int[] xx = new int[(int) x1[x1.length - 1]];
			for (int i = 0; i < xx.length; i++) {
				xx[i] = i + 1;
			}
			SplineUtils sp1 = new SplineUtils(x1, p, x1.length);
			double[] yp = sp1.s(xx);// 插值结果与matlab相比相差很小
			SplineUtils sp2 = new SplineUtils(x1, t, x1.length);
			double[] yt = sp2.s(xx);

			double[] ZHD = new double[yp.length];
			for (int i = 0; i < yp.length; i++) {
				ZHD[i] = 2.2779 * yp[i] / (1 - 0.00266 * Math.cos(2 * arcB) - 0.00028 * H);
			}
			double[] Bigpi = new double[yt.length];
			for (int i = 0; i < yp.length; i++) {
				Bigpi[i] = Math.pow(10, 5) / (174073600 / (0.72 * (yt[i] - 273.15) + 266.868) + 7597.28);
			}

			// 从doy33 2点到doy45 14点
			double[] ZHD_copy = Arrays.copyOfRange(ZHD, 2, 304);// 包括前面，不包括后面
			double[] Bigpi_copy = Arrays.copyOfRange(Bigpi, 2, 304);

			// 读取ZTD
			ArrayList<Double> ZTD = new ArrayList<>();
			File file = new File("C:/Users/mao/Desktop/同济大学/毕业论文/三种常用再分析资料/ncep数据/ncep_surface_2015/ztd.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String read;
			while ((read = bufferedReader.readLine()) != null) {
				ZTD.add(Double.parseDouble(read));
			}

			pwv_ncep = new double[ZHD_copy.length];
			for (int i = 0; i < pwv_ncep.length; i++) {
				pwv_ncep[i] = (ZTD.get(i) - ZHD_copy[i]) * Bigpi_copy[i];
			} // 最终结果与matlab几乎相同

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != tfile)
					tfile.close();
				if (null != pfile)
					pfile.close();
				if (null != hfile)
					hfile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return pwv_ncep;
	}

	public static void print(double[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
	}
}
