package com.junhao.controller;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.junhao.domain.Admin;
import com.junhao.domain.Goods;
import com.junhao.domain.User;
import com.junhao.domain.Validate;
import com.junhao.service.GoodsService;
import com.junhao.service.OrderService;
import com.junhao.service.UserService;


/**
 * <p>Description:处理用户一般操作的控制器</p>
 * @author junhao
 * @date 2018年7月30日 上午11:19:22
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService; 
    @Autowired
    private OrderService orderService;
    @Autowired
    private GoodsService goodsService;
   
	/**
	 * @title:login
	 * @description:处理普通用户登录请求
	 * @param session
	 * @param request
	 * @param ra
	 * @return
	 * ModelAndView
	 */
	@RequestMapping(value="login")
    public  ModelAndView login(@CookieValue(value = "username", required = false) String usernameCookie, 
    	HttpSession session, HttpServletRequest request, HttpServletResponse response, RedirectAttributes ra){
		User user = null;
		ModelAndView mv = new ModelAndView();
		if (usernameCookie != null) {
			user = userService.getUser(usernameCookie);	
		} else {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		user = userService.getUser(username, password);
		}
		// 获取用户对象
		String autoLogin = request.getParameter("autoLogin");
		if (autoLogin != null) {
			Cookie addCookieAutoCookie = new Cookie("autoLogin", "true");
			Cookie addCookieusernameCookie = new Cookie("username", user.getUsername());
			addCookieAutoCookie.setMaxAge(7 * 24 * 60 * 60);
			addCookieusernameCookie.setMaxAge(7 * 24 * 60 * 60);
			response.addCookie(addCookieusernameCookie);
			response.addCookie(addCookieAutoCookie);
		}
		 if (user != null) {
	            session.setAttribute("user", user);
	            mv.setView(new RedirectView("mainPage"));            
	        } else {  
	        	ra.addFlashAttribute("message","登录名或密码错误");
	            mv.setViewName("redirect:loginForm");                 
	        }  
			
        return mv;
    }
	
	
	/**
	 * @title:adminLogin
	 * @description:处理管理员登录请求
	 * @param session
	 * @param request
	 * @param ra
	 * @return
	 * ModelAndView
	 */
	@RequestMapping(value = "adminlogin")  
    public  ModelAndView adminLogin(HttpSession session, HttpServletRequest request, RedirectAttributes ra){
		ModelAndView mv = new ModelAndView();
		String adminname = request.getParameter("adminname");
    	String adminpassword = request.getParameter("adminpassword");
		Admin admin = userService.getAdmin(adminname, adminpassword); 
        if (admin != null) {
            session.setAttribute("admin", admin);
            // 重定向URL:admin/goods/list
            mv.setView(new RedirectView("admin/goods/list"));            
        } else {  
        	ra.addFlashAttribute("message","登录名或密码错误");
            mv.setViewName("redirect:adminloginForm");                 
        }  
        return mv;
    }
	
	/**
	 * @title:mainPage
	 * @description:用户主页面的显示请求URL，返回model到mainPage.jsp视图
	 * @param model
	 * @param pageNo
	 * @return
	 * String
	 */
	@RequestMapping(value = "mainPage")
	public String mainPage(Model model, @RequestParam(required = false,defaultValue = "1") int pageNo) {
		int row = 1;
		int count = goodsService.countGoods();
		int pageNum = (int) Math.ceil(count/row);
		int index = (pageNo-1)*row;
	
		List<Goods> goodsList = goodsService.listGoodses(index, row);
		
		model.addAttribute("pageNum",pageNum);
		model.addAttribute("goodsList",goodsList);
		
		int previousPageNo = pageNo - 1;
		String previousPageNoString = Integer.toString(previousPageNo);
		if (previousPageNo > 0) {
			model.addAttribute("previous","mainPage?pageNo=" + previousPageNoString);
		} else {
			model.addAttribute("previous","#");
		}
		
		int nextPageNo = pageNo + 1;
		String nextPageNoString = Integer.toString(nextPageNo);
		if (pageNo < pageNum) {
			model.addAttribute("next","mainPage?pageNo=" + nextPageNoString);
		} else {
			model.addAttribute("next","#");
		}
		return "mainPage";
	}
	
	@RequestMapping("/action/validate") // 用json的传入和传出实现ajax
	public @ResponseBody Validate validate(@RequestBody User user) {

		boolean isexist = userService.existUsername(user.getUsername());

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
    
	
    /**
     * @title:register
     * @description:用户注册请求
     * @param username
     * @param password
     * @param phone
     * @param email
     * @param user
     * @param br
     * @param mv
     * @return
     * ModelAndView
     */
    @RequestMapping(value = "/register") 
    public ModelAndView register(@RequestParam String username, @RequestParam String password, @RequestParam String phone, 
        @RequestParam String email, @Validated User user, BindingResult br, ModelAndView mv) {
    	
    	int errorCount = br.getErrorCount();
    	if(errorCount > 0) {
    		FieldError loginnameError = br.getFieldError("username");
    		FieldError passwordError = br.getFieldError("password");
    		FieldError phoneError = br.getFieldError("phone");
    		FieldError emailError = br.getFieldError("email");
    		
    		// 用户名格式错误
    		if (loginnameError != null) {
    			String lnEmsg = loginnameError.getDefaultMessage();
    			mv.addObject("lnEmsg", lnEmsg);
    		}
    		// 密码格式错误
    		if (passwordError != null) {
    			String pwEmsg = passwordError.getDefaultMessage();
    			mv.addObject("pwEmsg", pwEmsg);
    			
    		}
    		// 手机号码格式错误
    		if (phoneError != null) {
    			String pEmsg = phoneError.getDefaultMessage();
    			mv.addObject("pEmsg", pEmsg);
    			
    		}
    		// 邮箱格式错误
    		if (emailError!=null) {
    			String eEmsg = emailError.getDefaultMessage();
    			mv.addObject("eEmsg", eEmsg);
    			
    		}
    		
    		mv.setViewName("registerForm");
    		return mv;
    		
    	} else {
    		userService.insertUser(username, password, phone, email);
    		mv.addObject("messge", "注册成功" );
    		mv.setViewName("success");
    		return mv;
    	}  		
    }
    
    
    /**
     * @title:addToCart
     * @description:商品加入购物车请求
     * @param session
     * @param goodsid
     * @return
     * String
     */
    @RequestMapping("addToCart/goodsid/{goodsid}")
    public String addToCart(HttpSession session, @PathVariable("goodsid") Integer goodsid) {	
    	User user = (User)session.getAttribute("user");
		orderService.insertOrder(user, goodsid);
		// return new ModelAndView("redirect:/mainPage");
    	return "redirect:/mainPage";
    }
    
    /**
     * @title:modifyPassword
     * @description:修改密码请求
     * @param session
     * @param response
     * @param map
     * @return
     * @throws IOException
     * String
     */
    @RequestMapping(value="modifyPassword")
    public @ResponseBody String modifyPassword(HttpSession session, HttpServletResponse response, 
        @RequestBody Map<String, String> map) throws IOException {
    	User user = (User)session.getAttribute("user");
    	if (!user.getPassword().equals(map.get("oldpassword"))) {
    		System.out.println(map.get("oldpassword"));
    		return "旧密码错误"; 
    	}
    	else {
	    	if (!map.get("newpassword").equals(map.get("confirmpassword"))) {
	    		System.out.println(map.get("newpassword"));System.out.println(map.get("confirmpassword"));
	    		return "新密码错误";
	    	} else {
	    		if (userService.updateUserPassword(user,map.get("confirmpassword"))) {
	    			return "修改成功";
	    		} else {
					return "数据库出错，修改失败";
				}
	    	}
    	}
    }
    
 
    /**
     * @title:quit
     * @description:注销请求
     * @param session
     * @return
     * String
     */
    @RequestMapping(value = "quit")
    public String quit(HttpSession session) {
    	session.removeAttribute("user");
    	return "loginForm";
    }
    
    
    /**
     * @title:search
     * @description:关键字搜索请求
     * @param model
     * @param searchkey
     * @return
     * String
     */
    @RequestMapping(value = "search")
    public String search(Model model,@RequestParam String searchkey) {
    	model.addAttribute("searchkey", searchkey);
    	model.addAttribute("goodsList", goodsService.listGoodses(searchkey));
    	return "mainPageSearch";
    }
}