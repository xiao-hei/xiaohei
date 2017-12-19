package com.baizhi.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.jms.Session;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baizhi.entity.Manager;
import com.baizhi.service.ManagerService;
import com.baizhi.util.CreateValidateCode;
import com.baizhi.util.MD5Util;

@Controller
@RequestMapping("/manager")
public class ManagerController {
	@Resource(name="managerService")
	private ManagerService ms;
	
	@RequestMapping("/login")
	public String login(String username,String password,String code,HttpSession session){
		Manager manager = ms.login(username);
		System.out.println(username+password+code);
		if(manager==null){
			//用户不存在
			session.setAttribute("errologin", "用户名不存在");
			return "redirect:/jsp/manager/login.jsp";
		}else{
			String md = MD5Util.jdkMD(password+manager.getSalt());
			String vcode = (String) session.getAttribute("code");
			if(md.equals(manager.getPassword()) && code.equalsIgnoreCase(vcode)){
				//登陆成功
				session.setAttribute("successlogin", manager);
				return "redirect:/jsp/manager/main.jsp";
			}else if(md.equals(manager.getPassword())){
				//验证码错误
				session.setAttribute("errologin", "验证码错误");
				return "redirect:/jsp/manager/login.jsp";
			}else{
				//密码错误
				session.setAttribute("errologin", "密码错误");
				return "redirect:/jsp/manager/login.jsp";
			}
		}
	}
	@RequestMapping("/code")
	public void getCoded(HttpServletResponse response, HttpSession session) throws IOException{
		//生成验证码
		CreateValidateCode vcd = new CreateValidateCode(100,30,5,10);
		String code = vcd.getCode();
		//将随机数保存在session中
		session.setAttribute("code", code);
		//写到client
		ServletOutputStream out = response.getOutputStream();
		vcd.write(out);
	}
	
}
