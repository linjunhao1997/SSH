package com.junhao.domain;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;



public class User {
	private Integer id;
	@NotBlank(message="登录名不能为空")
	@Size(min=2,max=20,message="长度{min}-{max}")
	private String username;
	
	@NotBlank(message="密码不可为空")
	@Length(min=6,max=15,message="密码长度在{min}-{max}")
	private String password;
	
	@NotEmpty(message="手机号码不可为空")
	@Pattern(regexp="^1[34578]\\d{9}$",message="手机号码格式不正确")
	private String phone;
	
	@Email(message="邮箱格式错误，eg:xxx@xx.com")
	private String email;
	
	//用户有多个订单
	private Set<Order> orders = new HashSet<>();
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Order> getOrders() {
		return orders;
	}
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	
}
