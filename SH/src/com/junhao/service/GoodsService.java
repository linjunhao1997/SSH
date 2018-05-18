package com.junhao.service;

import java.util.List;

import com.junhao.domain.Goods;


public interface GoodsService {

	//展示商品
	public List<Goods> showGoods();
	
	//分页
	public List<Goods> showGoodsByPage(int index, int row);
	
	//获取商品总数
	public int getGoodsCount();
	
	//删除物品
	public boolean deleteGoods(int goodsid);
	
	//添加新的商品记录
	public int insertGoods(Goods entity);
	
	//通过商品id获取具体的商品
	public Goods getGoodsById(int goodsid);

	//上传商品图片
	public boolean uploadGoodsImage(Integer imgid ,String imgpath);
	
	//查询商品
	public List<Goods> searchGoods(String searchkey);
	
	//更新物品信息
	public boolean updateGoods(Goods entity);

}
