package com.junhao.test;


import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.junhao.domain.Auto;
import com.junhao.domain.Garage;
import com.junhao.domain.Goods;
import com.junhao.domain.GoodsImage;
import com.junhao.service.GoodsService;



@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations="classpath:applicationContext.xml") 
public class TestGoods {
	
	@Autowired
	SessionFactory sessionFactory;
	@Resource
	private GoodsService goodsService;
	@Test
	public void demoTest() {
	
		System.out.print(goodsService.listGoodses().get(0).getGoodsname());
		
		
	}
	
	
	
	
	/*@Test
	public void demoTest2() {
	
		Set<GoodsImage> goodsImageSet = goodsService.showGoodsByPage(0, 1).get(0).getGoodsimages();
		Iterator<GoodsImage> iterator = goodsImageSet.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next().getImgpath());
		}
		
		
	}*/
	
	@Test
	public void demoTest3() {
	
		System.out.print(goodsService.countGoods());
		
		
	}
	
	@Test
	public void demoTest4() {
	
		System.out.print(goodsService.listGoodses(0, 1).get(0).getGoodsname());
		
		
	}
	@Test
	public void demoTest5() {
		Goods entity = new Goods();
		entity.setGoodsname("布娃娃4");
		
		System.out.print(goodsService.insertGoods(entity));	
	}
	
	
	@Test
	public void demoTest6() {

	
	}
	@Test
	public void jpaTest() {
        //会话对象
        Session session =sessionFactory.openSession();
        //开启事务
        Transaction transaction =session.beginTransaction();
	      
			Goods goods = new Goods();
			goods.setGoodsname("终于");
			goods.setPrice(666.0);
			
			GoodsImage goodsImage1 = new GoodsImage();
			goodsImage1.setImgpath("hehe");
			
			
			goods.addGoodsGoodsImage(goodsImage1);
			session.save(goods);  
		    transaction.commit();  
		    session.close();  
		    sessionFactory.close();

	}
	@Test
	public void updateTest() {
        //会话对象
        Session session =sessionFactory.openSession();
        //开启事务
        Transaction transaction =session.beginTransaction();
	      
		    Goods goods = (Goods) session.load(Goods.class,36);
		    System.out.println(goods.getGoodsImages());  
		    goods.getGoodsImages().iterator().next().setImgpath("呵呵");
		    System.out.println(goods.getGoodsImages().iterator().next().getImgpath());
		    transaction.commit();  
		    session.close();  
		    sessionFactory.close();

	}
	
	@Test 
	public void save() {  
		  //会话对象
        Session session =sessionFactory.openSession();
        //开启事务
        Transaction transaction =session.beginTransaction();

        Garage garage = new Garage();  
        garage.setGaragenum("room1");  

        Auto auto1 = new Auto();  
        auto1.setAutonum("bj0000");  
        auto1.setAutotype("car");  

        Auto auto2 = new Auto();  
        auto2.setAutonum("bj1231");  
        auto2.setAutotype("bus");  

        garage.addGarageAuto(auto1);  
        garage.addGarageAuto(auto2);  

        session.save(garage);
        transaction.commit();  
	    session.close();  
	    sessionFactory.close(); 
    }  
	
	
	
}
