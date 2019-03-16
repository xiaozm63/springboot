package cn.tedu.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.ssm.pojo.User;
import cn.tedu.ssm.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/findAll")
	public List<User> findAll(){
		return userService.findAll();
	}
	
	@RequestMapping("/user/get/{id}")
	public User get(@PathVariable Integer id) {
		return userService.get(id);
	}
	
	@RequestMapping("/user/insert/{name}/{birthday}/{address}/{id}")
	public String insert(User user) {
		try {
			userService.insert(user);
			return "success insert";
		}catch(Exception e) {
			return "error insert";
		}
	}
}
