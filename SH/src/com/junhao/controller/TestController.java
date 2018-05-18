package com.junhao.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.util.WebUtils;

import com.junhao.domain.User;
import com.junhao.domain.XmlTest;

@Controller
public class TestController {
	
	//使用Servlet API作为入参
	@RequestMapping(value="test1")
	public void test1(HttpServletRequest request,
			HttpServletResponse response) {
			String username = WebUtils.findParameterValue(request, "username");
			response.addCookie(new Cookie("username", username));
			
	}
	
	//返回图片
	/*@ResponseBody
	@RequestMapping(value="img")
	public byte[] img() throws IOException {
		
		//获取ContextServlet对象
		ServletContext application=ContextLoader.getCurrentWebApplicationContext().getServletContext();
		Resource resource = new ServletContextResource(application,"/WEB-INF/resource/images/buwawa1.jpg");
		//Resource resource = new ClassPathResource("buwawa1.jpg");
		byte[] fileData = FileCopyUtils.copyToByteArray(resource.getInputStream());
		return fileData;
	}*/
	
	//接受json字符串并返回数据
	@RequestMapping(value="sendJson")
	@ResponseBody
	public String sendJson(@RequestBody User user) {
		System.out.println(user.getUsername()+user.getPassword());
		return "接收成功";
	}
	
	//输出xml测试
	/*@RequestMapping(value="xml/{name}",produces = "application/xml")
	@ResponseBody
	public XmlTest xmlTest(@PathVariable String name) {
	
		return new XmlTest(name, 1200);
	}*/
	
}
