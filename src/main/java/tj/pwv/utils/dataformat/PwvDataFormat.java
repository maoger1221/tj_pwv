package tj.pwv.utils.dataformat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class PwvDataFormat {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<PWV> pwv_list = new ArrayList<PWV>();
		for (int m = 33; m < 46; m++) {
			String pwvPath = "C:\\Users\\mao\\Desktop\\同济大学\\毕业论文\\计算swv\\swvdata\\0" + m + "\\met_tjch.150" + m;
			pwv_list.addAll(readPWVFile(pwvPath));
		}
		writeSWVFile(pwv_list);
	}
	private static ArrayList<PWV> readPWVFile(String pwvPath) {
		// 字节流读取数据
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		ArrayList<PWV> pwv_list = new ArrayList<PWV>();// 存储pwv数据的list
		int year_read, doy_read, hour_read, min_read, sec_read;
		double tzen_read,wzen_read,szen_read,pwv_read,spwv_read,press_read,temp_read,zhd_read,gradNS_read,sNS_read,gradEW_read,sEW_read;

		try {
			String str = "";
			int num_pwv = 0;
			// String str1 = "";
			fis = new FileInputStream(pwvPath);
			isr = new InputStreamReader(fis);// InputStreamReader 是字节流通向字符流的桥梁
			br = new BufferedReader(isr);
			while ((str = br.readLine()) != null) {
				if (num_pwv > 3 && num_pwv < 52) {
					year_read = Integer.parseInt(str.substring(1, 5).trim());// 年
					doy_read = Integer.parseInt(str.substring(6, 9).trim());// 年积日
					hour_read = Integer.parseInt(str.substring(10, 12).trim());// 时
					min_read = Integer.parseInt(str.substring(13, 15).trim());// 分
					sec_read = Integer.parseInt(str.substring(16, 18).trim());// 秒
					tzen_read = Double.parseDouble(str.substring(22, 30).trim());
					wzen_read = Double.parseDouble(str.substring(32, 39).trim());// zwd天顶湿延迟
					szen_read = Double.parseDouble(str.substring(40, 47).trim());
					pwv_read = Double.parseDouble(str.substring(49, 55).trim());// pwv
					spwv_read = Double.parseDouble(str.substring(56, 63).trim());
					press_read = Double.parseDouble(str.substring(69, 78).trim());
					temp_read = Double.parseDouble(str.substring(82, 90).trim());
					zhd_read = Double.parseDouble(str.substring(91, 100).trim());
					gradNS_read = Double.parseDouble(str.substring(103, 110).trim());// gN
					sNS_read = Double.parseDouble(str.substring(112, 119).trim());// sgN
					gradEW_read = Double.parseDouble(str.substring(121, 128).trim());// gE
					sEW_read = Double.parseDouble(str.substring(129).trim());// sgE
					
					pwv_list.add(new PWV(year_read, doy_read, hour_read, min_read, sec_read,tzen_read,wzen_read,szen_read,pwv_read,spwv_read,press_read,temp_read,zhd_read,gradNS_read,sNS_read,gradEW_read,sEW_read));
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
	public static void writeSWVFile(ArrayList<PWV> my_pwv) {
		// 将求得的swv写入文件
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;

		try {
			fos = new FileOutputStream("C:\\Users\\mao\\Desktop\\同济大学\\毕业论文\\PwvDataFormat.txt", true);
			osw = new OutputStreamWriter(fos);
			bw = new BufferedWriter(osw);

			for (PWV pwv : my_pwv) {
				try {
					bw.write(pwv.toString());
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
}
