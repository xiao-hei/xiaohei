package com.baizhi.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.baizhi.dao.SpecialDao;
import com.baizhi.entity.Picture;
import com.baizhi.entity.Special;
import com.baizhi.exception.FileNotException;

@Service("specialService")
@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
public class SpecialServiceImpl implements SpecialService{
	@Resource(name="specialDao")
	SpecialDao ss;
	@Transactional
	public void addSpecial(Special s, MultipartFile file,
			HttpServletRequest req, HttpSession session) {
		String realPath = session.getServletContext().getRealPath("/");
		String  pt = new File(realPath).getParent();
		// 添加upload 在不同的操作系统中 分隔符是不一样的 File.separator 屏蔽操作系统差异
		String path = pt + File.separator +"img";
		File file2 = new File(path);
		if(!file2.exists()){
			file2.mkdirs();
		}
		
		String id = UUID.randomUUID().toString();
		String originalFilename = file.getOriginalFilename();
		String newName = new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date()) + id.replace("-", "") + "." + FilenameUtils.getExtension(originalFilename);
		File target = new File(path,newName);
		try{
			file.transferTo(target);
			s.setId(id);
			s.setCover_name(newName);
			String netPath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+"/img/"+newName;
			s.setCover_url(netPath);
			s.setType("1");
			s.setUpload_time(new Date());
			ss.insertSpecial(s);
			
		}catch (FileNotException e) {
			//自定义处理
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	public List<Special> findByPage(int curpage, int rows) {
		return ss.selectAll((curpage-1)*rows, rows);
	}

	public int findCount() {
		return ss.selectCount();
	}
	@Transactional
	public void removeSpecial(String[] ids) {
		for (String st : ids) {
			ss.deleteSpecial(st);
		}
	}

	public Special findById(String id) {
		return ss.selectById(id);
	}
	@Transactional
	public void modifySpecial(Special s) {
		ss.updateSpecial(s);
	}

	public List<Special> findAll() {
		return ss.queryAll();
	}
	
}
