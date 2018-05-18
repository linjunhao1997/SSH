package com.junhao.dao.impl;



import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.junhao.dao.GoodsDao;
import com.junhao.dao.OrderDao;
import com.junhao.dao.UserDao;
import com.junhao.domain.Goods;
import com.junhao.domain.Order;
import com.junhao.domain.User;
import com.junhao.service.GoodsService;



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
	public void saveOrderByUseridAndGoodsid(User user, Integer goodsid) {
		/*Session session = getSession();
		Criteria criteria  = session.createCriteria(Goods.class);
		criteria.add(Restrictions.eq("goodsid",uid));
		Goods goods = (Goods)criteria.uniqueResult();
		Order order = new Order();
		order.setUserid(userid);
		order.setAddress("暂无");
		order.setQuantity(1);
		order.setPrice(goods.getPrice());
		order.setState(0);
		session.save(order);*/
		Session session =getCurrentSession();
		Goods goods=(Goods)session.get(Goods.class,goodsid);
		//goods.setGoodsid(goodsid);
		
		
		Order order = new Order();
		order.setGoods(goods);
		order.setGoodsname(goods.getGoodsname());
		order.setQuantity(1);
		order.setPrice(goods.getPrice()*order.getQuantity());
		order.setUser(user);
		session.save(order);
		
	}

	@Override
	public Set<Order> findOrder(Integer id) {
		
		//String hql = "select o.id, o.price, o.quantity from Order o,Goods g where o.userid=:userid";
		//List<Order> orders =getSession().createQuery(hql).setParameter("userid",id).list();
		//return orders;
		User user = (User)getCurrentSession().get(User.class, id);
		return user.getOrders();
		
	}

	@Override
	public boolean deleteOrder(Order order) {
		Session session=getCurrentSession();
		
		try {
			session.delete(order);
			return true;
		}catch(HibernateException e) {
			return false;
		}
	}



	
}
