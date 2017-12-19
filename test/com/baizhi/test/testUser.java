package com.baizhi.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;

public class testUser {
	
	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
	UserService ss = (UserService) ctx.getBean("userService");
	
	@Test
	public void findAll(){
		List<User> list = ss.findAll();
		for (User user : list) {
			
			System.out.println(user.getName());
		}
		
	}
	
	@Test
	public void insert(){
		User u = new User();
		u.setBirthday(new Date());
		u.setName("张三");
		ss.addUser(u);
		
	}
}
