package com.junhao.dao;

import java.util.List;
import java.util.Set;

import com.junhao.domain.Goods;
import com.junhao.domain.Order;
import com.junhao.domain.User;

public interface OrderDao {
	
	public void saveOrderByUseridAndGoodsid(User user ,Integer goodsid);
	
	public Set<Order> findOrder(Integer id);
	
	public boolean deleteOrder(Order order);
	
}