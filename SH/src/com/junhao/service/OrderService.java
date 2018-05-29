package com.junhao.service;

import java.util.List;
import java.util.Set;

import com.junhao.domain.Order;
import com.junhao.domain.User;

public interface OrderService {
	
	//通过物品id为用户添加新的订单
	public void addOrder(User user,Integer goodsid);
	
	//删除订单
	public boolean deleteOrder(Order order);
	
	//展示订单，也是购物车中的订单
	public List<Order> showOrder(Integer id);
}
