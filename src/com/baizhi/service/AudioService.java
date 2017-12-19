package com.baizhi.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.baizhi.entity.Audio;
import com.baizhi.entity.Special;

public interface AudioService {
	void addAudio(Audio a,MultipartFile file,HttpServletRequest req, HttpSession session);
	List<Audio> findByPage(int curpage,int rows);
	int findCount();
	void removeAudio(String[] ids);
	void removeBySpecialId(String[] ids);
	Special findById(String id);
	void modifyAudio(Audio a);
}
