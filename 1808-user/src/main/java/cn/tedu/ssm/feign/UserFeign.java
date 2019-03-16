package cn.tedu.ssm.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tedu.ssm.pojo.User;

@FeignClient("provider-user")
public interface UserFeign {
	@RequestMapping("/user/findAll")
	public List<User> findAll();
	
	//注意：低版本1.5.4@PathVariable("id")
	@RequestMapping("/user/get/{id}")
	public User get(@PathVariable("id") Integer id) ;
	
	@RequestMapping("/user/insert/{name}/{birthday}/{address}/{id}")
	public String insert(User user) ;
}
