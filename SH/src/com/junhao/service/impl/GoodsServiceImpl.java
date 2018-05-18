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
	public List<Goods> showGoods() {
		return goodsDao.findGoods();
	}

	@Override
	public List<Goods> showGoodsByPage(int index, int row) {
		return this.goodsDao.findGoodsByPageNum(index, row);
	}

	@Override
	public int getGoodsCount() {
		return this.goodsDao.findGoodsCount();
	}

	@Override
	public boolean  deleteGoods(int goodsid) {
		return this.goodsDao.delete(goodsid);
	}



	@Override
	public int insertGoods(Goods entity) {
		return this.goodsDao.insertGoods(entity);
	}

	@Override
	public Goods getGoodsById(int goodsid) {
		return this.goodsDao.findGoodsById(goodsid);
	}

	@Override
	public boolean uploadGoodsImage(Integer imgid,String imgpath) {
		return this.goodsDao.updateImgpath(imgid,imgpath);
	}

	@Override
	public List<Goods> searchGoods(String searchkey) {
		
		return this.goodsDao.findGoodsBySearchKey(searchkey);
	}

	@Override
	public boolean updateGoods(Goods entity) {
		return this.goodsDao.updateGoodsWithpriceAndgoodsname(entity);
	}	

}
