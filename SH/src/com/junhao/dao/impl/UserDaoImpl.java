package com.junhao.dao.impl;



import java.util.List;




import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;


import com.junhao.dao.UserDao;
import com.junhao.domain.Admin;
import com.junhao.domain.Order;
import com.junhao.domain.User;



@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.openSession();
		
	}
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
		
	}
	
	@Override
	public void save(User user) {
		
		
	}

	@Override
	public void update(User user) {
		
		
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String findById(Integer id) {
		String hql = "select username from User u where u.id=:id";
		String s=(String) getCurrentSession().createQuery(hql).setParameter("id",id).uniqueResult(); 
		return s;
	}

	@Override
	public List<User> findAll() {
		
		
		return null;
	}
	
	
	
	
	
	@Override
	public User findByName(String username) {
		Criteria criteria  = getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("username",username ));
		User user = (User)criteria.uniqueResult();
		return user;	
	}
	@Override
	public void saveOrder(Integer id) {
		Session session=getCurrentSession();
		User user =(User)session.get(User.class, id);
		Order order = new Order();
		order.setAddress("广东省广州市");
		order.setPrice(50.00);
		order.setUser(user);
		user.getOrders().add(order);
		session.save(user);
		session.save(order);
		
	}
	@Override
	public User findUsernameAndPassword(String username, String password) {
		Criteria criteria  = getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("username",username ));
		criteria.add(Restrictions.eq("password",password ));
		User user = (User)criteria.uniqueResult();
		return user;
	}

	
	//增加用户
	@Override
	public void saveUser(String username, String password, String phone, String email) {
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setPhone(phone);
		user.setEmail(email);
		getCurrentSession().save(user);
		
	}
	
	//检查用户名
	public boolean findUserName(String username) {
		Criteria criteria  = getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("username",username ));
		User user = (User)criteria.uniqueResult();
		if(user!=null) {
			return true;
		}
		else {
			return false;
		}
	}
	@Override
	public boolean updateUserPassword(User user, String password) {
		user.setPassword(password);
		try {
			getCurrentSession().update(user);
			return true;
		}catch(HibernateException e) {
			return false;
		}
	}
	@Override
	public Admin findAdminnameAndAdminpassword(String adminname, String adminpassword) {
		Criteria criteria  = getCurrentSession().createCriteria(Admin.class);
		criteria.add(Restrictions.eq("adminname",adminname ));
		criteria.add(Restrictions.eq("adminpassword",adminpassword ));
		Admin admin = (Admin)criteria.uniqueResult();
		return admin;
	}


	
}
