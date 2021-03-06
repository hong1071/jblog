package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.vo.CategoryVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public void add(CategoryVo vo) {
		
		categoryRepository.insert(vo);
	}

	public List<CategoryVo> findAll(String userId) {

		List<CategoryVo> categorylist = categoryRepository.findAll(userId);
		
		return categorylist;
	}

	public List<CategoryVo> findAllAndCount() {
		
		return categoryRepository.findAllAndCount();
	}

}

