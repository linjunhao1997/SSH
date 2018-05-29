package com.junhao.domain;

import java.util.HashSet;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="t_goods")
public class Goods {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer goodsid;
	
	@Pattern(regexp="^[^><&#]{1,50}$",message="{pattern}")
	@NotNull(message="{notNull}")
	
	@Column(length=50)
	private String goodsname;
	
	@OneToMany(mappedBy="goods")
	private Set<GoodsImage> goodsimages = new HashSet<>(); //这里要实例化，不然会报空指针
	
	@Min(value=1,message="必须大于或等于1")
	
	@Column
	private Double price;
	
	@Column
	private Integer quantity;
	
	@Column(length=100)
	private String introduce;
	//@OneToOne(mappedBy="goods",fetch = FetchType.EAGER)
	
	@OneToOne(mappedBy="goods")
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
