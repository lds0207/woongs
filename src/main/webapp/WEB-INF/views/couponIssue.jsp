<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>관리자 쿠폰 발행 페이지</title>
</head>
<script src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	$(function() {
		$.ajax({
			type : "post",
			url : "couponView",
			dataType : "json"
		}).done(
				function(args) { //응답이 성공 상태 코드를 반환하면 호출되는 함수
					if (args.length == 0) {
						$("#coupon").append("<div> 쿠폰이 없습니다. 발행해주세요 ~ </div>");

					} else {
						for (var i = 0; i < args.length; i++) {
							$("#coupon").append(
									"<div>" + args[i].no + " "
											+ args[i].coupon_code + " "
											+ args[i].coupon_name
											+ args[i].coupon_price + " "
											+ args[i].coupon_reg_date + " "
											+ args[i].coupon_validate_date + " " 
											+ args[i].coupon_for_everyone + " "
											+ args[i].admin_id + " "
											+ "<a href='couponDelete?no="+args[i].no+"'>삭제</a>"
											+"</div>");
						}
					}
				}).fail(function(e) {
			alert(e.responseText);
		})
	});
</script>
<body>
	<form action="/couponIssue" method="Post">
		쿠폰 이름 : <input type="text" name="coupon_name"> 쿠폰 가격 : <input
			type="number" name="coupon_price"> 유효 기간 : <input type="date"
			name="coupon_validate_date"> <select
			name="coupon_for_everyone">
			<option value="1">한명에게</option>
			<option value="100">모두에게</option>
		</select> <input type="submit" value="생성">
	</form>

	에이젝스로 쿠폰 만든것 보여주는 페이지
	<div>쿠폰번호 쿠폰코드 쿠폰이름 쿠폰가격 생성일자 유효기간 범위? 생성한 아이디</div>
	<div id="coupon"></div>
</body>
</html>