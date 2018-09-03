package com.junhao.controller;

import java.util.List;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.junhao.domain.Goods;
import com.junhao.service.GoodsService;

@Controller		//视图控制器
public class FormController {

	@Autowired
	private GoodsService goodsService;
	
	@RequestMapping(value = "loginForm")
	public String loginForm(Model model, @CookieValue(name = "autoLogin", required = false) String autoLogin) {
		// 动态跳转页面
		if(autoLogin != null &&	autoLogin.equals("true")) {
			return "redirect:login";
		}
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

	@RequestMapping(value="goToModifyPassword")
	public String gotoMP(){
		return "modifyPasswordPage";
	}
}