package com.baizhi.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.baizhi.entity.Menu;
import com.baizhi.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {
	@Resource(name="menuService")
	private MenuService ms;
	
	@RequestMapping("/menujson")
	@ResponseBody
	private List<Menu> menujson(){
		return ms.findAll();
	}
	
}
