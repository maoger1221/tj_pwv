package tj.pwv.service;

import javax.servlet.http.HttpSession;

import tj.pwv.pojo.User;

public interface UserService {
	boolean checkData(String content, HttpSession httpSession);
	boolean checkFormat(User user,String password1,String invitation,HttpSession httpSession);
	void createUser(User user);
	boolean userLogin(String username, String password,HttpSession httpSession);
	void quit(HttpSession httpSession);

}
