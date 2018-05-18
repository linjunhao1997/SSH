package com.junhao.dao;

import java.util.List;

import com.junhao.domain.Admin;
import com.junhao.domain.User;

public interface UserDao {
	User findUsernameAndPassword(String username,String password);
	
	Admin  findAdminnameAndAdminpassword(String adminname,String adminpassword);
	public void saveUser(String username,String password,String phone,String email);
	
	
	public void save(User user); 
	public void update(User user);
	public void delete(User user);
	public String findById(Integer id);
	public List<User> findAll();
	
	
	
	
	public User findByName(String username);
	
	
	public void saveOrder(Integer id);
	
	public boolean findUserName(String username);
	
	public boolean updateUserPassword(User user,String password);
}
