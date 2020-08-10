<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<script src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
<script>
	$(function(){
		var url="tag";
		$.ajax({
			type:"post"
			,url:url		
			,dataType:"json" })
			.done(function(args){	//������ ���� ���� �ڵ带 ��ȯ�ϸ� ȣ��Ǵ� �Լ�
			 	for(var i=0; i < args.length; i++) {
				 	$("#tag").append("<option value='"+args[i].no+"'>"+args[i].name+"</option>");
			 	}
 				})
	    	.fail(function(e) {
		    	alert(e.responseText);
	    })
});//ready ��

function selectTag(){
	var tagno=$("#tag").val();
	
	if(tagno=="") {	                
		$("#subtag option").each(function() {	
			$("#subtag option:eq(1)").remove();	
		});
		return;
	}
	
	var url="subtag";
	var params="tagno="+tagno;
	
	$.ajax({
		type:"post"
		,url:url	
		,data:params
		,dataType:"json"})
		.done(function(args){
			$("#subtag option").each(function() {	
				$("#subtag option:eq(0)").remove();
			});

			 for(var idx=0; idx<args.length; idx++) {	
				 $("#subtag").append("<option value='"+args[idx].no+"'>"+args[idx].name+"</option>");	
			 } 
		})
	    .fail(function(e) {
	    	alert(e.responseText);
	    });	
}
</script>
<form action="insertPost" method="post" enctype="multipart/form-data">
	test user id : <input name="user_id">
	<select id="tag" name="category_first" onchange="selectTag()">
  		<option value="">::��з�::</option>
	</select>
	<select id="subtag" name="category_second">
  		<option value="">::�Һз�::</option>
	</select>
		
	thumbnail : <input type="file" name="report" method="post" >
	title : <input type="text" name="title">
	desc : <input type="text" name="description">
	price : <input type="nubmer" name="price">
	worktime : <input type="number" name="worktime"> �� �� ���Է½� �⺻ 14�� 
	retouch : <input type="number" name="retouch_count">
	serv description : <input type="text" name="service_description">
	�䱸���� : <input type="text" name="requirement">
	
	<input type="submit" value="����">
	<input type="reset" value="�ʱ�ȭ">
</form>
</body>
</html>