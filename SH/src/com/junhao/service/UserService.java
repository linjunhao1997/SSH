package com.junhao.service;

import java.util.List;

import com.junhao.domain.Admin;
import com.junhao.domain.User;

public interface UserService {

	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUser(User user);
	
	String getUsername(Integer id);
	
	List<User> listUsers();
	
	//通过一份用户名来查找用户并返回User类的全部信息
	User findUserByName(String username);
	
	//通过用户的id为用户添加新的订单
	void insertUserOrder(Integer id);
	
	//通过用户名和密码找出用户
	User getUser(String username, String password);
	
	//通过管理员帐号和管理员密码找出管理员
	Admin getAdmin(String adminname, String adminpassword);
	
	//通过后面的参数注册新的账户
	void insertUser(String username,String password, String phone, String email);
	
	//检测登录名是否已被注册
	boolean existUsername(String username);
	
	//修改密码
	boolean updateUserPassword(User user, String password);

	User getUser(String usernameCookie);
}
