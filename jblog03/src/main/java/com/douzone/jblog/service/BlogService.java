package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVo;

@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;
	
	public void update(BlogVo vo) {
		
		System.out.println(vo + " before service");
		
		blogRepository.update(vo);
		System.out.println(vo + " after service");
	}

}
