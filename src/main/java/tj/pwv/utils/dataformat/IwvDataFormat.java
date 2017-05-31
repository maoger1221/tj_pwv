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


public class IwvDataFormat {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String iwvPath = "C:\\Users\\mao\\Desktop\\同济大学\\毕业论文\\入库数据\\15年2月份辐射计天顶数据原始.txt";
		ArrayList<IWV> iwv_list = readPWVFile(iwvPath);
		
		writeIWVFile(iwv_list);
	}
	private static ArrayList<IWV> readPWVFile(String iwvPath) {
		// 字节流读取数据
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		ArrayList<IWV> iwv_list = new ArrayList<IWV>();// 存储iwv数据的list
		ArrayList<IWV> iwv_30min = new ArrayList<IWV>();
		
		int year_read, month_read,day_read ,hour_read, min_read, sec_read,rainflag_read;
		double iwv_read,elev_read,azi_read;

		try {
			String str = "";
			int type=0;
			fis = new FileInputStream(iwvPath);
			isr = new InputStreamReader(fis);// InputStreamReader 是字节流通向字符流的桥梁
			br = new BufferedReader(isr);
			while ((str = br.readLine()) != null) {
				year_read = Integer.parseInt(str.substring(0, 2).trim());// 年
				month_read = Integer.parseInt(str.substring(3, 5).trim());// 年积日
				day_read = Integer.parseInt(str.substring(6, 8).trim());// 年积日
				hour_read = Integer.parseInt(str.substring(9, 11).trim());// 时
				min_read = Integer.parseInt(str.substring(12, 14).trim());// 分
				sec_read = Integer.parseInt(str.substring(15, 17).trim());// 秒
				rainflag_read = Integer.parseInt(str.substring(18, 19).trim());// 秒
				iwv_read = Double.parseDouble(str.substring(20, 25).trim());
				elev_read = Double.parseDouble(str.substring(26, 31).trim());
				azi_read = Double.parseDouble(str.substring(32).trim());
				if(min_read<30){
					type=0;
				}else{
					type=1;
				}
				iwv_list.add(new IWV(year_read, month_read,day_read, hour_read, min_read, sec_read,rainflag_read,iwv_read,elev_read,azi_read,type));
			}
			int k=1;
			double sum=iwv_list.get(0).getIwv();
			//忽略了最后一段的情况
			for (int i = 1; i < iwv_list.size(); i++) {
				if(iwv_list.get(i).getType() == iwv_list.get(i-1).getType()){
					sum += iwv_list.get(i).getIwv();
					k++;
				}else{
					if(iwv_list.get(i-1).getType() == 0){
						iwv_30min.add(new IWV(iwv_list.get(i-1).getYear(), iwv_list.get(i-1).getMonth(),iwv_list.get(i-1).getDay(), iwv_list.get(i-1).getHour(), 0, 0,0,sum/k,90,0,iwv_list.get(i-1).getType()));
					}else{
						iwv_30min.add(new IWV(iwv_list.get(i-1).getYear(), iwv_list.get(i-1).getMonth(),iwv_list.get(i-1).getDay(), iwv_list.get(i-1).getHour(), 30, 0,0,sum/k,90,0,iwv_list.get(i-1).getType()));
					}
					k =1;
					sum = iwv_list.get(i).getIwv();
				}
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
		return iwv_30min;
	}
	public static void writeIWVFile(ArrayList<IWV> my_iwv) {
		// 将求得的iwv写入文件
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;

		try {
			fos = new FileOutputStream("C:\\Users\\mao\\Desktop\\同济大学\\毕业论文\\IwvDataFormat(utils包自动生成).txt", true);
			osw = new OutputStreamWriter(fos);
			bw = new BufferedWriter(osw);

			for (IWV iwv : my_iwv) {
				try {
					bw.write(iwv.toString());
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
