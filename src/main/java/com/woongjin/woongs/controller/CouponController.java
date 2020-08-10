package com.woongjin.woongs.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.woongjin.woongs.model.CouponDto;
import com.woongjin.woongs.service.CouponService;

@Controller
public class CouponController {
	
	@Autowired
	CouponService service;

	@RequestMapping(value = "/coupon")
	public String coupon(HttpServletRequest request) {
		if ((boolean) request.getSession().getAttribute("admin")) {
			return "couponIssue";
		}

		return "coupon";
	}

	// 쿠폰 생성된 값을 받아옴
	@RequestMapping(value = "/couponIssue", method = RequestMethod.POST)
	public String issueCoupon(CouponDto dto, HttpServletRequest request ) {
		// 나중에 session 값으로 admin_id 를 가져옴
		dto.setAdmin_id((String) request.getSession().getAttribute("user_id"));

		SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월dd일 HH시mm분ss초");
		Date now = new Date();

		dto.setCoupon_reg_date(format.format(now));

		// 랜덤 문자열 생성
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		String randomStr = String.valueOf((char) ((int) (rnd.nextInt(26)) + 97));

		for (int i = 0; i < 16; i++) {
			int rIndex = rnd.nextInt(3);
			switch (rIndex) {
			case 0:
				// a-z
				temp.append((char) ((int) (rnd.nextInt(26)) + 97));
				break;
			case 1:
				// A-Z
				temp.append((char) ((int) (rnd.nextInt(26)) + 65));
				break;
			case 2:
				// 0-9
				temp.append((rnd.nextInt(10)));
				break;
			}
		}

		dto.setCoupon_code(temp.toString());
		service.issueCoupon(dto);

		return "couponIssue";
	}

	// coupon view Ajax
	@RequestMapping(value = "/couponView", method = RequestMethod.POST)
	public void tagList(HttpServletResponse resp, HttpServletRequest request ) throws Exception {
		Gson json = new Gson();
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		List<CouponDto> list = null;
		
		if ((boolean) request.getSession().getAttribute("admin")) {
			list = service.getCouponInfo();
		}else {
			list = service.getUserCouponInfo((String) request.getSession().getAttribute("user_id"));	
		}

		out.print(json.toJson(list));
	}

	// 사용자 쿠폰 등록 메소드
	@RequestMapping(value = "/couponEnroll", method = RequestMethod.POST)
	public String enrollCoupon(String coupon_code, HttpServletRequest request ) {
		Map<String, String> map = new HashedMap<String, String>();
		map.put("user_id", (String) request.getSession().getAttribute("user_id"));
		map.put("coupon_no", service.selectCouponByCode(coupon_code));
		
		System.out.println("user_id : " + map.get("user_id"));
		System.out.println("coupon_code : " + coupon_code);
		
		// 만약 이미 등록한 쿠폰이 있다면 혹은 이미 사용된 쿠폰을 등록하려고 한다면 에러 뜨게
		// coupon for everyone = everyone -1
		// if coupon for everyone 이 1이상이라면 ok
		// coupon 이름이 동일하다면 등록 불가
		int check = service.isAvailableCoupon(map);
		
		if(check == 1) {
			System.out.println("이미 등록한 쿠폰입니다.");
			request.setAttribute("isAvailable", "already");
			return "coupon";
		}else {
			check = service.isAvailableCoupon(coupon_code);
			if(check < 1) {
				System.out.println("이미 사용된 쿠폰입니다.");
				request.setAttribute("isAvailable", "used");
				return "coupon";
			}
		}
		
		int x = service.enrollCoupon(map);
		
		if( x== 1) {
			// 발행 성공
			request.setAttribute("isAvailable", "success");
			return "coupon";
		}else {
			// 발행 실패
			request.setAttribute("isAvailable", "fail");
			return "coupon";
		}
	}
	
	// 쿠폰 삭제 메소드
	@RequestMapping(value = "/couponDelete", method = RequestMethod.GET)
	public String deleteCoupon(int no) {
		// 나중에 session 값으로 admin_id 를 가져옴
		service.deleteCoupon(no);

		return "coupon";
	}

}