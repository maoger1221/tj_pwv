package tj.pwv.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tj.pwv.pojo.Mwr2d;
import tj.pwv.pojo.MwrZenit;
import tj.pwv.service.DataService;

@Controller
public class DataController {
	@Autowired
	private DataService dataService;
	//获取分页数据
	@RequestMapping(value="/getdbdata", method=RequestMethod.POST)
	@ResponseBody
	public Object getDbData(String date,String elev,String azi,String iwv,Integer pageNum,String type,HttpSession httpSession){
		if(type == null || type.equals("")){
			type = (String) httpSession.getAttribute("type");
		}else{
			httpSession.setAttribute("type", type);
		}
		if(type.equals("mwr2d")){
			List<Mwr2d> dbData = dataService.getMwr2d(date,elev,azi,iwv,pageNum,httpSession);
			return dbData;
		}else if(type.equals("mwrz")){
			List<MwrZenit> dbData = dataService.getMwrZ(date,elev,azi,iwv,pageNum,httpSession);
			return dbData;
		}
		return null;
	}
	@RequestMapping("/getTotalpages")
	@ResponseBody
	public String getTotalPages(HttpSession httpSession){
		return String.valueOf(httpSession.getAttribute("totalpages"));
	}
	@RequestMapping(value="/clearSession")
	@ResponseBody
	public String clearSession(HttpSession httpSession){
		httpSession.setAttribute("pageNum", 1);
		httpSession.setAttribute("date", "");
		httpSession.setAttribute("elev", "");
		httpSession.setAttribute("azi", "");
		httpSession.setAttribute("iwv", "");
		httpSession.setAttribute("totalpages", "");
		httpSession.setAttribute("type", "");
		return "success";
	}
	
}
