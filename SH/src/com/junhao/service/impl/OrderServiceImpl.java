package com.junhao.service.impl;



import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.junhao.dao.GoodsDao;
import com.junhao.dao.OrderDao;
import com.junhao.dao.UserDao;
import com.junhao.domain.Goods;
import com.junhao.domain.Order;
import com.junhao.domain.User;
import com.junhao.service.GoodsService;
import com.junhao.service.OrderService;
import com.junhao.service.UserService;

@Transactional
@Service("OrderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;
	@Override
	public void addOrder(User user, Integer goodsid) {
		this.orderDao.saveOrderByUseridAndGoodsid(user, goodsid);
	}
	@Override
	public Set<Order> showOrder(Integer id) {
		return this.orderDao.findOrder(id);
	}
	@Override
	public boolean deleteOrder(Order order) {
		return this.orderDao.deleteOrder(order);
	}
	
	

}
