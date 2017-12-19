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
import com.baizhi.entity.Special;
import com.baizhi.service.AudioService;
import com.baizhi.service.SpecialService;

@Controller
@RequestMapping("special")
public class SpecialController {
	@Resource(name="specialService")
	SpecialService ss;
	@Resource(name="audioService")
	AudioService as;
	
	@RequestMapping("showAll")
	@ResponseBody
	public Object showAll(int page,int rows){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows",ss.findByPage(page, rows));
		map.put("total",ss.findCount());
		return map;
		
	}
	@RequestMapping("uploadspecial")
	public void uploadspecial(MultipartHttpServletRequest mreq,HttpServletRequest req,HttpSession session,Special s){
		System.out.println(s.getSynopsis());
		List<MultipartFile> files = mreq.getFiles("file1");
		for (MultipartFile multipartFile : files) {
			ss.addSpecial(s, multipartFile, req, session);
		}
	}
	@RequestMapping("deletespecial")
	@ResponseBody
	public void deletespecial(String[] ids,String[] name,HttpSession session){
		String realPath = session.getServletContext().getRealPath("/");
		String  pt = new File(realPath).getParent();
		// 添加upload 在不同的操作系统中 分隔符是不一样的 File.separator 屏蔽操作系统差异
		for (String st : name) {
			String path = pt + File.separator + "img/"+ st;
			File file2 = new File(path);
			file2.delete();
		}
		as.removeBySpecialId(ids);
		ss.removeSpecial(ids);
	}
	@RequestMapping("queryAll")
	@ResponseBody
	public List<Special> queryAll(){
		return ss.findAll();
	}
	
}
