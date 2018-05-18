package com.junhao.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.junhao.domain.Goods;
import com.junhao.domain.Order;
import com.junhao.domain.User;
import com.junhao.service.GoodsService;
import com.junhao.service.OrderService;

@Controller		//视图控制器
public class CartController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="goToCart")
	public String goToCart(HttpSession session,Model model) {
		User user = (User)session.getAttribute("user");
		Integer id = user.getId();
		model.addAttribute("orders",orderService.showOrder(id));
		
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