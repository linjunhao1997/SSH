package com.junhao.controller;



import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.junhao.domain.Order;
import com.junhao.domain.User;
import com.junhao.service.OrderService;

@Controller		//视图控制器
public class CartController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="goToCart")
	public String goToCart(HttpSession session,Model model) {
		User user = (User)session.getAttribute("user");
		Integer id = user.getId();
		List<Order> orders = orderService.showOrder(id);
		for(Order order:orders) {
			System.out.println(order.getPrice());
		}
		model.addAttribute("orders",orders);
		
		return "cartForm";
	}
	
	
	
	@RequestMapping(value="deleteOrder")
	public @ResponseBody String goToCart(Model model,@RequestBody Order order) {
		
		if(orderService.deleteOrder(order)) {
			return "删除订单成功";
		}
		else {
			return "删除订单失败";
		}
		
	}

}