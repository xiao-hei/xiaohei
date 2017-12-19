package com.baizhi.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baizhi.entity.Picture;
import com.baizhi.entity.PictureDto;
import com.baizhi.service.PictureService;

@Controller
@RequestMapping("/picture")
public class PictureController {
	@Resource(name="pictureService")
	PictureService ps;
	
	@RequestMapping("showAll")
	@ResponseBody
	public Object showAll(int page,int rows){
		/*PictureDto pd = new PictureDto();
		pd.setRows(ps.findByPage(page, rows));
		pd.setTotal(ps.findCount());*/
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows",ps.findByPage(page, rows));
		map.put("total",ps.findCount());
		return map;
	}
	@RequestMapping("uploadpicture")
	public void uploadpicture(MultipartHttpServletRequest mreq,HttpServletRequest req,HttpSession session,Picture p){
		System.out.println(p.getDescription());
		List<MultipartFile> files = mreq.getFiles("file1");
		for (MultipartFile multipartFile : files) {
			ps.addPicture(p, multipartFile, req, session);
		}
	}
	@RequestMapping("deletepicture")
	@ResponseBody
	public void deletepicture(String[] ids,String[] name,HttpSession session){
		String realPath = session.getServletContext().getRealPath("/");
		String  pt = new File(realPath).getParent();
		// 添加upload 在不同的操作系统中 分隔符是不一样的 File.separator 屏蔽操作系统差异
		for (String st : name) {
			String path = pt + File.separator + "img/"+ st;
			File file2 = new File(path);
			file2.delete();
		}
		ps.removePicture(ids);
	}
	@RequestMapping("selectById")
	@ResponseBody
	public Object selectById(String id){
		return ps.findById(id);
	}
	@RequestMapping("modifypicture")
	@ResponseBody
	public void modifyPriture(Picture p){
		System.out.println("asddasdas");
		ps.modifyPicture(p);
	}
	
	
}
