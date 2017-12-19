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

import com.baizhi.dao.PictureDao;
import com.baizhi.entity.Picture;
import com.baizhi.exception.FileNotException;

@Service("pictureService")
@Transactional
public class PictureServiceImpl implements PictureService{
	@Resource(name="pictureDao")
	PictureDao pd;
	
	public void addPicture(Picture p,MultipartFile file,HttpServletRequest req, HttpSession session){
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
			p.setId(id);
			p.setName(newName);
			String netPath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+"/img/"+newName;
			p.setUrl(netPath);
			p.setStatus(false);
			p.setUpload_time(new Date());
			pd.insertpicture(p);
			
		}catch (FileNotException e) {
			//自定义处理
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<Picture> findByPage(int curpage, int rows) {
		return pd.selectAll((curpage-1)*rows, rows);
	}

	public int findCount() {
		return pd.selectCount();
	}

	public void removePicture(String[] ids) {
		for (String id : ids) {
			pd.deletePicture(id);
		}
	}

	public Picture findById(String id) {
		return pd.selectById(id);
	}

	public void modifyPicture(Picture p) {
		pd.updatePicture(p);
		
	} 
}
