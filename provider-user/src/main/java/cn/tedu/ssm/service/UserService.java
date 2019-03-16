package cn.tedu.ssm.service;

import java.util.List;

import cn.tedu.ssm.pojo.User;

public interface UserService {
	public List<User> findAll();
	public User get(Integer id) ;
	public void insert(User user);
}
