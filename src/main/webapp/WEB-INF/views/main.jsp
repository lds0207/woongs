<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<a href="upload"> 업로드 페이지</a>

관리자 아이디로 로그인했다면 쿠폰발행 페이지로 링크
<a href="coupon"> 쿠폰 발행 페이지 (관리자)</a>
사용자 아이디로 로그인했다면 쿠폰확인 페이지로 링크
<a href="coupon"> 쿠폰 확인/등록 페이지 (사용자)</a>
<a href="login"> 로그인 </a>
<img src="resources/Images/test.jpg">
<script src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
$(function(){
	$.ajax({
		type:"post"
		,url:"tag"		
		,dataType:"json" })
		.done(function(args){	//응답이 성공 상태 코드를 반환하면 호출되는 함수
		 	for(var i=0; i < args.length; i++) {
			 	$("#tag").append("<a href='cate?="+args[i].no+"'>"+args[i].name+"</option>");
			 	$("#cate").after("<div id='"+args[i].no+"'>"+args[i].name+"</div>");
		 	}
				})
    	.fail(function(e) {
	    	alert(e.responseText);
    })

    console.log("ajax 끝");
	afterAjax();
});//ready 끝

function afterAjax() {
	console.log("함수 호출");
	
    $.ajax({
		type:"post"
		,url:"post"		
		,dataType:"json" })
		.done(function(args){	//응답이 성공 상태 코드를 반환하면 호출되는 함수
		 	for(var i=0; i < args.length; i++) {
			 	console.log(args[i].category_first);
			 	$("#"+args[i].category_first).after("<div id='"+args[i].no+"'><img src="+args[i].thumbnail+">"+args[i].title+"</div>");
		 	}
				})
    	.fail(function(e) {
	    	alert(e.responseText);
    })
}
</script>
<h2>카테고리 모음</h2>
<div id="tag"></div>
<div id="cate"></div>
</body>
</html>