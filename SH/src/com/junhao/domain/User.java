package com.junhao.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="t_user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message="登录名不能为空")
	@Size(min=2,max=20,message="长度{min}-{max}")
	
	@Column
	private String username;
	
	@NotBlank(message="密码不可为空")
	@Length(min=6,max=15,message="密码长度在{min}-{max}")
	@Column
	private String password;
	
	@NotEmpty(message="手机号码不可为空")
	@Pattern(regexp="^1[34578]\\d{9}$",message="手机号码格式不正确")
	@Column
	private String phone;
	
	@Email(message="邮箱格式错误，eg:xxx@xx.com")
	@Column
	private String email;
	
	//用户有多个订单
	@OneToMany(mappedBy="user") //如果把这个mapperBy忘了，我就不能从主表查到从表的数据
	private List<Order> orders = new ArrayList<>();
	
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
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	
}
