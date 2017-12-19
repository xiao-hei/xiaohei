package com.baizhi.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.baizhi.entity.Picture;
import com.baizhi.entity.Special;

public interface SpecialService {
	void addSpecial(Special s,MultipartFile file,HttpServletRequest req, HttpSession session);
	List<Special> findByPage(int curpage,int rows);
	int findCount();
	void removeSpecial(String[] ids);
	Special findById(String id);
	void modifySpecial(Special s);
	List<Special> findAll();
}
