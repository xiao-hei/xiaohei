package com.baizhi.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.baizhi.entity.Manager;
import com.baizhi.service.ManagerService;
import com.baizhi.service.ManagerServiceImpl;
import com.baizhi.util.MD5Util;

public class testManager {
	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
	ManagerService msi = (ManagerService) ctx.getBean("managerService");

	@Test
	public void sqlSessionFactory(){
		SqlSessionFactory ssf = (SqlSessionFactory) ctx.getBean("sqlSessionFactoryBean");
		System.out.println(ssf.openSession().getClass());
	}
	
	@Test
	public void selectByUserName(){
		Manager fbun = msi.login("111");
		System.out.println(fbun.getPassword());
	}
	
	@Test
	public void insertMagager(){
		Manager mg = new Manager();
		String salt = MD5Util.getSalt(4);
		String md = MD5Util.jdkMD("1111"+salt);
		System.out.println(salt+md);
		mg.setPassword(md);
		mg.setPower("1");
		mg.setSalt(salt);
		mg.setUsername("张");
		msi.register(mg);
		
	}
	
}
