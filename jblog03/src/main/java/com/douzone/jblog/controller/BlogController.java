package com.douzone.jblog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/blog/{authUser}")
public class BlogController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping("")
	public String main() {
		return "blog/blog-main";
	}
	
	@RequestMapping("admin")
	public String admin() {
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping("admin/update")
	public String update(BlogVo vo,HttpSession session) {
		
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		
		String userId = userVo.getId();
		vo.setId(userId);
		
		System.out.println(vo + " controller");
		
		blogService.update(vo);
		
		return "redirect:/blog/" + userId + "/admin/";
	}
	
	@RequestMapping("admin/category")
	public String category() {
		return "blog/blog-admin-category";
	}
	
	@RequestMapping("admin/category/add")
	public String categoryAdd(CategoryVo vo, HttpSession session) {
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		
		String userId = userVo.getId();
		vo.setBlogId(userId);
		
		System.out.println(vo);
		categoryService.add(vo);
		
		return "redirect:/blog/" + userId + "/admin/category";
	}
	
	@RequestMapping("admin/write")
	public String write() {
		return "blog/blog-admin-write";
	}

}
