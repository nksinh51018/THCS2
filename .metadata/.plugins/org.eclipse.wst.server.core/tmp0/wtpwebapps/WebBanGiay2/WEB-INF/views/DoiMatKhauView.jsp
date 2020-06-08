
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đổi mật khẩu</title>

</head>
<body>
	<jsp:include page="DauTrang.jsp"/>
	<div class="container" style="margin-top: 5%">
		<div>
				<h3 style="text-align: center;">Đổi mật khẩu</h3>
			</div>
			<div style="margin-top: 30px;margin-bottom: 10px">Mật khẩu cũ: </div>
			<input class="form-control" type="password" placeholder="Mật khẩu cũ" id="matKhauCu" />
			<span id="errorMatKhauCu" style="color: red"></span>
			<br/>
			<span style="margin-top: 30px;margin-bottom: 10px">Mật khẩu mới: </span>
			<input class="form-control" type="password" placeholder="Mật khẩu mới" id="matKhauMoi"/>
			<span id="errorMatKhauMoi" style="color: red"></span>
			<br/>
			<span style="margin-top: 30px;margin-bottom: 10px">Nhập lại mật khẩu: </span>
			<input class="form-control" type="password" placeholder="Nhập lại mật khẩu" id="nhapLaiMatKhau"/>
			<span id="errorNhapLaiMatKhau" style="color: red"></span>
			<br/>
			
			<button style="margin-top: 30px;width: 100%" class="btn btn-dark" id= "btnDoiMatKhau" style="width: 100%">Đổi mật khẩu</button>
		</div>
	
		<script type="text/javascript" src='<c:url value="/resources/js/DoiMatKhau.js" />'></script>
	
</body>
</html>