package com.junhao.dao.impl;



import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.junhao.dao.OrderDao;
import com.junhao.domain.Goods;
import com.junhao.domain.Order;
import com.junhao.domain.User;



@Repository
public class OrderDaoImpl implements OrderDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.openSession();	
	}
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();	
	}

	@Override
	public void saveOrders(User user, Integer goodsid) {
		Session session = getCurrentSession();
		Goods goods = (Goods)session.get(Goods.class, goodsid);
		Order order = new Order();
		order.setGoods(goods);
		order.setQuantity(1);
		order.setPrice(goods.getPrice() * order.getQuantity());
		order.setUser(user);
		session.save(order);	
	}

	@Override
	public List<Order> listOrders(Integer id) {
		
		// String hql = "select o.id, o.price, o.quantity from Order o,Goods g where o.userid=:userid";
		// List<Order> orders =getSession().createQuery(hql).setParameter("userid",id).list();
		// return orders;
		User user = (User)getCurrentSession().get(User.class, id);
		return user.getOrders();
		
	}

	@Override
	public boolean deleteOrder(Order order) {
		Session session = getCurrentSession();
		
		try {
			session.delete(order);
			return true;
		}catch(HibernateException e) {
			return false;
		}
	}	
}
