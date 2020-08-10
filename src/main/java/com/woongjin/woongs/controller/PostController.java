package com.woongjin.woongs.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.woongjin.woongs.model.PostDto;
import com.woongjin.woongs.model.TagDto;
import com.woongjin.woongs.service.PostService;

@Controller
public class PostController {
	@Autowired
	PostService post;

	// post upload
	@RequestMapping("/upload")
	public String upload() {
		return "postUpload";
	}

	// tag load
	@RequestMapping(value = "/tag", method = RequestMethod.POST)
	public void tagList(HttpServletResponse resp) throws Exception {
		List<TagDto> list = post.getTagList();
		Gson json = new Gson();
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();

		out.print(json.toJson(list));
	}

	// sub tag load
	@RequestMapping(value = "/subtag", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String subtagList(int tagno) throws Exception {
		List<Map<String, Object>> list = post.getSubTagList(tagno);
		Gson json = new Gson();
		return json.toJson(list);
	}

	// post insert
	@RequestMapping(value = "/insertPost", method = RequestMethod.POST)
	public String insertPost(PostDto dto, MultipartFile report) {
		
		String path = "C:\\Users\\asd35\\Documents\\project-spring-workspace\\woongs\\src\\main\\webapp\\resources\\Images";
		String alterpath = "resources\\Images\\";

		File file = new File(path);
		
		if (!file.exists()) {
			file.mkdirs();
		}

		path += "\\" + report.getOriginalFilename();
		alterpath += report.getOriginalFilename();
		
		file = new File(path);

		try {
			report.transferTo(file);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dto.setThumbnail(alterpath);
		post.insertPost(dto);

		return "/";
	}

	// post viewByUser_id
	
	// get all recent post viewByCategory
	@RequestMapping(value = "/post", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String cateList() throws Exception {
		List<PostDto> list = post.getPosts();
		for(PostDto dto : list) {
			dto.setThumbnail(dto.getThumbnail().replace('\\', '/'));
		}
		
		Gson json = new Gson();
		return json.toJson(list);
	}
}
