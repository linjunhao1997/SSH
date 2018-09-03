package com.junhao.service.impl;



import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.junhao.dao.GoodsDao;
import com.junhao.dao.UserDao;
import com.junhao.domain.Goods;
import com.junhao.domain.User;
import com.junhao.service.GoodsService;
import com.junhao.service.UserService;

@Transactional
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	
	private GoodsDao goodsDao;

	@Override
	public List<Goods> listGoodses() {
		return goodsDao.listGoodses();
	}

	@Override
	public List<Goods> listGoodses(int index, int row) {
		return this.goodsDao.listGoodses(index, row);
	}

	@Override
	public int countGoods() {
		return this.goodsDao.countGoods();
	}

	@Override
	public boolean  deleteGoods(int goodsid) {
		return this.goodsDao.deleteGoods(goodsid);
	}



	@Override
	public int insertGoods(Goods entity) {
		return this.goodsDao.insertGoods(entity);
	}

	@Override
	public Goods getGoods(int goodsid) {
		return this.goodsDao.getGoods(goodsid);
	}

	@Override
	public boolean updateGoodsImage(Integer imgid,String imgpath) {
		return this.goodsDao.updateImagePath(imgid,imgpath);
	}

	@Override
	public List<Goods> listGoodses(String searchkey) {
		
		return this.goodsDao.listGoodses(searchkey);
	}

	@Override
	public boolean updateGoods(Goods entity) {
		return this.goodsDao.updateGoods(entity);
	}	

}
