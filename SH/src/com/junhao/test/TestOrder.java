package com.junhao.test;


import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;


import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.junhao.domain.Order;
import com.junhao.domain.User;
import com.junhao.service.OrderService;




@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations="classpath:applicationContext.xml") 
public class TestOrder {
	
	@Resource
	private OrderService orderService;
	@Test
	public void demoTest() {
		User user = new User();
		user.setId(9);
		orderService.addOrder(user, 1);
		
	}
	
	@Test
	public void demoTest2() {
	
		Set<Order> orders=orderService.showOrder(8);
		Iterator<Order> iterator = orders.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next().getGoods().getIntroduce());
		}
	}
	
	
}
