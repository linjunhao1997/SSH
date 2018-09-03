package com.junhao.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_goods")
public class Goods {
	
	@Id
	@GeneratedValue(generator ="paymentableGenerator")       
	@GenericGenerator(name ="paymentableGenerator", strategy ="identity")
	private int goodsid;
	
	@Pattern(regexp="^[^><&#]{1,50}$",message="{pattern}")
	@NotNull(message="{notNull}")
	
	@Column(length=50)
	private String goodsname;
	
	@OneToMany(mappedBy="goods",targetEntity=GoodsImage.class) //去掉这个mappedBy后不能取到关联的表的内容mappedBy相当于inverse=true
	@Cascade(value= {org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	private Set<GoodsImage> goodsImages = new HashSet<>();
	
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
	
	
	public int getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(int goodsid) {
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
	
	public Set<GoodsImage> getGoodsImages() {
		return goodsImages;
	}


	public void addGoodsGoodsImage(GoodsImage goodsImage) {  
        goodsImage.setGoods(this);  //关键
        this.goodsImages.add(goodsImage);  
    } 
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	
}
