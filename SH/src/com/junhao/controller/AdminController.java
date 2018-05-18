package com.junhao.controller;


import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.junhao.domain.Admin;
import com.junhao.domain.User;
import com.junhao.domain.Validate;
import com.junhao.service.OrderService;
import com.junhao.service.UserService;

@Controller
public class AdminController {    //用户控制器

	@Autowired
	private UserService userService; 
	
	@RequestMapping(value="adminlogin")  //处理login请求
    public  ModelAndView login(HttpSession session,HttpServletRequest request){
    	
		ModelAndView mv = new ModelAndView();
		String adminname = request.getParameter("adminname");
    	String adminpassword = request.getParameter("adminpassword");
		Admin admin = userService.adminlogin(adminname, adminpassword); 
         //调用业务层方法返回一个实例对象
        if (admin!=null) {  //判断查到的数据是否为空
            //如果用户不为空，设在Session域中
        	
            session.setAttribute("admin", admin);
           
            mv.setView(new RedirectView("goods/list")); //重定向到main页面中
            
           
        }else {  
        	
            mv.addObject("message","登录名或者密码错误");
            mv.setViewName("adminloginForm"); //重新设置view视图页面                  
        }  
      
        return mv; //返回视图
    }
  
}