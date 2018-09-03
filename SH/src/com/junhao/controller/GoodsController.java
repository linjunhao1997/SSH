package com.junhao.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.junhao.domain.Goods;
import com.junhao.domain.GoodsImage;
import com.junhao.service.GoodsService;
/**
 *@author junhao
 *管理员处理商品的控制器
 */
/**
 * <p>Description:</p>
 * @author junhao
 * @date 2018年7月30日 上午11:16:33
 */
@Controller
@RequestMapping("admin/goods")
public class GoodsController {
	@Resource
	GoodsService goodsService;

	
	/**
	 * @title:list
	 * @description:产品列表与分类URL
	 * @param model
	 * @param pageNO
	 * @return
	 * String
	 * 
	 */
	@RequestMapping("/list")
	public String list(Model model, @RequestParam(required=false,defaultValue="0") int pageNO) {
		int size = 5;
		model.addAttribute("size", size);
		model.addAttribute("pageNO", pageNO);
		model.addAttribute("count",goodsService.countGoods());
		int index = (pageNO - 1) * size;
		model.addAttribute("goods", goodsService.listGoodses(index, size));
		return "managePage";
	}
	
	/**
	 * @title:delete
	 * @description:处理删除商品的请求
	 * @param model
	 * @param goodsid
	 * @param pageNO
	 * @param redirectAttributes
	 * @param request
	 * @return
	 * String
	 * 
	 */
	@RequestMapping("/delete/{goodsid}")
	public String delete(Model model, @PathVariable int goodsid, 
        @RequestParam(required = false,defaultValue = "1") int pageNO, RedirectAttributes redirectAttributes, HttpServletRequest request){
		
		// 目前只有一张图片，取第一个即可
		Iterator<GoodsImage> gImgSet = goodsService.getGoods(goodsid).getGoodsImages().iterator();
		try {
		    if(gImgSet.hasNext()) {
	            GoodsImage gImgPath = gImgSet.next();
			    String imgPath = gImgPath.getImgpath();
			    File localFile = new File("E:\\eclipse-workspace\\SH\\WebContent\\WEB-INF\\resource\\images", imgPath);
			    File file = new File(request.getServletContext().getRealPath("/WEB-INF/resource/images"), imgPath);
			
			    file.delete();
			    localFile.delete();
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (goodsService.deleteGoods(goodsid)) {
			redirectAttributes.addFlashAttribute("successmessage", "删除成功！");
			
		} else {
			redirectAttributes.addFlashAttribute("faildmessage", "删除失败！");
		}
		return "redirect:/admin/goods/list?pageNO=" + pageNO;
	}
	
	
	
	/**
	 * @title:add
	 * @description:添加商品操作
	 * @param model
	 * @return
	 * String
	 * 
	 */
	@RequestMapping("/add")
	public String add(Model model){
		model.addAttribute("entity", new Goods());
		return "addPage";
	}
	
	
	/**
	 * @title:addSave
	 * @description:添加商品保存操作
	 * @param model
	 * @param entity
	 * @param bindingResult
	 * @return
	 * String
	 * 
	 */
	@RequestMapping("/addSave")
	public String addSave(Model model, @ModelAttribute("entity") @Valid Goods entity, BindingResult bindingResult) {
		//如果模型中存在错误
		if (!bindingResult.hasErrors()) {
			if (goodsService.insertGoods(entity) > 0) {
				return "redirect:list";	
			}
		}
		model.addAttribute("entity", entity);
		return "addPage";
	}
	
	
	/**
	 * @title:edit
	 * @description:编辑商品操作
	 * @param model
	 * @param goodsid
	 * @return
	 * String
	 * 
	 */
	@RequestMapping("/edit/{goodsid}")
	public String edit(Model model, @PathVariable int goodsid) {
		Goods entity = goodsService.getGoods(goodsid);
		System.out.println(entity.getGoodsname());
		model.addAttribute("entity", entity);
		return "editPage";
	}
	
	
	/**
	 * @title:editSave
	 * @description:编辑商品保存操作
	 * @param model
	 * @param entity
	 * @param bindingResult
	 * @return
	 * String
	 * 
	 */
	@RequestMapping("/editSave")
	public String editSave(Model model,@ModelAttribute("entity") @Valid Goods entity,BindingResult bindingResult){
		// 如果模型中存在错误
		System.out.println(entity.getGoodsid());
		// 提示的错误可在资源文件设置
		if (!bindingResult.hasErrors()) {
			if (goodsService.updateGoods(entity)) {
				return "redirect:list";	
			}
		}
		model.addAttribute("entity", entity);
		return "editPage";
	}
	
	
	/**
	 * @title:upPicture
	 * @description:上传图片操作
	 * @param model
	 * @param id
	 * @return
	 * String
	 * 
	 */
	@RequestMapping("/upPicture/{id}")
	public String upPicture(Model model, @PathVariable int id){
		Goods entity = goodsService.getGoods(id);
		model.addAttribute("entity", entity);
		return "upfilePage";
	}
	
	
	/**
	 * @title:upPictureSave
	 * @description:上传图片保存操作
	 * @param model
	 * @param goodsid
	 * @param file
	 * @param request
	 * @return
	 * String
	 */
	@RequestMapping("/upPictureSave/{goodsid}")
	public String upPictureSave(Model model, @PathVariable int goodsid, MultipartFile file, HttpServletRequest request) {
		
		// 如果选择了文件
		if (file != null) { 
			// 如果文件大小不为0
			if (file.getSize() > 0) {
				// 获得上传位置
				String imageTempPath = request.getServletContext().getRealPath("/WEB-INF/resource/images");
				String imageLocalPath = "E:\\eclipse-workspace\\SH\\WebContent\\WEB-INF\\resource\\images";
				// 生成文件名
				String imagePath = UUID.randomUUID().toString() + file.getOriginalFilename()
				    .substring(file.getOriginalFilename().lastIndexOf("."));
				
				File tempFile = new File(imageTempPath, imagePath);
				File localFile = new File(imageLocalPath, imagePath);
				try {
					// 保存到临时目录
					file.transferTo(tempFile);
				
					// 保存到本地
					InputStream in = new FileInputStream(tempFile);
					OutputStream out = new FileOutputStream(localFile);
					byte[] buff = new byte[2048];
					int len =0;
					while ((len = in.read(buff)) != -1) {
						out.write(buff, 0, len);
					}
					in.close();
					out.close();
					goodsService.updateGoodsImage(goodsid, imagePath);
					//转向列表页
					return "redirect:/admin/goods/list";	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		Goods goods = goodsService.getGoods(goodsid);
		model.addAttribute("entity", goods);
		return "upfilePage";
	}

}
