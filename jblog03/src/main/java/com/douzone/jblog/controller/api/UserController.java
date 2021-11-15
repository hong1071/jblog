package com.douzone.jblog.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.jblog.dto.JsonResult;
import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

@Controller("userApiController")
@RequestMapping("/user/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/checkemail")
	public JsonResult checkEmail(@RequestParam(value="email", required=true, defaultValue="") String email) {

		UserVo userVo = userService.getUser(email);
		
		
		/*
		Map<String, Object> map = new HashMap<>();
		map.put("exist", "success");
		map.put("data", userVo != null);
		map.put("message", null);
		*/
		
		return JsonResult.success(userVo != null);
		
	}

}
