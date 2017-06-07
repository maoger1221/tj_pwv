package tj.pwv.utils.SWV;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class GetSWV {

	public static void main(String[] args) {
		for (int m = 33; m < 46; m++) {
			String pwvPath = "file\\swvdata\\0" + m + "\\met_tjch.150" + m;
			for (int i = 1; i <= 32; i++) {
				String dphPath = "file\\swvdata\\0" + m + "\\DPH.TJCH.PRN";
				if (i < 10) {
					dphPath = dphPath + "0" + i;
				} else {
					dphPath += i;
				}
				if (!new File(dphPath).exists())
					continue;
				List<SWV> my_swv = getMySWV(pwvPath, dphPath);
				writeSWVFile(pwvPath, dphPath, my_swv);
			}
		}

		// System.out.println("运行结束");
	}


	public static void writeSWVFile(String swvPath, String dphPath, List<SWV> my_swv) {
		// 将求得的swv写入文件
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;

		try {
			// fos = new FileOutputStream("file\\swv" +
			// dphPath.substring(dphPath.length() - 2, dphPath.length()) + "."
			// + pwvPath.substring(pwvPath.length() - 5, pwvPath.length()),
			// true);
			fos = new FileOutputStream(
					swvPath + dphPath.substring(dphPath.length() - 2, dphPath.length()) + ".txt", true);
			osw = new OutputStreamWriter(fos);
			bw = new BufferedWriter(osw);

			for (SWV swv1 : my_swv) {
				try {
					bw.write(swv1.toString());
					bw.newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			// System.out.println("没有找到文件");
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				osw.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static List<SWV> getMySWV(String pwvPath, String dphPath) {
		// 1.读取PWV文件中的PWV、gN、gE

		ArrayList<PWV> pwv_list = readPWVFile(pwvPath);

		// 2.读取DPH文件，获取卫星历元、方位角、仰角
		ArrayList<DPH> dph_list = readDPHFile(dphPath);

		// 3.PWV/ZWD转换参数大π，值约0.15
		ArrayList<Double> pi = getPI(pwv_list);// 顺序与pwv_list对应

		// 4.通过12小时的平均，计算湿梯度项gNw、gEw
		ArrayList<PWV> pwv_list_w = getgwNE(pwv_list);// 返回具有湿梯度项的pwvlist

		// 5.计算湿延迟映射函数mw
		double lat = 31.28293624713861;// lat当地纬度
		ArrayList<Double> mw = getMW(lat, dph_list);// 计算得到mw的list，顺序与dph文件相同

		// 6.计算水平梯度映射函数md
		ArrayList<Double> md = getMD(dph_list);// 计算得到md的list，顺序与dph文件相同

		// 7.计算SWV
		ArrayList<SWV> my_swv = calSWV(mw, pwv_list_w, pi, md, dph_list);

		return my_swv;
	}

	private static ArrayList<SWV> calSWV(ArrayList<Double> mw, ArrayList<PWV> pwv_list_w, ArrayList<Double> pi,
			ArrayList<Double> md, ArrayList<DPH> dph_list) {
		ArrayList<SWV> my_swv = new ArrayList<SWV>();
		final int interval = 15;// 测站采样间隔15s
		int hour = 0, min = 0, tMin = 0, sec = 0, tSec = 0;
		double swv = 0;
		for (int i = 0; i < dph_list.size(); i++) {
			// 历元转换为时间
			tSec = dph_list.get(i).getEpoch() * interval;
			sec = tSec % 60;
			tMin = tSec / 60;
			min = tMin % 60;
			hour = tMin / 60;
			for (int j = 0; j < pwv_list_w.size(); j++) {
				if (pwv_list_w.get(j).getHour() == hour && pwv_list_w.get(j).getMin() == min
						&& pwv_list_w.get(j).getSec() == sec) {
					// 如果历元与时间匹配则计算swv
					swv = SWV.calSWV(mw.get(i), pwv_list_w.get(j).getPwv(), pi.get(j), md.get(i),
							pwv_list_w.get(j).getGradNS(), pwv_list_w.get(j).getGradEW(), dph_list.get(i).getAzi());
					my_swv.add(new SWV(pwv_list_w.get(j).getYear(), pwv_list_w.get(j).getDoy(), hour, min, sec, swv,
							dph_list.get(i).getAzi(), dph_list.get(i).getElev()));
				}
			}
		}
		return my_swv;
	}

	private static ArrayList<Double> getMD(ArrayList<DPH> dph_list) {
		ArrayList<Double> md = new ArrayList<Double>();
		for (DPH dph1 : dph_list) {
			md.add(1 / (Math.sin(dph1.getElev() * Math.PI / 180) * Math.tan(dph1.getElev() * Math.PI / 180) + 0.003));
		}
		return md;
	}

	private static ArrayList<PWV> getgwNE(ArrayList<PWV> pwv_list) {
		// final int num=24*60/interval;//个数
		// 将12h的干大气延迟梯度项作为常数
		final int num = pwv_list.size() - 1;
		double gNd1 = 0, gEd1 = 0, gNd2 = 0, gEd2 = 0;// 干梯度项
		for (int i = 0; i < pwv_list.size(); i++) {
			if (i < num / 2) {
				gEd1 += pwv_list.get(i).getGradEW();
				gNd1 += pwv_list.get(i).getGradNS();
			} else if (i < num) {
				gEd2 += pwv_list.get(i).getGradEW();
				gNd2 += pwv_list.get(i).getGradNS();
			}
		}
		gEd1 = gEd1 / (num / 2);
		gNd1 = gNd1 / (num / 2);
		gEd2 = gEd2 / (num / 2);
		gNd2 = gNd2 / (num / 2);
		for (int i = 0; i < pwv_list.size(); i++) {
			if (i < num / 2) {
				pwv_list.get(i).setGradEW(pwv_list.get(i).getGradEW() - gEd1);
				pwv_list.get(i).setGradNS(pwv_list.get(i).getGradNS() - gNd1);
			} else if (i < num) {
				pwv_list.get(i).setGradEW(pwv_list.get(i).getGradEW() - gEd2);
				pwv_list.get(i).setGradNS(pwv_list.get(i).getGradNS() - gNd2);
			}
		}
		return pwv_list;
	}

	private static ArrayList<DPH> readDPHFile(String dphPath) {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		ArrayList<DPH> dph_list = new ArrayList<DPH>();// 存储dph数据的list
		int epoch_read;
		double azi_read, elev_read;

		try {
			String str = "";
			int num_dph = 0;
			fis = new FileInputStream(dphPath);
			isr = new InputStreamReader(fis);// InputStreamReader 是字节流通向字符流的桥梁
			br = new BufferedReader(isr);
			while ((str = br.readLine()) != null) {
				if (num_dph > 1) {
					epoch_read = Integer.parseInt(str.substring(1, 5).trim());// 历元
					azi_read = Double.parseDouble(str.substring(93, 102).trim());// 方位角
					elev_read = Double.parseDouble(str.substring(104, 112).trim());// 高度角
					dph_list.add(new DPH(azi_read, elev_read, epoch_read));
				}

				num_dph++;
			}
		} catch (FileNotFoundException e) {
			// System.out.println("找不到指定文件");
			e.printStackTrace();
		} catch (IOException e) {
			// System.out.println("读取文件失败");
			e.printStackTrace();
		} finally {
			try {
				br.close();
				isr.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return dph_list;
	}

	private static ArrayList<PWV> readPWVFile(String pwvPath) {
		// 字节流读取数据
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		ArrayList<PWV> pwv_list = new ArrayList<PWV>();// 存储pwv数据的list
		int year_read, doy_read, hour_read, min_read, sec_read;
		double zwd_read, pwv_read, gN_read, gE_read;

		try {
			String str = "";
			int num_pwv = 0;
			// String str1 = "";
			fis = new FileInputStream(pwvPath);
			isr = new InputStreamReader(fis);// InputStreamReader 是字节流通向字符流的桥梁
			br = new BufferedReader(isr);
			while ((str = br.readLine()) != null) {
				if (num_pwv > 3) {
					year_read = Integer.parseInt(str.substring(1, 5).trim());// 年
					doy_read = Integer.parseInt(str.substring(6, 9).trim());// 年积日
					hour_read = Integer.parseInt(str.substring(10, 12).trim());// 时
					min_read = Integer.parseInt(str.substring(13, 15).trim());// 分
					sec_read = Integer.parseInt(str.substring(16, 18).trim());// 秒
					zwd_read = Double.parseDouble(str.substring(32, 39).trim());// zwd天顶湿延迟
					pwv_read = Double.parseDouble(str.substring(49, 55).trim());// pwv
					gN_read = Double.parseDouble(str.substring(103, 110).trim());// gN
					gE_read = Double.parseDouble(str.substring(122, 128).trim());// gE
					pwv_list.add(new PWV(year_read, doy_read, hour_read, min_read, sec_read, pwv_read, zwd_read,
							gN_read, gE_read));
				}

				num_pwv++;
			}
		} catch (FileNotFoundException e) {
			// System.out.println("找不到指定文件");
			e.printStackTrace();
		} catch (IOException e) {
			// System.out.println("读取文件失败");
			e.printStackTrace();
		} finally {
			try {
				br.close();
				isr.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return pwv_list;
	}

	private static ArrayList<Double> getMW(double lat, ArrayList<DPH> dph_list) {
		// 计算湿延迟映射函数
		double a = 0, b = 0, c = 0;
		ArrayList<Double> mw = new ArrayList<Double>();
		for (DPH dph1 : dph_list) {
			a = 1.878 * Math.pow(10, -11) * Math.pow(lat, 4) + (-3.935) * Math.pow(10, -9) * Math.pow(lat, 3)
					+ 3.052 * Math.pow(10, -7) * Math.pow(lat, 2) + (-9.035) * Math.pow(10, -6) * lat + 0.0006635;
			b = (-1.048) * Math.pow(10, -10) * Math.pow(lat, 4) + 2.772 * Math.pow(10, -8) * Math.pow(lat, 3)
					+ (-2.223) * Math.pow(10, -6) * Math.pow(lat, 2) + 6.743 * Math.pow(10, -5) * lat + 0.000828;
			c = (-3.097) * Math.pow(10, -9) * Math.pow(lat, 4) + 9.395 * Math.pow(10, -7) * Math.pow(lat, 3)
					+ (-8.063) * Math.pow(10, -5) * Math.pow(lat, 2) + 0.002523 * lat + 0.02076;// 4次多项式拟合
			mw.add((1 + a / (1 + (b / (1 + c))))
					/ (Math.sin(dph1.getElev() * Math.PI / 180) + a / (Math.sin(dph1.getElev() * Math.PI / 180)
							+ (b / (Math.sin(dph1.getElev() * Math.PI / 180) + c)))));
		}
		return mw;
	}

	private static ArrayList<Double> getPI(ArrayList<PWV> pwv_list) {
		ArrayList<Double> pi = new ArrayList<Double>();
		for (PWV pwv1 : pwv_list) {// 增强for循环遍历
			pi.add(pwv1.getPwv() / pwv1.getZwd());
		}
		return pi;
	}

}
