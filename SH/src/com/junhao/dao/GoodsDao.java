package com.junhao.dao;

import java.util.List;

import com.junhao.domain.Goods;

public interface GoodsDao {
	
	List<Goods> listGoodses();
	
	List<Goods> listGoodses(int index, int row);
	
	int countGoods();
	
	boolean deleteGoods(int id);
	
	//添加商品
	int insertGoods(Goods entity);
	
	Goods getGoods(int goodsid);
	
	boolean updateImagePath(Integer imgid, String imgpath);
	
	//搜索商品
	List<Goods> listGoodses(String searchkey);
	
	//更新商品信息
	boolean updateGoods(Goods goods);
	
}
