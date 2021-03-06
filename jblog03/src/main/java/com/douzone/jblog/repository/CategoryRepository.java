package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	
	@Autowired
	private SqlSession sqlSession;

	public boolean insert(CategoryVo vo) {
		
		int count = sqlSession.insert("category.insert", vo);
		
		return count == 1;
	}

	public List<CategoryVo> findAll(String userId) {
		
		return sqlSession.selectList("category.findAll");
		
	}

	public List<CategoryVo> findAllAndCount() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("category.findAllAndCount");
	}


}
