package com.woongjin.woongs.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.woongjin.woongs.model.UserDto;
import com.woongjin.woongs.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService service;
	
	@RequestMapping("login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="login",method = RequestMethod.POST )
	public String loginPro(UserDto user, HttpServletRequest request ) {
		
		UserDto check = service.isUser(user);
		
		if(check != null) {
			request.getSession().setAttribute("user_id", check.getUser_id());
			
			if(check.getIsAdmin() == 1) {
				request.getSession().setAttribute("admin", true);
			}else {
				request.getSession().setAttribute("admin", false);
			}
			
			//어서오세요 ㅎㅎ
			return "main";
		}

		//여기로 가면 로그인 실패여 샣갸
		return "index";
	}
}
