package com.junhao.dao.impl;



import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import com.junhao.dao.GoodsDao;
import com.junhao.domain.Goods;
import com.junhao.domain.GoodsImage;


@Repository
public class GoodsDaoImpl implements GoodsDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.openSession();
		
	}
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
		
	}



	@Override
	public List<Goods> findGoods() {
		String hql = "from Goods";
		Query query = getCurrentSession().createQuery(hql);
		List<Goods> goodses=query.list();
		return goodses;
	}



	@Override
	public List<Goods> findGoodsByPageNum(int index, int row) {
		Session session=getCurrentSession();
		String hql = "from Goods";
		Query query = session.createQuery(hql);
		query.setFirstResult(index);
		query.setMaxResults(row);
		List<Goods> goodslist = query.list();
	
		return goodslist;
	}



	@Override
	public int findGoodsCount() {
		//下面这条hql语句中select省略后出错了
		//第①种方法
		//String hql = "select count(*) from Goods";
		//Long count = (Long)getSession().createQuery(hql).uniqueResult();
		//return count.intValue();
		
		//第②种方法
		Criteria criteria = getCurrentSession().createCriteria(Goods.class);
		criteria.setProjection(Projections.rowCount());
		Long count = (Long)criteria.uniqueResult();
		return count.intValue();
	}



	@Override
	public boolean delete(int goodsid) {
		
		
		try {
			String hql="delete from Goods g where g.goodsid=:goodsid";
			Query query=getCurrentSession().createQuery(hql).setParameter("goodsid",goodsid);
			query.executeUpdate();
			return true;
		}catch(HibernateException e) {
			return false;
		}
		
	}



	@Override
	public int insertGoods(Goods entity) {
		
		Session session=getCurrentSession();
		//session.beginTransaction();
		GoodsImage goodsImage = new GoodsImage();
		goodsImage.setImgpath("");
		entity.getGoodsimages().add(goodsImage);
		session.save(entity);
		
		//session.getTransaction().commit();
		return entity.getGoodsid();
	}



	@Override
	public Goods findGoodsById(int goodsid) {
		Session session=getCurrentSession();
		Goods goods =(Goods)session.load(Goods.class, goodsid);
		
		return goods;
	}
	/*@Override
	public boolean updateImgpath(Goods entity,String imgpath) {
		Session session=getSession();
		session.beginTransaction();
		
		try {
		Goods goods=(Goods)session.get(Goods.class, entity.getGoodsid());
		System.out.print(entity.getGoodsid());
		GoodsImage goodsImage = new GoodsImage();
		goodsImage.setImgpath(imgpath);
		goods.getGoodsimages().add(goodsImage);
		session.save(goods);
	
		
		
		session.getTransaction().commit();
		
			return true;
		}catch(HibernateException e) {
			System.out.println(e);
			return false;
		}
	}*/
	@Override
	public boolean updateImgpath(Integer imgid, String imgpath) {
		Session session=getCurrentSession();
		try {
			GoodsImage goodsImage = (GoodsImage)session.get(GoodsImage.class, imgid);
			goodsImage.setImgpath(imgpath);
			return true;
		} catch (HibernateException e) {
			return false;
		}
	}
	@Override
	public List<Goods> findGoodsBySearchKey(String searchkey) {
		//Criteria criteria = getCurrentSession().createCriteria(Goods.class);
		//List<Goods> goodsList = criteria.add(Restrictions.like("goodsname", searchkey)).list();
		//return goodsList;
		  List<Goods> goodsList = (List<Goods>)getCurrentSession().createQuery("from Goods where goodsname like :goodsname")
                  .setParameter("goodsname", "%"+searchkey+"%")
                  .list();
		  return goodsList;
	}
	@Override
	public boolean updateGoodsWithpriceAndgoodsname(Goods entity) {
		
		Session session=getCurrentSession();
		try {
		Goods goods=(Goods)session.load(Goods.class, entity.getGoodsid());
		goods.setGoodsname(entity.getGoodsname());
		goods.setPrice(entity.getPrice());
		return true;
		}catch(HibernateException e) {
			return false;
		}
	}



	

	
}
