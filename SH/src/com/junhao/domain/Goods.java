package com.junhao.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Goods {
	private Integer goodsid;
	
	@Pattern(regexp="^[^><&#]{1,50}$",message="{pattern}")
	@NotNull(message="{notNull}")
	private String goodsname;
	
	private Set<GoodsImage> goodsimages=new HashSet<GoodsImage>();
	
	@Min(value=1,message="必须大于或等于1")
	private Double price;
	private Integer quantity;
	private String introduce;
	//@OneToOne(mappedBy="goods",fetch = FetchType.EAGER)
	
	private Order order;
	public Integer getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Set<GoodsImage> getGoodsimages() {
		return goodsimages;
	}
	public void setGoodsimages(Set<GoodsImage> goodsimages) {
		this.goodsimages = goodsimages;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	
}
