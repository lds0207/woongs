package com.woongjin.woongs.model;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class UserDao extends SqlSessionDaoSupport{

	public UserDto isUser(UserDto user) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("woongs.login", user);
	}

}
