package cn.jt.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.jt.common.vo.SysResult;
import cn.jt.sso.pojo.User;
import cn.jt.sso.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/check/{param}/{type}")
	public SysResult check(@PathVariable String param,@PathVariable Integer type) {
		try {
			boolean b = userService.check(param, type);
			if(b) {
				return SysResult.build(201, "用户、电话、邮箱已存在");
			}else {
				return SysResult.ok(b);		//不存在
			}
		}catch(Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "检查用户出错");
		}
	}
	
	//如果是使用对象，Zuul+Feign必须以json方式接参
	@RequestMapping("/user/register")	//以post请求方式接参 httpclient
	public SysResult regiester(@RequestBody User user) {
		
		userService.register(user);
		return SysResult.ok(user.getUsername());
	}
	
	@RequestMapping("/user/login")	//重命名username，u
	public SysResult login(@RequestParam("u") String username, 
			@RequestParam("p") String password) {
		String ticket = userService.login(username, password);
		return SysResult.ok(ticket);
	}
	
	@RequestMapping("/user/query/{ticket}")
	public SysResult queryByTicket(@PathVariable String ticket) {
		String userJson = userService.queryByTicket(ticket);
		return SysResult.ok(userJson);
	}
}






