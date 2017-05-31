package tj.pwv.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tj.pwv.pojo.User;
import tj.pwv.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/user/register", method=RequestMethod.POST)
	public String showRegister(User user,String password1,String invitation,HttpSession httpSession) {
		if(!userService.checkFormat(user, password1, invitation, httpSession))
			return "redirect:/register";
		if(!userService.checkData(user.getUsername(), httpSession))
			return "redirect:/register";
		userService.createUser(user);
		httpSession.setAttribute("msg", "注册成功，请登录！");
		return "redirect:/login";
	}
	@RequestMapping(value="/user/login", method=RequestMethod.POST)
	public String showLogin(String username,String password,HttpSession httpSession) {
		
		boolean flag = userService.userLogin(username, password, httpSession);
		//登录成功
		if(flag == true)
			return "redirect:/";
		
		return "redirect:/login";
	}
	@RequestMapping("/user/quit")
	public String showQuit(HttpSession httpSession) {
		userService.quit(httpSession);
		return "redirect:/login";
	}
}
