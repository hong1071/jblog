package com.douzone.jblog.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/blog/{authUser}")
public class BlogController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private PostService postService;
	
	@RequestMapping("")
	public String main(HttpSession session, Model model) {
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		String userId = userVo.getId();
		
		BlogVo blogVo = blogService.findById(userId);
		
		List<CategoryVo> categoryList = categoryService.findAll(userId);
		
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("blogVo", blogVo);
		
		return "blog/blog-main";
	}
	
	@RequestMapping("/category/{no}")
	public String category(@PathVariable("no") int categoryNo, HttpSession session, Model model) {
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		String userId = userVo.getId();
		
		List<PostVo> postList = postService.findByCateNo(categoryNo);
		List<CategoryVo> categoryList = categoryService.findAll(userId);
		BlogVo blogVo = blogService.findById(userId);
		
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("postList", postList);
		model.addAttribute("blogVo", blogVo);

		return "blog/blog-main";
	}
	
	@RequestMapping("/post/{no}")
	public String post(@PathVariable("no") int postNo, HttpSession session, Model model) {
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		String userId = userVo.getId();
		
		List<CategoryVo> categoryList = categoryService.findAll(userId);
		BlogVo blogVo = blogService.findById(userId);
		
		PostVo postVo = postService.findByNo(postNo);
		int categoryNo = postVo.getCategoryNo();

		List<PostVo> postList = postService.findByCateNo(categoryNo);

		
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("postList", postList);
		model.addAttribute("postVo", postVo);
		
		
		return "blog/blog-main";
	}
	
	@RequestMapping("admin")
	public String admin(HttpSession session, Model model) {
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		String userId = userVo.getId();
		BlogVo blogVo = blogService.findById(userId);
		model.addAttribute("blogVo", blogVo);
		
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping("admin/update")
	public String update(BlogVo blogVo, HttpSession session, 
		@RequestParam(value="file") MultipartFile multipartFile, Model model) {
		
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		
		String userId = userVo.getId();
		blogVo.setId(userId);
				
		String url = blogService.restore(multipartFile);
		blogVo.setLogo(url);
		System.out.println(blogVo);
		blogService.update(blogVo);
		model.addAttribute("blogVo", blogVo);
		
		return "redirect:/blog/" + userId + "/admin/";
	}
	
	@RequestMapping("admin/category")
	public String category(HttpSession session, Model model) {
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		String userId = userVo.getId();
		BlogVo blogVo = blogService.findById(userId);
		List<CategoryVo> categoryList = categoryService.findAllAndCount();
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("categoryList", categoryList);
		
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
	
	@RequestMapping("admin/writeForm")
	public String writeForm(HttpSession session, Model model) {
		
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		String userId = userVo.getId();

		BlogVo blogVo = blogService.findById(userId);
		model.addAttribute("blogVo", blogVo);
		
		List<CategoryVo> categoryList = categoryService.findAll(userId);
		model.addAttribute("categoryList", categoryList);
		
		return "blog/blog-admin-write";
	}
	
	@RequestMapping("admin/write")
	public String write(PostVo vo, HttpSession session, Model model) {
		
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		String userId = userVo.getId();
		BlogVo blogVo = blogService.findById(userId);
		model.addAttribute("blogVo", blogVo);
		
		postService.insert(vo);
		
		return "blog/blog-admin-write";
	}

}
