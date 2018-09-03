package com.junhao.dao;

import java.util.List;
import java.util.Set;

import com.junhao.domain.Order;
import com.junhao.domain.User;

public interface OrderDao {
	
	void saveOrders(User user, Integer goodsid);
	
	List<Order> listOrders(Integer id);
	
	boolean deleteOrder(Order order);
	
}
