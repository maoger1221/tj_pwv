package tj.pwv.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import tj.pwv.mapper.UserMapper;
import tj.pwv.pojo.User;
import tj.pwv.pojo.UserExample;
import tj.pwv.pojo.UserExample.Criteria;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Value("${INVITATION}")
	private String INVITATION;
	@Override
	public boolean userLogin(String username, String password, HttpSession httpSession) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<User> list = userMapper.selectByExample(example);
		//如果没有此用户名
		if (null == list || list.size() == 0) {
			httpSession.setAttribute("msg", "用户名或密码错误");
			return false;
		}
		User user = list.get(0);
		//比对密码
		if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
			httpSession.setAttribute("msg", "用户名或密码错误");
			return false;
		}
		
		httpSession.setAttribute("msg", "");
		httpSession.setAttribute("user", user);
		return true;
	}

	@Override
	public void quit(HttpSession httpSession) {
		// TODO Auto-generated method stub
		httpSession.setAttribute("msg", "");
		httpSession.setAttribute("user", null);
	}
	
	public boolean checkData(String content, HttpSession httpSession) {
		//创建查询条件
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(content);
		//执行查询
		List<User> list = userMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			return true;
		}
		httpSession.setAttribute("unmsg", "用户名已存在");
		return false;
	}

	@Override
	public boolean checkFormat(User user, String password1, String invitation, HttpSession httpSession) {
		// TODO Auto-generated method stub
		boolean res = true;
		httpSession.setAttribute("unmsg", "");
		httpSession.setAttribute("psmsg", "");
		httpSession.setAttribute("emmsg", "");
		httpSession.setAttribute("phmsg", "");
		httpSession.setAttribute("wpmsg", "");
		httpSession.setAttribute("ivmsg", "");
		if(user.getUsername().length() < 5 || user.getUsername().length() > 20){
			httpSession.setAttribute("unmsg", "用户名长度不在5-20间！");
			res = false;
		}
		if(!user.getPassword().equals(password1)){
			httpSession.setAttribute("psmsg", "两次密码不一致！");
			res = false;
		}
		if(user.getPassword().length() < 5 || user.getPassword().length() > 20){
			httpSession.setAttribute("psmsg", "密码长度不在5-20间！");
			res = false;
		}
		if(!user.getEmail().contains("@")){
			httpSession.setAttribute("emmsg", "邮箱地址非法！");
			res = false;
		}
		if(user.getPhone().length() < 1){
			httpSession.setAttribute("phmsg", "电话号码不能为空！");
			res = false;
		}
		if(user.getWorkplace().length() < 1){
			httpSession.setAttribute("wpmsg", "工作单位不能为空！");
			res = false;
		}
		if(!invitation.equals(INVITATION)){
			httpSession.setAttribute("ivmsg", "邀请码错误！");
			res = false;
		}
		return res;
	}

	@Override
	public void createUser(User user) {
		// TODO Auto-generated method stub
		//md5加密
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		userMapper.insert(user);
	}
}
