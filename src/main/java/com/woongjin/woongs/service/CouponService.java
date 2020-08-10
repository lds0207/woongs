package com.woongjin.woongs.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woongjin.woongs.model.CouponDao;
import com.woongjin.woongs.model.CouponDto;

@Service
public class CouponService {
	
	@Autowired
	CouponDao dao;
	
	public void issueCoupon(CouponDto dto) {
		// TODO Auto-generated method stub
		dao.issueCoupon(dto);
	}

	public List<CouponDto> getCouponInfo() {
		// TODO Auto-generated method stub
		return dao.getCouponInfo();
	}

	public void deleteCoupon(int no) {
		// TODO Auto-generated method stub
		dao.deleteCoupon(no);
	}

	public List<CouponDto> getUserCouponInfo(String user_id) {
		// TODO Auto-generated method stub
		return dao.getUserCouponInfo(user_id);
	}

	public int enrollCoupon(Map<String, String> map) {
		// TODO Auto-generated method stub
		return dao.enrollcoupon(map);
	}

	public String selectCouponByCode(String coupon_code) {
		// TODO Auto-generated method stub
		return dao.selectCouponByCode(coupon_code);
	}

	public int isAvailableCoupon(Map<String, String> map) {
		// TODO Auto-generated method stub
		return dao.isAvailableCoupon(map);
	}

	public int isAvailableCoupon(String coupon_code) {
		// TODO Auto-generated method stub
		return dao.isAvailableCoupon(coupon_code);
	}	
}
