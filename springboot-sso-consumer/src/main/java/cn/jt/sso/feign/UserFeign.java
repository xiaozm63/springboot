package cn.jt.sso.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.jt.common.vo.SysResult;

@FeignClient("jt-sso-provider")
public interface UserFeign {
	@RequestMapping("/user/check/{param}/{type}")
	public SysResult check(@PathVariable("param") String param,
			@PathVariable("type") Integer type) ;
	
	@RequestMapping("/user/register")	//SpringCloud 选择RESTFul+json
	public SysResult regiester(@PathVariable("username") String username, 
			@PathVariable("password") String password, 
			@PathVariable("phone") String phone, 
			@PathVariable("email") String email) ;
	
	@RequestMapping("/user/login")
	public SysResult login(@RequestParam("u") String username,
			@RequestParam("p") String password);
	
	@RequestMapping("/user/query/{ticket}")
	public SysResult queryByTicket(@PathVariable("ticket") String ticket);
}
