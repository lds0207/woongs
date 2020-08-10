<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>������ ���� ���� ������</title>
</head>
<script src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	$(function() {
		var check = "<%= request.getAttribute("isAvailable") %>";
		if(check == null) {
			console.log("<%= request.getAttribute("isAvailable") %>");
			console.log("null �̴�!");
		} else {
			console.log("<%= request.getAttribute("isAvailable") %>");
			if(check == "success") {
				alert("��� ����");	
			}else if(check == "fail"){
				alert("��� ����");
			}else if(check == "already") {
				alert("�̹� ����� �����Դϴ�.");
			}else if(check == "used") {
				alert("�̹� ���� �����Դϴ�.");
			}
		}
		
		$.ajax({
			type : "post",
			url : "couponView",
			dataType : "json"
		}).done(
				function(args) { //������ ���� ���� �ڵ带 ��ȯ�ϸ� ȣ��Ǵ� �Լ�
					if (args.length == 0) {
						$("#coupon").append("<div> ������ �����ϴ�.</div>");

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
		���� �ڵ� : <input type="text" name="coupon_code"> 
		<input type="submit" value="�������">
	</form>

	���������� ���� �����ִ� ������
	<div>�����̸� �������� ��ȿ�Ⱓ </div>
	<div id="coupon"></div>
</body>
</html>