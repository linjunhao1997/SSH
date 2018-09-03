package com.junhao.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_goodsimage")
public class GoodsImage {
	
	@Id
	@GeneratedValue(generator ="paymentableGenerator")       
	@GenericGenerator(name ="paymentableGenerator", strategy ="native") 
	private Integer imgid;
	
	@Column(length=50)
	private String imgpath;
	
	@ManyToOne
	@JoinColumn(name="gid")
	private Goods goods;
	
	public Integer getImgid() {
		return imgid;
	}
	public void setImgid(Integer imgid) {
		this.imgid = imgid;
	}
	public String getImgpath() {
		return imgpath;
	}
	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	
}
