package com.baizhi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baizhi.entity.Special;
import com.baizhi.service.SpecialService;

@Controller
@RequestMapping("/")
public class SpecialServiceController {
	@Resource(name="specialService")
	SpecialService ss;
	
	@RequestMapping(value = { "queryByPage/{page}/{rows}" }, method = RequestMethod.GET)
	@ResponseBody
	public Object queryByPage(@PathVariable("page")int page,@PathVariable("rows")int rows){
		System.out.println(page+"aaaa"+rows);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows",ss.findByPage(page, rows));
		map.put("total",ss.findCount());
		return map;
	}
	@RequestMapping(value = { "queryAll" }, method = RequestMethod.GET)
	@ResponseBody
	public List<Special> queryAll(){
		return ss.findAll();
	}
	
	
}
