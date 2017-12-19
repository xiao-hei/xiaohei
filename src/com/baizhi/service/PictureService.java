package com.baizhi.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.baizhi.entity.Picture;

public interface PictureService {
	void addPicture(Picture p,MultipartFile file,HttpServletRequest req, HttpSession session);
	List<Picture> findByPage(int curpage,int rows);
	int findCount();
	void removePicture(String[] ids);
	Picture findById(String id);
	void modifyPicture(Picture p);
}
