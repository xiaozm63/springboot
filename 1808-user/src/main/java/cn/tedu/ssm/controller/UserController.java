package cn.tedu.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.ssm.feign.UserFeign;
import cn.tedu.ssm.pojo.User;

@RestController
public class UserController {
	@Autowired
	private UserFeign userFeign;
	
	@RequestMapping("/user/findAll")
	public List<User> findAll(){
		return userFeign.findAll();
	}
	
	//注意：低版本1.5.4@PathVariable("id")
	@RequestMapping("/user/get/{id}")
	public User get(@PathVariable("id") Integer id) {
		return userFeign.get(id);
	}
	
	@RequestMapping("/user/insert/{name}/{birthday}/{address}/{id}")
	public String insert(User user) {
		return userFeign.insert(user);
	}
}
