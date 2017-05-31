package tj.pwv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	@RequestMapping("/")
	public String showIndex() {
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
	public String showDbDatamwr2d() {
		return "dbdata_mwr2d";
	}
	@RequestMapping("/dbdata_mwrz")
	public String showDbDatamwrz() {
		return "dbdata_mwrz";
	}
	@RequestMapping("/pwvimg")
	public String getImg(){
		return "pwvimg";
	}
	@RequestMapping("/dbdrawing")
	public String showDbDrawing() {
		return "dbdrawing";
	}
}