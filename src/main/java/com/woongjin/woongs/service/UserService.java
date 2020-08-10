package com.woongjin.woongs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woongjin.woongs.model.UserDao;
import com.woongjin.woongs.model.UserDto;

@Service
public class UserService {
	
	@Autowired
	UserDao dao;

	public UserDto isUser(UserDto user) {
		// TODO Auto-generated method stub
		return dao.isUser(user);
	}

}
