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
		this.userDao.save(user);
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
	public String findUserById(Integer id) {
		return this.userDao.findById(id);
	}

	@Override
	public List<User> findAllUser() {
		return this.userDao.findAll();
	}

	
	
	
	@Override
	public User findUserByName(String username) {
		return this.userDao.findByName(username);
	}

	@Override
	public void insertUserOrder(Integer id) {
		this.userDao.saveOrder(id);
		
	}

	@Override
	public User login(String username, String password) {
		
		return this.userDao.findUsernameAndPassword(username, password);
	}
	
	@Override
	public Admin adminlogin(String adminname, String adminpassword) {
		return this.userDao.findAdminnameAndAdminpassword(adminname, adminpassword);
	}

	@Override
	public void register(String username, String password, String phone, String email) {
		this.userDao.saveUser(username, password, phone, email);
		
	}

	@Override
	public boolean checkUserName(String username) {
		return this.userDao.findUserName(username);
	}

	@Override
	public boolean modifyUserPassword(User user, String password) {
		return this.userDao.updateUserPassword(user,password);
	}

	
	
	

	


	
}
