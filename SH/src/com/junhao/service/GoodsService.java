package com.junhao.service;

import java.util.List;

import com.junhao.domain.Goods;


public interface GoodsService {

	//展示商品
	List<Goods> listGoodses();
	
	//分页
	List<Goods> listGoodses(int index, int row);
	
	//获取商品总数
	int countGoods();
	
	//删除物品
	boolean deleteGoods(int goodsid);
	
	//添加新的商品记录
	int insertGoods(Goods entity);
	
	//通过商品id获取具体的商品
	Goods getGoods(int goodsid);

	//上传商品图片
	boolean updateGoodsImage(Integer imgid , String imgpath);
	
	//查询商品
	List<Goods> listGoodses(String searchkey);
	
	//更新物品信息
	boolean updateGoods(Goods entity);

}
