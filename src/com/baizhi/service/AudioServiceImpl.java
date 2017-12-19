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

import com.baizhi.dao.AudioDao;
import com.baizhi.entity.Audio;
import com.baizhi.entity.Special;
import com.baizhi.exception.FileNotException;

@Service("audioService")
@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
public class AudioServiceImpl implements AudioService {
	@Resource(name="audioDao")
	AudioDao ad;
	
	@Transactional
	public void addAudio(Audio a, MultipartFile file, HttpServletRequest req,
			HttpSession session) {
		String realPath = session.getServletContext().getRealPath("/");
		String  pt = new File(realPath).getParent();
		// 添加upload 在不同的操作系统中 分隔符是不一样的 File.separator 屏蔽操作系统差异
		String path = pt + File.separator +"voice";
		File file2 = new File(path);
		if(!file2.exists()){
			file2.mkdirs();
		}
		
		String id = UUID.randomUUID().toString();
		String originalFilename = file.getOriginalFilename();
		String newName = new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date()) + id.replace("-","") + "." + FilenameUtils.getExtension(originalFilename);
		File target = new File(path,newName);
		try{
			file.transferTo(target);
			a.setId(id);
			a.setAudio_name(newName);
			String netPath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+"/voice/"+newName;
			a.setAudio_url(netPath);
			a.setUpload_time(new Date());
			a.setStatus("1");
			a.setType("2");
			ad.insertAudio(a);
		}catch (FileNotException e) {
			//自定义处理
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Audio> findByPage(int curpage, int rows) {
		return null;
	}

	public int findCount() {
		return 0;
	}
	@Transactional
	public void removeAudio(String[] ids) {
		for (String id : ids) {
			ad.deleteAudio(id);
		}
	}

	public Special findById(String id) {
		return null;
	}
	@Transactional
	public void modifyAudio(Audio a) {

	}

	public void removeBySpecialId(String[] ids) {
		for (String id : ids) {
			ad.deletBySpecialId(id);
		}
		
	}

}
