package com.baizhi.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.baizhi.dao.AudioDao;
import com.baizhi.entity.Audio;
import com.baizhi.entity.Picture;
import com.baizhi.entity.Special;

public class testAudio {
	
	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
	AudioDao ad = (AudioDao) ctx.getBean("audioDao");
	
	@Test
	public void insertSpecial(){
		Audio a = new Audio();
		a.setId("aaaaa");
		a.setAudio_name("aaaaa");
		a.setName("adsdasd");
		a.setAudio_url("dasdas");
		a.setSpecial_id("2126dd89-dacd-4d0b-8bda-0db8185dd0f2");
		ad.insertAudio(a);
	}
	
	
}
