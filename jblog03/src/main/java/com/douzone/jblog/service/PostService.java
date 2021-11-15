package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.PostRepository;
import com.douzone.jblog.vo.PostVo;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;

	public void insert(PostVo vo) {
		
		postRepository.insert(vo);
	}

	public List<PostVo> findByCateNo(int categoryNo) {

		return postRepository.findByCateNo(categoryNo);
		
	}

	public PostVo findByNo(int postNo) {
		
		return postRepository.findByNo(postNo);
	}

}
