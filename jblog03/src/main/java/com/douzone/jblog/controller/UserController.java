package com.douzone.jblog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		userService.makeBlog(vo);
		
		return "user/joinsuccess";
	}
	
	@RequestMapping("/loginForm")
	public String loginForm() {
		return "user/login";
	}
	
	/* LoginInterceptor으로 인해 사용하지 않음
	@RequestMapping("/login")
	public String login(@RequestParam String id, @RequestParam String password, Model model, HttpSession session) {
		
		UserVo userVo = userService.getUser(id, password);
		
		if(userVo == null) {
			model.addAttribute("result", "fail");
			return "user/login";
		}
		
		// 인증처리 
		session.setAttribute("authUser", userVo);
		return "redirect:/main";
	}
	*/

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/";
	}
}
