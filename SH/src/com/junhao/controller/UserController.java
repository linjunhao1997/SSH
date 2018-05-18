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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.junhao.domain.User;
import com.junhao.domain.Validate;
import com.junhao.service.GoodsService;
import com.junhao.service.OrderService;
import com.junhao.service.UserService;

@Controller
public class UserController {    //用户控制器
    @Autowired
    private UserService userService; 
    @Autowired
    private OrderService orderService;
    @Autowired
    private GoodsService goodsService;
    
	@RequestMapping(value="login")  //处理login请求
    public  ModelAndView login(HttpSession session,HttpServletRequest request,RedirectAttributes ra){
    	
		ModelAndView mv = new ModelAndView();
		String username = request.getParameter("username");
    	String password = request.getParameter("password");
		User user = userService.login(username, password); 
         //调用业务层方法返回一个实例对象
        if (user!=null) {  //判断查到的数据是否为空
            //如果用户不为空，设在Session域中
        	
            session.setAttribute("user", user);
           
            mv.setView(new RedirectView("mainPage")); //重定向到main页面中
            
           
        }else {  
        	ra.addFlashAttribute("message","登录名或密码错误");
            //mv.addObject("message","登录名或者密码错误");
            mv.setViewName("redirect:loginForm"); //重新设置view视图页面                  
        }  
      
        return mv; //返回视图
    }
	
	@RequestMapping("/action/validate") // 用json的传入和传出实现ajax
	public @ResponseBody Validate validate(@RequestBody User user) {

		boolean isexist = userService.checkUserName(user.getUsername());

		Validate v = new Validate();
		System.out.println(isexist);

		if (!isexist) {
			v.setMessage("该用户名可用.");
			return v;
		}

		else {
			v.setMessage("该用名户名已被注册!");
			return v;
		}
	}
    
    
    @RequestMapping(value="/register")  //注册
    public ModelAndView register(
    		@RequestParam String username,
    		@RequestParam String password,
    		
    		@RequestParam String phone,
    		@RequestParam String email,
    		//@RequestParam String address,
    		@Validated User user,BindingResult br,
    		ModelAndView mv)
    {	 	
    	int errorCount = br.getErrorCount();
    	if(errorCount>0) {
    		FieldError loginnameError = br.getFieldError("username");
    		FieldError passwordError = br.getFieldError("password");
    		FieldError phoneError = br.getFieldError("phone");
    		FieldError emailError = br.getFieldError("email");
    		
    		if(loginnameError!=null) {
    			String lnEmsg = loginnameError.getDefaultMessage();
    			mv.addObject("lnEmsg", lnEmsg);
    			//用户名格式错误
    		}
    		
    		if(passwordError!=null) {
    			String pwEmsg = passwordError.getDefaultMessage();
    			mv.addObject("pwEmsg", pwEmsg);
    			//密码格式错误
    		}
    		
    		if(phoneError!=null) {
    			String pEmsg = phoneError.getDefaultMessage();
    			mv.addObject("pEmsg", pEmsg);
    			//手机号码格式错误
    		}
    		if(emailError!=null) {
    			String eEmsg = emailError.getDefaultMessage();
    			mv.addObject("eEmsg", eEmsg);
    			//邮箱格式错误
    		}
    		
    		mv.setViewName("registerForm");
    		return mv;
    		
    	}
    	else {
    		
    		userService.register(username, password, phone, email);
    		mv.addObject("messge", "注册成功" );
    		mv.setViewName("success");
    		return mv;
    	}  		
    }
    
    @RequestMapping("addToCart/goodsid/{goodsid}")
   
    public String addToCart(
    		HttpSession session,
    		@PathVariable("goodsid") Integer goodsid) {
    	
    	User user = (User)session.getAttribute("user");
		orderService.addOrder(user, goodsid);
		 //return new ModelAndView("redirect:/mainPage");
    	   return "redirect:/mainPage";
    }
    
    @RequestMapping(value="modifyPassword")
    public @ResponseBody String modifyPassword(HttpSession session,HttpServletResponse response,@RequestBody Map<String, String> map) throws IOException {
    	User user=(User)session.getAttribute("user");
    	if(!user.getPassword().equals(map.get("oldpassword"))) {
    		System.out.println(map.get("oldpassword"));
    		return "旧密码错误"; 
    	}
    	else {
	    	if(!map.get("newpassword").equals(map.get("confirmpassword"))) {
	    		System.out.println(map.get("newpassword"));System.out.println(map.get("confirmpassword"));
	    		return "新密码错误";
	    	}else {
	    		if(userService.modifyUserPassword(user,map.get("confirmpassword"))) {
	    			
	    			return "修改成功";
	    		}
	    		else {
					return "数据库出错，修改失败";
				}
	    	}
    	}
    	
    	
    }
    
    

		
    
    //注销
    @RequestMapping(value="quit")
    public String quit(HttpSession session) {
    	session.removeAttribute("user");
    	return "loginForm";
    }
    
    //搜索
   /* @RequestMapping(value="search")
    @ResponseBody
    public String search(Model model,@RequestBody Map<String,String> map) {
    	String searchkey = map.get("searchkey");
    	System.out.println(searchkey);
    	//model.addAttribute("goodsList", goodsService.searchGoods(searchkey));
    	return "/SH/resource/views/main.jsp";
    	
    }*/
    @RequestMapping(value="search")
    
   /* public String search(Model model,@RequestBody Map<String,String> map) {
    	String searchkey = map.get("searchkey");
    	System.out.println(searchkey);
    	model.addAttribute("goodsList", goodsService.searchGoods(searchkey));
    	return "redirect:mainPage";
    }*/
    public String search(Model model,@RequestParam String searchkey) {
    	System.out.println(searchkey);
    	model.addAttribute("searchkey", searchkey);
    	model.addAttribute("goodsList", goodsService.searchGoods(searchkey));
    	return "mainPageSearch";
    }
  
}