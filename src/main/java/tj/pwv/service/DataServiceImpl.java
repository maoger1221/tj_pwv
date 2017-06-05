package tj.pwv.service;

import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpSession;
import javax.swing.text.View;

import Jama.Matrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tj.pwv.mapper.Mwr2dMapper;
import tj.pwv.mapper.MwrZenit30minMapper;
import tj.pwv.mapper.MwrZenitMapper;
import tj.pwv.mapper.PwvMapper;
import tj.pwv.pojo.*;
import tj.pwv.pojo.Mwr2dExample.Criteria;
import tj.pwv.utils.arima.ARIMA;
import tj.pwv.utils.dataformat.PWV;
import tj.pwv.utils.ssa.SSA;
import tj.pwv.utils.ssa.SSAUtils;

@Service
public class DataServiceImpl implements DataService {
	@Autowired
	private Mwr2dMapper mwr2dmapper;
	@Autowired
	private MwrZenitMapper mwrzenitmapper;
	@Autowired
	private PwvMapper pwvMapper;
	@Autowired
	private MwrZenit30minMapper  mwrzenit30minmapper;
	@Value("${TOTALPERPAGE}")
	private Integer TOTALPERPAGE;
	@Override
	public List<Mwr2d> getMwr2d(String date,String elev,String azi,String iwv,Integer pageNum,HttpSession httpSession) {
		// TODO Auto-generated method stub
		//设置分页查询
		if(pageNum == null)
			pageNum = 1;
		PageHelper.startPage(pageNum, TOTALPERPAGE);
		Mwr2dExample example = new Mwr2dExample();
		//添加查询条件
		Criteria criteria = example.createCriteria();
		//查询日期范围
		if(date != "" && date != null){
			//若session中没有该参数，则放入
			httpSession.setAttribute("date", date);
			String[] sdate = date.split("~");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			try {
				Date date1 = sdf.parse(sdate[0]);
				Date date2 = sdf.parse(sdate[1]);
				criteria.andDateBetween(date1, date2);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else{
			if(httpSession.getAttribute("date") !="" && httpSession.getAttribute("date") !=null){
				//若session中有该参数，则取出
				String hdate = (String) httpSession.getAttribute("date");
				String[] sdate = hdate.split("~");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
				try {
					Date date1 = sdf.parse(sdate[0]);
					Date date2 = sdf.parse(sdate[1]);
					criteria.andDateBetween(date1, date2);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		//查询高度角范围
		if(elev != "" && elev != null){
			httpSession.setAttribute("elev", elev);
			String[] selev = elev.split("~");
			criteria.andElevBetween(new BigDecimal(selev[0]), new BigDecimal(selev[1]));
		}else{
			if(httpSession.getAttribute("elev") !="" && httpSession.getAttribute("elev") !=null){
				String helev = (String) httpSession.getAttribute("elev");
				String[] selev = helev.split("~");
				criteria.andElevBetween(new BigDecimal(selev[0]), new BigDecimal(selev[1]));
			}
		}
		//查询方位角范围
		if(azi != "" && azi != null){
			httpSession.setAttribute("azi", azi);
			String[] sazi = azi.split("~");
			criteria.andAziBetween(new BigDecimal(sazi[0]), new BigDecimal(sazi[1]));
		}
		else{
			if(httpSession.getAttribute("azi") !="" && httpSession.getAttribute("azi") !=null){
				String hazi = (String) httpSession.getAttribute("azi");
				String[] sazi = hazi.split("~");
				criteria.andAziBetween(new BigDecimal(sazi[0]), new BigDecimal(sazi[1]));
			}
		}
		//查询综合水汽范围
		if(iwv != "" && iwv != null){
			httpSession.setAttribute("iwv", iwv);
			String[] siwv = iwv.split("~");
			criteria.andIwvBetween(new BigDecimal(siwv[0]), new BigDecimal(siwv[1]));
		}
		else{
			if(httpSession.getAttribute("iwv") !="" && httpSession.getAttribute("iwv") !=null){
				String hiwv = (String) httpSession.getAttribute("iwv");
				String[] siwv = hiwv.split("~");
				criteria.andIwvBetween(new BigDecimal(siwv[0]), new BigDecimal(siwv[1]));
			}
		}
		List<Mwr2d> list = mwr2dmapper.selectByExample(example);
		httpSession.setAttribute("pageNum", pageNum);
		if(httpSession.getAttribute("totalpages")=="" || httpSession.getAttribute("totalpages")==null){
			PageInfo<Mwr2d> pageInfo = new PageInfo<>(list);
			httpSession.setAttribute("totalpages", pageInfo.getPages());
		}
		return list;
	}
	
	
	
	
	public List<MwrZenit> getMwrZ(String date,String elev,String azi,String iwv,Integer pageNum,HttpSession httpSession) {
		// TODO Auto-generated method stub
		//设置分页查询
		if(pageNum == null)
			pageNum = 1;
		PageHelper.startPage(pageNum, TOTALPERPAGE);
		MwrZenitExample example = new MwrZenitExample();
		//添加查询条件
		tj.pwv.pojo.MwrZenitExample.Criteria criteria = example.createCriteria();
		//查询日期范围
		if(date != "" && date != null){
			//若session中没有该参数，则放入
			httpSession.setAttribute("date", date);
			String[] sdate = date.split("~");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			try {
				Date date1 = sdf.parse(sdate[0]);
				Date date2 = sdf.parse(sdate[1]);
				criteria.andDateBetween(date1, date2);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else{
			if(httpSession.getAttribute("date") !="" && httpSession.getAttribute("date") !=null){
				//若session中有该参数，则取出
				String hdate = (String) httpSession.getAttribute("date");
				String[] sdate = hdate.split("~");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
				try {
					Date date1 = sdf.parse(sdate[0]);
					Date date2 = sdf.parse(sdate[1]);
					criteria.andDateBetween(date1, date2);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		//查询高度角范围
		if(elev != "" && elev != null){
			httpSession.setAttribute("elev", elev);
			String[] selev = elev.split("~");
			criteria.andElevBetween(new BigDecimal(selev[0]), new BigDecimal(selev[1]));
		}else{
			if(httpSession.getAttribute("elev") !="" && httpSession.getAttribute("elev") !=null){
				String helev = (String) httpSession.getAttribute("elev");
				String[] selev = helev.split("~");
				criteria.andElevBetween(new BigDecimal(selev[0]), new BigDecimal(selev[1]));
			}
		}
		//查询方位角范围
		if(azi != "" && azi != null){
			httpSession.setAttribute("azi", azi);
			String[] sazi = azi.split("~");
			criteria.andAziBetween(new BigDecimal(sazi[0]), new BigDecimal(sazi[1]));
		}
		else{
			if(httpSession.getAttribute("azi") !="" && httpSession.getAttribute("azi") !=null){
				String hazi = (String) httpSession.getAttribute("azi");
				String[] sazi = hazi.split("~");
				criteria.andAziBetween(new BigDecimal(sazi[0]), new BigDecimal(sazi[1]));
			}
		}
		//查询综合水汽范围
		if(iwv != "" && iwv != null){
			httpSession.setAttribute("iwv", iwv);
			String[] siwv = iwv.split("~");
			criteria.andIwvBetween(new BigDecimal(siwv[0]), new BigDecimal(siwv[1]));
		}
		else{
			if(httpSession.getAttribute("iwv") !="" && httpSession.getAttribute("iwv") !=null){
				String hiwv = (String) httpSession.getAttribute("iwv");
				String[] siwv = hiwv.split("~");
				criteria.andIwvBetween(new BigDecimal(siwv[0]), new BigDecimal(siwv[1]));
			}
		}
		List<MwrZenit> list = mwrzenitmapper.selectByExample(example);
		httpSession.setAttribute("pageNum", pageNum);
		if(httpSession.getAttribute("totalpages")=="" || httpSession.getAttribute("totalpages")==null){
			PageInfo<MwrZenit> pageInfo = new PageInfo<>(list);
			httpSession.setAttribute("totalpages", pageInfo.getPages());
		}
		return list;
	}
	
	//获取绘图数据（实时pwv）
	@Override
	public List<Pwv> getPWV() {
		// TODO Auto-generated method stub
		PwvExample example = new PwvExample();
		Long count = (long) pwvMapper.countByExample(example);
		tj.pwv.pojo.PwvExample.Criteria criteria = example.createCriteria();
		// 取最后10天的数据，即480个
		criteria.andIdBetween(count - 480 + 1, count);
		List<Pwv> list = pwvMapper.selectByExample(example);
		return list;
	}
	
	//获取绘图数据（水汽辐射计天顶,30min）
	@Override
	public List<MwrZenit30min> getDbDrawingMwrZ(String date) {
		
		MwrZenit30minExample example = new MwrZenit30minExample();
		//添加查询条件
		tj.pwv.pojo.MwrZenit30minExample.Criteria criteria = example.createCriteria();
		//查询日期范围
		if(date != "" && date != null){

			String[] sdate = date.split("~");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			try {
				Date date1 = sdf.parse(sdate[0]);
				Date date2 = sdf.parse(sdate[1]);
				criteria.andDateBetween(date1, date2);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else{
			Long count = (long) mwrzenit30minmapper.countByExample(example);
			criteria.andIdBetween(count - 480 + 1, count);
		}
		
		List<MwrZenit30min> list = mwrzenit30minmapper.selectByExample(example);

		return list;
	}



	//获取绘图数据（pwv）
	@Override
	public List<Pwv> getDbDrawingPWV(String date, String ownfilename) {
		List<Pwv> list = null;
		//当用户上传自定义文件时
		if(ownfilename != "" && ownfilename != null) {
			list = readOwnFile(ownfilename);
		}else {
			PwvExample example = new PwvExample();
			tj.pwv.pojo.PwvExample.Criteria criteria = example.createCriteria();
			//查询日期范围
			if (date != "" && date != null) {
				String[] sdate = date.split("~");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					Date date1 = sdf.parse(sdate[0]);
					Date date2 = sdf.parse(sdate[1]);
					criteria.andDateBetween(date1, date2);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else {
				Long count = (long) pwvMapper.countByExample(example);
				// 取最后10天的数据，即480个
				criteria.andIdBetween(count - 480 + 1, count);
			}
			list = pwvMapper.selectByExample(example);
		}
		return list;
	}

	public ViewObject getDbDrawingPWV(String date, String ownfilename,String selectbox) {
		List<Pwv> list = null;
		//当用户上传自定义文件时
		if(ownfilename != "" && ownfilename != null) {
			list = readOwnFile(ownfilename);
		}else {
			PwvExample example = new PwvExample();
			tj.pwv.pojo.PwvExample.Criteria criteria = example.createCriteria();
			//查询日期范围
			if (date != "" && date != null) {
				String[] sdate = date.split("~");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					Date date1 = sdf.parse(sdate[0]);
					Date date2 = sdf.parse(sdate[1]);
					criteria.andDateBetween(date1, date2);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else {
				Long count = (long) pwvMapper.countByExample(example);
				// 取最后10天的数据，即480个
				criteria.andIdBetween(count - 480 + 1, count);
			}
			list = pwvMapper.selectByExample(example);
		}
		ViewObject vo = null;
		if (selectbox != null && selectbox != ""){
			vo = doAnalysis(selectbox,list);
		}
		return vo;
	}

	private static final String DATAPOSTION1 = "E:\\JetBrains\\IdeaProjects\\tj_pwv\\src\\main\\resources\\upload\\";
	//读取自定义文件
	private List<Pwv> readOwnFile(String ownfilename) {
		File file = new File(DATAPOSTION1 + ownfilename);
		if (!file.exists()){
			return null;
		}
		List<Pwv> list = new ArrayList<>();
		BufferedReader br = null;
		FileReader fr = null;

		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String str = "";
			while ((str = br.readLine()) != null) {
				Pwv pwv = new Pwv();
				BigDecimal bigDecimal = new BigDecimal(Double.parseDouble(str));
				pwv.setPw(bigDecimal);
				list.add(pwv);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}


	private ViewObject doAnalysis(String selectbox, List<Pwv> list) {
		double[] pwvForSsa = new double[list.size()];
		for (int i = 0; i < list.size(); i++) {
			pwvForSsa[i] = list.get(i).getPw().doubleValue();
		}
		ViewObject vo = new ViewObject();
		if (selectbox.equals("1")){
			SSA ssa = SSAUtils.ssaFilter(pwvForSsa,list.size()/3);
			vo.set("p_filter",ssa.getP_filter());//滤波裁取长度
			vo.set("pwv_after",matrixToList(ssa.getY()));//滤波后信号
			vo.set("r",matrixToList(ssa.getR()));//残差
			vo.set("sigma",matrixToList(ssa.getS_Dialog()));//奇异值
			return vo;
		}else if (selectbox.equals("2")){
			SSA ssa = SSAUtils.ssaTrends(pwvForSsa,list.size()/3);
			vo.set("trends",ssa.getTrends());//趋势项序号
			vo.set("per",ssa.getPer());//趋势项贡献率
			vo.set("pwv_after",matrixToList(ssa.getZ()));//总的趋势项
			vo.set("y",matrixToList2(ssa.getY()));//各个rc
			vo.set("sigma",matrixToList(ssa.getS_Dialog()));//奇异值
			return vo;
		}else if (selectbox.equals("3")){
			SSA ssa = SSAUtils.ssaPeriod(pwvForSsa,list.size()/3);
			vo.set("per",ssa.getPer());//周期项贡献率
			vo.set("p",ssa.getP());//周期项序号(每个周期项由两项构成，p和p+1)
			vo.set("y",matrixToList2(ssa.getY()));//各个rc
			vo.set("sigma",matrixToList(ssa.getS_Dialog()));//奇异值
			vo.set("ffk",ssa.getFfk());//频率在ffk和ffk1之间
			vo.set("ffk1",ssa.getFfk1());
//			vo.set("pwv_after",matrixToList(ssa.getZ()));
//			vo.set("pwv_after","");
			return vo;
		}else if (selectbox.equals("4")){
			List<Double> datalist=new ArrayList<Double>();
			for(Pwv pwv : list){
				datalist.add(pwv.getPw().doubleValue());
			}
			//递推进行多步预测(5步)
			int n = 5;
			for (int j = 0; j < n; j++){

				double[] dataArray=new double[datalist.size()];
				for(int i=0;i<datalist.size();i++)
					dataArray[i]=datalist.get(i);

				ARIMA arima=new ARIMA(dataArray);

				int []model=arima.getARIMAmodel();
				System.out.println("Best model is [p,q]="+"["+model[0]+" "+model[1]+"]");

				double pvalue = arima.aftDeal(arima.predictValue(model[0],model[1]));
				System.out.println("Predict value="+ pvalue);
				datalist.add(pvalue);
			}
//			vo.set("pwv_after","");
			vo.set("predict",datalist);
			return vo;
		}

		return null;
	}

	private List<Double> matrixToList(Matrix x){
		List<Double> list = new ArrayList<>();
		double[][] array = x.getArray();
		for (int i = 0; i < x.getRowDimension(); i++) {
			list.add(array[i][0]);
		}
		return list;
	}
	private List<List<Double>> matrixToList2(Matrix x){
		List<List<Double>> list = new ArrayList<>();
		double[][] array = x.getArray();
		for (int i = 0; i < x.getRowDimension(); i++) {
			List<Double> listCol = new ArrayList<>();
			for (int j = 0; j < x.getColumnDimension(); j++) {
				listCol.add(array[i][j]);
			}
			list.add(listCol);
		}
		return list;
	}

}
