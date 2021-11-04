package com.douzone.jblog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/joinForm")
	public String joinForm() {
		return "user/join";
	}
	
	@RequestMapping("/join")
	public String join(UserVo vo) {
		
		System.out.println(vo);
		userService.join(vo);
		
		return "user/joinsuccess";
	}
	
	@RequestMapping("/loginForm")
	public String loginForm() {
		return "user/login";
	}
}
