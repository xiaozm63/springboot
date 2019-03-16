package cn.jt.sso.service;

import cn.jt.sso.pojo.User;

public interface UserService {
	public Boolean check(String param, Integer type);
	public String register(User user) ;
	public String login(String username, String password);
	public String queryByTicket(String ticket) ;
}
