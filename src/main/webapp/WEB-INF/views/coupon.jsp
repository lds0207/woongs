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
		var check = "<%= request.getAttribute("isAvailable") %>";
		if(check == null) {
			console.log("<%= request.getAttribute("isAvailable") %>");
			console.log("null 이다!");
		} else {
			console.log("<%= request.getAttribute("isAvailable") %>");
			if(check == "success") {
				alert("등록 성공");	
			}else if(check == "fail"){
				alert("등록 오류");
			}else if(check == "already") {
				alert("이미 등록한 쿠폰입니다.");
			}else if(check == "used") {
				alert("이미 사용된 쿠폰입니다.");
			}
		}
		
		$.ajax({
			type : "post",
			url : "couponView",
			dataType : "json"
		}).done(
				function(args) { //응답이 성공 상태 코드를 반환하면 호출되는 함수
					if (args.length == 0) {
						$("#coupon").append("<div> 쿠폰이 없습니다.</div>");

					} else {
						for (var i = 0; i < args.length; i++) {
							$("#coupon").append(
									"<div>" 
											+ args[i].coupon_name + " "
											+ args[i].coupon_price + " "
											+ args[i].coupon_validate_date + " "
											+"</div>");
						}
					}
				}).fail(function(e) {
			alert(e.responseText);
		})
	});

	
</script>
<body>
	<form action="/couponEnroll" method="Post">
		쿠폰 코드 : <input type="text" name="coupon_code"> 
		<input type="submit" value="쿠폰등록">
	</form>

	에이젝스로 쿠폰 보여주는 페이지
	<div>쿠폰이름 쿠폰가격 유효기간 </div>
	<div id="coupon"></div>
</body>
</html>