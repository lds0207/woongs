package com.woongjin.woongs.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woongjin.woongs.model.PostDao;
import com.woongjin.woongs.model.PostDto;
import com.woongjin.woongs.model.TagDto;

@Service
public class PostService {
	@Autowired
	PostDao dao;

	public void setDao(PostDao dao) {
		this.dao = dao;
	}

	public List<TagDto> getTagList() {
		// TODO Auto-generated method stub
		return dao.getTagList();
	}

	public List<Map<String, Object>> getSubTagList(int tagno) {
		// TODO Auto-generated method stub
		return dao.getSubTagList(tagno);
	}

	public void insertPost(PostDto dto) {
		// TODO Auto-generated method stub
		dao.insertPost(dto);
	}

	public List<PostDto> getPosts() {
		// TODO Auto-generated method stub
		return dao.getPosts();
	}
}
