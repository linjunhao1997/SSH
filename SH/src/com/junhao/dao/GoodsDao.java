package com.junhao.dao;

import java.util.List;

import com.junhao.domain.Goods;

public interface GoodsDao {
	
	public List<Goods> findGoods();
	
	public List<Goods> findGoodsByPageNum(int index,int row);
	
	public int findGoodsCount();
	
	public boolean delete(int id);
	
	//添加商品
	public int insertGoods(Goods entity);
	
	public Goods findGoodsById(int goodsid);
	
	public boolean updateImgpath(Integer imgid,String imgpath);
	
	//搜索商品
	public List<Goods> findGoodsBySearchKey(String searchkey);
	
	//更新商品信息
	public boolean updateGoodsWithpriceAndgoodsname(Goods goods);
	
}
