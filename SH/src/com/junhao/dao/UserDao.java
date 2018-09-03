package com.junhao.dao;

import java.util.List;

import com.junhao.domain.Admin;
import com.junhao.domain.User;

public interface UserDao {
	
	User getUser(String username, String password);
	
	Admin getAdmin(String adminname, String adminpassword);
	
	void saveUser(String username, String password, String phone, String email);
	
	void saveUser(User user); 
	
	void update(User user);
	
	void delete(User user);
	
	String getUsername(Integer id);
	
	List<User> listUsers();
	
	User getUser(String username);
	
	void saveOrder(Integer id);
	
	boolean existUsername(String username);
	
	boolean updateUserPassword(User user, String password);

}
