package com.junhao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.junhao.domain.Goods;
import com.junhao.service.GoodsService;

@Controller		//视图控制器
public class FormController {

	@Autowired
	private GoodsService goodsService;
	
	@RequestMapping(value = "loginForm")

	public String loginForm() {
		// 动态跳转页面
		return "loginForm";
	}
	
	@RequestMapping(value = "adminloginForm")

	public String adminloginForm() {
		// 动态跳转页面
		return "adminloginForm";
	}
	
	
	
	@RequestMapping(value = "registerForm")

	public String registerForm() {
		// 动态跳转页面
		 
		return "registerForm";
	}
	
	//@RequestMapping(value = "mainPage")
	//public String mainPage(ModelAndView mv) {
	//	 动态跳转页面		 
	//	return "mainPage";
	//}
	
	@RequestMapping(value = "mainPage")
	public String mainPage(Model model,@RequestParam(required=false,defaultValue="1") int pageNo) {
		int row = 1;
		int count = goodsService.getGoodsCount();
		int pageNum = (int) Math.ceil(count/row);
		
		int index = (pageNo-1)*row;
	
		List<Goods> goodsList = goodsService.showGoodsByPage(index, row);
		//System.out.println( goodsList.get(0).getGoodsname());
		model.addAttribute("pageNum",pageNum);
		model.addAttribute("goodsList",goodsList);
		
		int previousPageNo = pageNo-1;
		String previousPageNoString=Integer.toString(previousPageNo);
		if(previousPageNo>0) {
			model.addAttribute("previous","mainPage?pageNo="+previousPageNoString);
		}else {
			model.addAttribute("previous","#");
		}
		
		int nextPageNo = pageNo+1;
		String nextPageNoString = Integer.toString(nextPageNo);
		if(pageNo<pageNum) {
			model.addAttribute("next","mainPage?pageNo="+nextPageNoString);
		}else {
			model.addAttribute("next","#");
		}
		return "mainPage";
	}
	
	@RequestMapping(value="goToModifyPassword")
	public String gotoMP(){
		return "modifyPasswordPage";
	}
	
	
	
	



}