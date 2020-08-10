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
		$.ajax({
			type : "post",
			url : "couponView",
			dataType : "json"
		}).done(
				function(args) { //������ ���� ���� �ڵ带 ��ȯ�ϸ� ȣ��Ǵ� �Լ�
					if (args.length == 0) {
						$("#coupon").append("<div> ������ �����ϴ�. �������ּ��� ~ </div>");

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
											+ "<a href='couponDelete?no="+args[i].no+"'>����</a>"
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
		���� �̸� : <input type="text" name="coupon_name"> ���� ���� : <input
			type="number" name="coupon_price"> ��ȿ �Ⱓ : <input type="date"
			name="coupon_validate_date"> <select
			name="coupon_for_everyone">
			<option value="1">�Ѹ���</option>
			<option value="100">��ο���</option>
		</select> <input type="submit" value="����">
	</form>

	���������� ���� ����� �����ִ� ������
	<div>������ȣ �����ڵ� �����̸� �������� �������� ��ȿ�Ⱓ ����? ������ ���̵�</div>
	<div id="coupon"></div>
</body>
</html>