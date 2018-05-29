package com.junhao.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.junhao.domain.User;
import com.junhao.service.UserService;



@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations="classpath:applicationContext.xml") 
public class TestUser {
	
	@Autowired
	
	private UserService userService;
	@Test
	public void demoTest() {
	
		System.out.print(userService.findUserById(4));
		
	}
	
	@Test
	public void demoTest1() {
		System.out.println(userService.findUserByName("林俊浩").getPassword());
	}
	
	@Test
	public void demoTest2() {
		userService.insertUserOrder(8);
	}
	
	@Test 
	public void demoTest3() {
		User user=userService.login("林俊浩","1234567");
		System.out.println(user.getPassword());
	}
	
	

	
	
	
}
