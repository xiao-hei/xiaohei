package com.baizhi.controller;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.baizhi.entity.Audio;
import com.baizhi.service.AudioService;

@Controller
@RequestMapping("audio")
public class AudioController {
	@Resource(name="audioService")
	AudioService as;
	
	
	@RequestMapping("uploadaudio")
	public void uploadaudio(MultipartHttpServletRequest mreq,HttpServletRequest req,HttpSession session,Audio a){
		System.out.println(a.getSpecial_id()+"==================");
		List<MultipartFile> files = mreq.getFiles("file1");
		for (MultipartFile multipartFile : files) {
			as.addAudio(a, multipartFile, req, session);
		}
	}
	@RequestMapping("deleteAudio")
	@ResponseBody
	public void deleteAudio(String[] ids,String[] name,HttpSession session){
		String realPath = session.getServletContext().getRealPath("/");
		String  pt = new File(realPath).getParent();
		// 添加upload 在不同的操作系统中 分隔符是不一样的 File.separator 屏蔽操作系统差异
		for (String st : name) {
			String path = pt + File.separator + "voice/"+ st;
			File file2 = new File(path);
			file2.delete();
		}
		as.removeAudio(ids);
	}
	
	
}
