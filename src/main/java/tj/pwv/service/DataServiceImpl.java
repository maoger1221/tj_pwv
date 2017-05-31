package tj.pwv.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tj.pwv.mapper.Mwr2dMapper;
import tj.pwv.mapper.MwrZenit30minMapper;
import tj.pwv.mapper.MwrZenitMapper;
import tj.pwv.mapper.PwvMapper;
import tj.pwv.pojo.Mwr2d;
import tj.pwv.pojo.Mwr2dExample;
import tj.pwv.pojo.Mwr2dExample.Criteria;
import tj.pwv.pojo.MwrZenit;
import tj.pwv.pojo.MwrZenit30min;
import tj.pwv.pojo.MwrZenit30minExample;
import tj.pwv.pojo.MwrZenitExample;
import tj.pwv.pojo.Pwv;
import tj.pwv.pojo.PwvExample;
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
	public List<Pwv> getDbDrawingPWV(String date) {
		PwvExample example = new PwvExample();
		tj.pwv.pojo.PwvExample.Criteria criteria = example.createCriteria();
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
			Long count = (long) pwvMapper.countByExample(example);
			// 取最后10天的数据，即480个
			criteria.andIdBetween(count - 480 + 1, count);
		}
		List<Pwv> list = pwvMapper.selectByExample(example);
		return list;
	}
	
}
