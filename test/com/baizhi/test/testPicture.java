package com.baizhi.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.baizhi.entity.Picture;
import com.baizhi.service.PictureService;

public class testPicture {
	
	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
	PictureService ps = (PictureService) ctx.getBean("pictureService");
	
	@Test
	public void insertPicture(){
		Picture p = new Picture();
		p.setName("aaa");
		p.setStatus(true);
		p.setUrl("aaa");
		p.setUpload_time(new Date());
		p.setDescription("aa");
		
	}
	
	@Test
	public void selectAll(){
		List<Picture> list = ps.findByPage(1,3);
		for (Picture picture : list) {
			System.out.println(picture.getName());
		}
		
	}
	
	@Test
	public void selectCount(){
		System.out.println(ps.findCount());
		
	}
	@Test
	public void deletePicture(){
		
	}
	@Test
	public void selectById(){
		System.out.println(ps.findById("e82f14be-1f1a-4764-a8b4-397917178628").getUrl());
	}
	@Test
	public void updatePicture(){
		Picture p = new Picture();
		p.setId("e82f14be-1f1a-4764-a8b4-397917178628");
		p.setStatus(true);
		p.setUrl("aaa");
		p.setUpload_time(new Date());
		p.setDescription("aazzzzzzzzzaaazzz");
		ps.modifyPicture(p);
	}
}
