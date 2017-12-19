package com.baizhi.code;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@RequestMapping("/identify")
@Controller
public class UserController {
    
	@Autowired
	private UserService userService;
	
	@RequestMapping("/obtain")
	public @ResponseBody void getCode(String phone){
		
		userService.sendCode(phone);
		
	}
	
	@RequestMapping(value={"/check/{phone}"},method=RequestMethod.GET)
	public @ResponseBody Map<String,String> checkCode(@PathVariable("phone")String phone,String code){
		
		Map<String, String> map = userService.checkCode(phone,code);
		
		return map;
	}
	
}
