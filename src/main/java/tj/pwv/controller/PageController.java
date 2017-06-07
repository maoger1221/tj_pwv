package tj.pwv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PageController {

	@RequestMapping("/")
	public String showIndex(HttpSession httpSession) {
		setActive(httpSession,"index");
		return "index";
	}
	@RequestMapping("/register")
	public String showRegister() {
		return "register";
	}
	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}
	@RequestMapping("/dbdata_mwr2d")
	public String showDbDatamwr2d(HttpSession httpSession) {
		setActive(httpSession,"dbdata_mwr2d");
		return "dbdata_mwr2d";
	}
	@RequestMapping("/dbdata_mwrz")
	public String showDbDatamwrz(HttpSession httpSession) {
		setActive(httpSession,"dbdata_mwrz");
		return "dbdata_mwrz";
	}
	@RequestMapping("/pwvimg")
	public String getImg(HttpSession httpSession){
		setActive(httpSession,"pwvimg");
		return "pwvimg";
	}
	@RequestMapping("/dbdrawing")
	public String showDbDrawing(HttpSession httpSession) {
		setActive(httpSession,"dbdrawing");
		return "dbdrawing";
	}
	@RequestMapping("/dbdata_swv")
	public String showDbDataswv(HttpSession httpSession) {
		setActive(httpSession,"dbdata_swv");
		return "dbdata_swv";
	}
	@RequestMapping("/dataAnalysis")
	public String showDataAnalysis(HttpSession httpSession) {
		setActive(httpSession,"dataAnalysis");
		return "dataAnalysis";
	}
	private void setActive(HttpSession httpSession,String page){
		httpSession.setAttribute("index",null);
		httpSession.setAttribute("dbdata_mwr2d",null);
		httpSession.setAttribute("dbdata_mwrz",null);
		httpSession.setAttribute("pwvimg",null);
		httpSession.setAttribute("dbdrawing",null);
		httpSession.setAttribute("dataAnalysis",null);

		httpSession.setAttribute(page,"active-menu");
	}
}