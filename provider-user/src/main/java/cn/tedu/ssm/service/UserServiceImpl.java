package cn.tedu.ssm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

import cn.tedu.ssm.mapper.UserMapper;
import cn.tedu.ssm.pojo.User;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	
	//查询所有
	public List<User> findAll(){
		return userMapper.selectList(null);
	}
	
	//查询某个用户 where id=?
	public User get(Integer id) {
		EntityWrapper<User> wrapper = new EntityWrapper<User>();
		//id是数据库字段，占位符{0} {1}
		wrapper.where("id={0}", id);
		List<User> userList = userMapper.selectList(wrapper);
		if(userList != null) {
			return userList.get(0);		//只有一个元素
		}else {
			return null;
		}
	}
	
	//新增方法
	public void insert(User user) {
		userMapper.insert(user);
	}
}






