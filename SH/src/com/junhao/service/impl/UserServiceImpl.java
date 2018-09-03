package com.junhao.service.impl;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.junhao.dao.UserDao;
import com.junhao.domain.Admin;
import com.junhao.domain.User;
import com.junhao.service.UserService;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	
	private UserDao userDao;
	
	@Override
	public void saveUser(User user) {
		this.userDao.saveUser(user);
	}

	@Override
	public void updateUser(User user) {
		this.userDao.update(user);
		
	}

	@Override
	public void deleteUser(User user) {
		this.userDao.delete(user);
		
	}

	@Override
	public String getUsername(Integer id) {
		return this.userDao.getUsername(id);
	}

	@Override
	public List<User> listUsers() {
		return this.userDao.listUsers();
	}

	
	
	
	@Override
	public User findUserByName(String username) {
		return this.userDao.getUser(username);
	}

	@Override
	public void insertUserOrder(Integer id) {
		this.userDao.saveOrder(id);
		
	}

	@Override
	public User getUser(String username, String password) {
		
		return this.userDao.getUser(username, password);
	}
	
	@Override
	public Admin getAdmin(String adminname, String adminpassword) {
		return this.userDao.getAdmin(adminname, adminpassword);
	}

	@Override
	public void insertUser(String username, String password, String phone, String email) {
		this.userDao.saveUser(username, password, phone, email);
		
	}

	@Override
	public boolean existUsername(String username) {
		return this.userDao.existUsername(username);
	}

	@Override
	public boolean updateUserPassword(User user, String password) {
		return this.userDao.updateUserPassword(user,password);
	}

	@Override
	public User getUser(String username) {
		
		return this.userDao.getUser(username);
	}

	
	
	

	


	
}
