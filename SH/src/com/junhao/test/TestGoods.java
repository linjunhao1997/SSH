package com.junhao.test;


import java.util.Iterator;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.junhao.domain.Goods;
import com.junhao.domain.GoodsImage;
import com.junhao.domain.User;
import com.junhao.service.GoodsService;
import com.junhao.service.UserService;



@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations="classpath:applicationContext.xml") 
public class TestGoods {
	
	@Resource
	private GoodsService goodsService;
	@Test
	public void demoTest() {
	
		System.out.print(goodsService.showGoods().get(0).getGoodsname());
		
		
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
	
		System.out.print(goodsService.getGoodsCount());
		
		
	}
	
	@Test
	public void demoTest4() {
	
		System.out.print(goodsService.showGoodsByPage(0, 1).get(0).getGoodsname());
		
		
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
	
}
