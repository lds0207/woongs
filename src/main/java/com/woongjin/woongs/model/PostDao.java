package com.woongjin.woongs.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class PostDao extends SqlSessionDaoSupport{

	public List<TagDto> getTagList() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("woongs.tags");
	}

	public List<Map<String, Object>> getSubTagList(int tagno) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("woongs.subtags", tagno);
	}

	public void insertPost(PostDto dto) {
		// TODO Auto-generated method stub
		getSqlSession().insert("woongs.insertPost", dto);
	}
	
	public List<PostDto> getPosts() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("woongs.selectPost");
	}
}
