package tj.pwv.utils.arima;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class test1 {

	public static void main(String args[])
	{
		Scanner ino=null;
	
		try {
			ArrayList<Double> arraylist=new ArrayList<Double>();
			ino=new Scanner(new File(System.getProperty("user.dir")+"/data/pr2.txt"));
			while(ino.hasNext())
			{
				arraylist.add(Double.parseDouble(ino.next()));
			}
			//递推进行多步预测(5步)
			int n = 0;
			while(n++<5){

				double[] dataArray=new double[arraylist.size()]; 
				for(int i=0;i<arraylist.size();i++)
					dataArray[i]=arraylist.get(i);
			
				ARIMA arima=new ARIMA(dataArray); 
				
				int []model=arima.getARIMAmodel();
				System.out.println("Best model is [p,q]="+"["+model[0]+" "+model[1]+"]");
				
				double pvalue = arima.aftDeal(arima.predictValue(model[0],model[1]));
				System.out.println("Predict value="+ pvalue);
				arraylist.add(pvalue);
//				System.out.println("Predict error="+(arima.aftDeal(arima.predictValue(model[0],model[1]))-arraylist.get(arraylist.size()-1))/arraylist.get(arraylist.size()-1)*100+"%");
			}
		
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally{
			ino.close();
		}
	}
	
	
}
