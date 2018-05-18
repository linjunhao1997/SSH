package com.junhao.service;

import java.util.List;

import com.junhao.domain.Admin;
import com.junhao.domain.User;

public interface UserService {

	
	public void saveUser(User user);
	public void updateUser(User user);
	public void deleteUser(User user);
	public String findUserById(Integer id);
	public List<User> findAllUser();
	
	//通过一份用户名来查找用户并返回User类的全部信息
	public User findUserByName(String username);
	
	
	 //通过用户的id为用户添加新的订单
	public void insertUserOrder(Integer id);
	//通过用户名和密码找出用户
	public User login(String username, String password);
	
	//通过管理员帐号和管理员密码找出管理员
	public Admin adminlogin(String adminname, String adminpassword);
	
	//通过后面的参数注册新的账户
	public void register(String username,String password,String phone,String email);
	
	//检测登录名是否已被注册
	public boolean checkUserName(String username);
	
	//修改密码
	public boolean modifyUserPassword(User user,String password);
}
