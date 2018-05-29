package com.junhao.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
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

@Controller
@RequestMapping("admin/goods")
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
	public String delete(Model model,
			@PathVariable int goodsid,
			@RequestParam(required=false,defaultValue="1") int pageNO,
			RedirectAttributes redirectAttributes,
			HttpServletRequest request){
		
		//目前只有一张图片，取第一个即可
		Iterator<GoodsImage> gImgSet = goodsService.getGoodsById(goodsid).getGoodsimages().iterator();
		
		try {
		if(gImgSet.hasNext()) {
			GoodsImage gImgPath = gImgSet.next();
			String imgPath = gImgPath.getImgpath();
			File localFile=new File("E:\\eclipse-workspace\\SH\\WebContent\\WEB-INF\\resource\\images",imgPath);
			File file = new File(request.getServletContext().getRealPath("/WEB-INF/resource/images"),imgPath);
			
			file.delete();
			localFile.delete();
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(goodsService.deleteGoods(goodsid))
		{
			redirectAttributes.addFlashAttribute("successmessage", "删除成功！");
			
		}else{
			redirectAttributes.addFlashAttribute("faildmessage", "删除失败！");
		}
		return "redirect:/admin/goods/list?pageNO="+pageNO;
	}
	
	
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
	public String upPictureSave(Model model,@PathVariable Integer imgid,MultipartFile picFile,HttpServletRequest request){
		//如果选择了文件
		if(picFile!=null){ 
			//如果文件大小不为0
			if(picFile.getSize()>0){
				//获得上传位置
				String path=request.getServletContext().getRealPath("/WEB-INF/resource/images");
				String localPath="E:\\eclipse-workspace\\SH\\WebContent\\WEB-INF\\resource\\images";
				//生成文件名
				String imgPath=UUID.randomUUID().toString()+picFile.getOriginalFilename().substring(picFile.getOriginalFilename().lastIndexOf("."));
				
				File localFile = new File(localPath,imgPath);
				File tempFile=new File(path, imgPath);
				try {
					//保存文件
					picFile.transferTo(tempFile);
					//保存到本地
					InputStream in = new FileInputStream(tempFile);
					OutputStream out = new FileOutputStream(localFile);
					byte[] buff = new byte[2048];
					int len =0;
					while((len=in.read(buff))!=-1) {
						out.write(buff,0,len);
					}
					
					in.close();
					out.close();
					//更新数据
					//Set<GoodsImage> goodsImages = new HashSet<GoodsImage>();
					
					
					//entity.setGoodsimages(goodsImages);
					goodsService.uploadGoodsImage(imgid,imgPath);
					//转向列表页
					return "redirect:/admin/goods/list";	
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
