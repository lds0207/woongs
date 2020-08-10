package com.woongjin.woongs.model;

public class CouponDto {
	int no;
	String admin_id;
	String coupon_name;
	String coupon_code;
	int coupon_price;
	String coupon_reg_date;
	String coupon_validate_date;
	int coupon_for_everyone;

	public String getCoupon_reg_date() {
		return coupon_reg_date;
	}

	public void setCoupon_reg_date(String coupon_reg_date) {
		this.coupon_reg_date = coupon_reg_date;
	}

	public String getCoupon_validate_date() {
		return coupon_validate_date;
	}

	public void setCoupon_validate_date(String coupon_validate_date) {
		this.coupon_validate_date = coupon_validate_date;
	}

	public int getCoupon_for_everyone() {
		return coupon_for_everyone;
	}

	public void setCoupon_for_everyone(int coupon_for_everyone) {
		this.coupon_for_everyone = coupon_for_everyone;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}

	public String getCoupon_name() {
		return coupon_name;
	}

	public void setCoupon_name(String coupon_name) {
		this.coupon_name = coupon_name;
	}

	public String getCoupon_code() {
		return coupon_code;
	}

	public void setCoupon_code(String coupon_code) {
		this.coupon_code = coupon_code;
	}

	public int getCoupon_price() {
		return coupon_price;
	}

	public void setCoupon_price(int coupon_price) {
		this.coupon_price = coupon_price;
	}

	@Override
	public String toString() {
		return "CouponDto [no=" + no + ", admin_id=" + admin_id + ", coupon_name=" + coupon_name + ", coupon_code="
				+ coupon_code + ", coupon_price=" + coupon_price + ", coupon_reg_date=" + coupon_reg_date
				+ ", coupon_validate_date=" + coupon_validate_date + ", coupon_for_everyone=" + coupon_for_everyone
				+ "]";
	}

}
