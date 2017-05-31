package tj.pwv;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;

import tj.pwv.mapper.Mwr2dMapper;
import tj.pwv.pojo.Mwr2d;
import tj.pwv.pojo.Mwr2dExample;
import tj.pwv.utils.JsonUtils;

public class TestPageHelper {

	@Test
	public void fun() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/applicationContext-*.xml");
		Mwr2dMapper mapper = applicationContext.getBean(Mwr2dMapper.class);
		Mwr2dExample example = new Mwr2dExample();
		PageHelper.startPage(2, 30);
		List<Mwr2d> list = mapper.selectByExample(example);
//		for (Mwr2d mwr2d : list) {
//			System.out.println(mwr2d.getDay() + "  " + mwr2d.getHour() + "  " + mwr2d.getMinute());
//		}
//		PageInfo<Mwr2d> pageInfo = new PageInfo<>(list);
//		System.out.println("共有" + pageInfo.getTotal() + "pageNum" + pageInfo.getPageNum());
		String json = JsonUtils.objectToJson(list);
		System.out.println(json);
	}
	@Test
	public void fun1(){
		String dateString = "2012-12-06 02:41:12";  
		 
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		    try {
				Date date = sdf.parse(dateString);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	private String doy2day(int year, int doy){
		int flag = 0;
		String temp;
		if(year % 4 == 0)
			flag = 1;
		if(doy <= 31){
			temp = "00"+doy;
			return year + "-01" + "-" + temp.substring(temp.length()-2);
		}else if(doy <= 59 + flag){
			temp = "00"+(doy-31);
			return year + "-02" + "-" + temp.substring(temp.length()-2);
		}else if(doy <= 90 + flag){
			temp = "00"+(doy-59-flag);
			return year + "-03" + "-" + temp.substring(temp.length()-2);
		}else if(doy <= 120 + flag){
			temp = "00"+(doy-90-flag);
			return year + "-04" + "-" + temp.substring(temp.length()-2);
		}else if(doy <= 151 + flag){
			temp = "00"+(doy-120-flag);
			return year + "-05" + "-" + temp.substring(temp.length()-2);
		}else if(doy <= 181 + flag){
			temp = "00"+(doy-151-flag);
			return year + "-06" + "-" + temp.substring(temp.length()-2);
		}else if(doy <= 212 + flag){
			temp = "00"+(doy-181-flag);
			return year + "-07" + "-" + temp.substring(temp.length()-2);
		}else if(doy <= 243 + flag){
			temp = "00"+(doy-212-flag);
			return year + "-08" + "-" + temp.substring(temp.length()-2);
		}else if(doy <= 273 + flag){
			temp = "00"+(doy-243-flag);
			return year + "-09" + "-" + temp.substring(temp.length()-2);
		}else if(doy <= 304 + flag){
			temp = "00"+(doy-273-flag);
			return year + "-10" + "-" + temp.substring(temp.length()-2);
		}else if(doy <= 334 + flag){
			temp = "00"+(doy-304-flag);
			return year + "-11" + "-" + temp.substring(temp.length()-2);
		}else if(doy <= 365 + flag){
			temp = "00"+(doy-334-flag);
			return year + "-12" + "-" + temp.substring(temp.length()-2);
		}
		return "";
	}
	@Test
	public void fun2(){
		System.out.println(doy2day(2016,60));
	}
}
