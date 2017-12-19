package com.baizhi.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.baizhi.entity.Audio;
import com.baizhi.entity.Picture;
import com.baizhi.entity.Special;
import com.baizhi.service.SpecialService;

public class testSpecial {
	
	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
	SpecialService ss = (SpecialService) ctx.getBean("specialService");
	
	@Test
	public void insertSpecial(){
		Picture p = new Picture();
		p.setName("aaa");
		p.setStatus(true);
		p.setUrl("aaa");
		p.setUpload_time(new Date());
		p.setDescription("aa");
		//ps.addPicture(p);
	}
	
	@Test
	public void selectAll(){
		List<Special> list = ss.findByPage(1,10);
		for (Special s : list) {
			System.out.println(s.getName());
			List<Audio> list2 = s.getChildren();
			for (Audio audio : list2) {
				System.out.println(audio.getName());
			}
		}
		
	}
	
	@Test
	public void selectCount(){
		System.out.println(ss.findCount());
		
	}
	@Test
	public void deletePicture(){
		
	}
	@Test
	public void selectById(){
		System.out.println(ss.findById("aaa").getCover_url());
	}
	@Test
	public void updatePicture(){
		Special s = new Special();
		s.setId("uuid");
		s.setAuthor("张3");
		s.setType("1");
		ss.modifySpecial(s);
	}
	@Test
	public void queryAll(){
		List<Special> list = ss.findAll();
		for (Special special : list) {
			System.out.println(special);
		}
		
	}
}
