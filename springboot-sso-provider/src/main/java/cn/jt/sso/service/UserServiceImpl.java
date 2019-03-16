package cn.jt.sso.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.jt.common.service.RedisClientTemplate;
import cn.jt.common.service.RedisService;
import cn.jt.sso.mapper.UserMapper;
import cn.jt.sso.pojo.User;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
    RedisClientTemplate redisClientTemplate;
	@Autowired
	private UserMapper userMapper;
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	//保存，3种情况
	private static final Map<Integer,String> map = new HashMap<Integer,String>();
	//map值一次定型
	static {		//静态代码块，初始化，初始一次，效率高
		map.put(1, "username");
		map.put(2, "phone");
		map.put(3, "email");
	}
	
	
	//检查是否可用
	public Boolean check(String param, Integer type) {
		//设置where 
		EntityWrapper<User> wrapper = new EntityWrapper<User>();
		//if(type==1) {	//代码结构不是最好，如果type值扩充，if的结构
		wrapper.where(map.get(type) + "={0}", param);
		int i = userMapper.selectCount(wrapper);
		if(i==0) {
			return false;		//用户不存在
		}else {
			return true;			//用户存在
		}
	}
	
	//用户注册
	public String register(User user) {
		//设置日期
		user.setCreated(new Date());
		user.setUpdated(user.getCreated());
		
		//密码进行加密md5，shiro md5hash+盐
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		
		userMapper.insert(user);
		return user.getUsername();
	}
	
	//用户登录
	public String login(String username, String password) {
		String ticket = null;
		//1.按用户名进行查询，获取user对象
		User param = new User();		//查询条件
		param.setUsername(username);
		
		User user = userMapper.selectOne(param);
		if(user!=null) {
			//2.数据库获取的user.password（加密）和页面接收到password（明码）加密比较
			String newPassword = DigestUtils.md5Hex(password);
			if(user.getPassword().equals(newPassword)) {
				try {
					//3.如果是系统用户，产生ticket=redis.key，value=user.json
					//ticket 要求：a.动态性，b.唯一性，c.混淆性
					ticket = DigestUtils.md5Hex("JT_TICKERT_"+System.currentTimeMillis() + username);
					
					//4.写redis，设置过期时间7天
					String userJson = MAPPER.writeValueAsString(user);
					redisClientTemplate.set(ticket, userJson, 60*60*24*7);		//不用，编译时编译器优化，会自动计算出来
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return ticket;
	}
	
	
	//根据ticket查询，返回user对象json串
	public String queryByTicket(String ticket) {
		return redisClientTemplate.get(ticket);
	}
}





