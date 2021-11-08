package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;

@Repository
public class BlogRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public boolean update(BlogVo vo) {
		System.out.println(vo + " repository");
		int count = sqlSession.update("blog.update", vo); 
		return count == 1;
	}

}
