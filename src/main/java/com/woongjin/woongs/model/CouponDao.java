package com.woongjin.woongs.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class CouponDao extends SqlSessionDaoSupport{

	public void issueCoupon(CouponDto dto) {
		// TODO Auto-generated method stub
		getSqlSession().insert("woongs.issueCoupon", dto);
	}

	public List<CouponDto> getCouponInfo() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("woongs.selectCoupon");
	}

	public void deleteCoupon(int no) {
		// TODO Auto-generated method stub
		getSqlSession().delete("woongs.deleteCoupon", no);
	}

	//user coupon
	public List<CouponDto> getUserCouponInfo(String user_id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("woongs.selectUserCoupon", user_id);
	}

	public int enrollcoupon(Map<String, String> map) {
		// TODO Auto-generated method stub
		
		getSqlSession().update("woongs.updateCouponCount", map.get("coupon_no"));
		return getSqlSession().insert("woongs.enrollCoupon", map);
	}

	public String selectCouponByCode(String coupon_code) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("woongs.selectCouponByCode", coupon_code);
	}

	public int isAvailableCoupon(Map<String, String> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("woongs.isAvailableCoupon", map);
	}

	public int isAvailableCoupon(String coupon_code) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("woongs.isAvailableCouponCode", coupon_code);
	}
}
