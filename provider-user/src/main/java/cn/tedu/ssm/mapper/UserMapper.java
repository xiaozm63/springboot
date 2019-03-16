package cn.tedu.ssm.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.tedu.ssm.pojo.User;

//引入MybatisPlus
public interface UserMapper  extends BaseMapper<User>{
		//如果有扩充方法还可以添加，关联查询
}
