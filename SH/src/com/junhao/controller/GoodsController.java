package com.junhao.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.junhao.domain.Goods;
import com.junhao.domain.GoodsImage;
import com.junhao.service.GoodsService;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	
	@Resource
	GoodsService goodsService;
	
	/*
	 * 产品列表与分页Action
	 */
	@RequestMapping("/list")
	public String list(Model model,@RequestParam(required=false,defaultValue="0") int pageNO){
		int size=5;
		model.addAttribute("size",size);
		model.addAttribute("pageNO",pageNO);
		model.addAttribute("count",goodsService.getGoodsCount());
		int index = (pageNO-1)*size;
		model.addAttribute("goods", goodsService.showGoodsByPage(index,size));
		return "managePage";
	}
	
	/*
	 * 删除单个产品对象Action
	 */
	@RequestMapping("/delete/{goodsid}")
	public String delete(Model model,@PathVariable int goodsid,@RequestParam(required=false,defaultValue="1") int pageNO,RedirectAttributes redirectAttributes){
		if(goodsService.deleteGoods(goodsid))
		{
			redirectAttributes.addFlashAttribute("successmessage", "删除成功！");
		}else{
			redirectAttributes.addFlashAttribute("faildmessage", "删除失败！");
		}
		return "redirect:/goods/list?pageNO="+pageNO;
	}
	
	/*
	 * 删除多个产品对象Action
	 
	@RequestMapping("/deletes")
	public String deletes(Model model,@RequestParam int[] goodsid,@RequestParam(required=false,defaultValue="1") int pageNO,RedirectAttributes redirectAttributes){
		//执行删除
		int rows=goodsService.deleteSomeGoods(goodsid);
		if(rows>0)
		{
			redirectAttributes.addFlashAttribute("message", "删除"+rows+"行记录成功！");
		}else{
			redirectAttributes.addFlashAttribute("message", "删除失败！");
		}
		return "redirect:/goods/list?pageNO="+pageNO;
	}
	*/
	
	/*
	 * 添加商品
	 */
	@RequestMapping("/add")
	public String add(Model model){
		model.addAttribute("entity", new Goods());
		return "addPage";
	}
	
	/*
	 * 添加商品保存
	 */
	@RequestMapping("/addSave")
	public String addSave(Model model,@ModelAttribute("entity") @Valid Goods entity,BindingResult bindingResult){
		//如果模型中存在错误
		if(!bindingResult.hasErrors()){
			if(goodsService.insertGoods(entity)>0)
			{
				return "redirect:list";	
			}
		}
		model.addAttribute("entity", entity);
		return "addPage";
	}
	
	/*
	 * 编辑商品
	 */
	@RequestMapping("/edit/{goodsid}")
	public String edit(Model model,@PathVariable int goodsid){
		Goods entity=goodsService.getGoodsById(goodsid);
		System.out.println(entity.getGoodsname());
		model.addAttribute("entity", entity);
		return "editPage";
	}
	
	/*
	 * 编辑商品保存
	 */
	@RequestMapping("/editSave")
	public String editSave(Model model,@ModelAttribute("entity") @Valid Goods entity,BindingResult bindingResult){
		//如果模型中存在错误
		System.out.println(entity.getGoodsid());
		if(!bindingResult.hasErrors()){
			if(goodsService.updateGoods(entity))
			{
				return "redirect:list";	
			}
		}
		model.addAttribute("entity", entity);
		return "editPage";
	}
	
	/**
	 * 上传图片
	 */
	@RequestMapping("/upPicture/{id}")
	public String upPicture(Model model,@PathVariable int id){
		Goods entity=goodsService.getGoodsById(id);
		model.addAttribute("entity", entity);
		System.out.print(entity.getGoodsname());
		return "upfilePage";
	}
	
	/*
	 * 上传图片保存
	 */
	@RequestMapping("/upPictureSave/{imgid}")
	public String upPictureSave(Model model,@PathVariable("imgid") Integer imgid,MultipartFile picFile,HttpServletRequest request){
		//如果选择了文件
		if(picFile!=null){ 
			//如果文件大小不为0
			if(picFile.getSize()>0){
				//获得上传位置
				String path=request.getServletContext().getRealPath("/WEB-INF/resource/images");
				//String path="E:\\eclipse-workspace\\SH\\WebContent\\WEB-INF\\resource\\images";
				//生成文件名
				String imgpath=UUID.randomUUID().toString()+picFile.getOriginalFilename().substring(picFile.getOriginalFilename().lastIndexOf("."));
				File tempFile=new File(path, imgpath);
				try {
					//保存文件
					picFile.transferTo(tempFile);
					//更新数据
					//Set<GoodsImage> goodsImages = new HashSet<GoodsImage>();
					
					
					//entity.setGoodsimages(goodsImages);
					goodsService.uploadGoodsImage(imgid,imgpath);
					//转向列表页
					return "redirect:/goods/list";	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		Goods goods =(Goods) goodsService.showGoods();
		model.addAttribute("entity", goods);
		return "upfilePage";
	}
	
	
	
	
	
}
