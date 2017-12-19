package com.baizhi.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.baizhi.entity.Menu;
import com.baizhi.service.MenuService;

public class testMenu {
	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
	MenuService ms = (MenuService) ctx.getBean("menuService");
	@Test
	public void selectAll(){
		List<Menu> list = ms.findAll();
		for (Menu menu : list) {
			System.out.println(menu.getName()+"====="+menu.getIconCls());
			for (Menu m : menu.getChildren()) {
				System.out.println(m.getName()+"c");
			}
		}
		
	}
}
