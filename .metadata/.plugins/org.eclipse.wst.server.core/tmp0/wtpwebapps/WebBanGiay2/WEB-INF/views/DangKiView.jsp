
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng kí</title>

</head>
<body>
	<jsp:include page="DauTrang.jsp"/>
	<div class="container" style="margin-top: 5%">
			<div>
				<h3 style="text-align: center;">Đăng kí tài khoản</h3>
			</div>
			<div style="margin-top: 30px;margin-bottom: 10px">Tên người dùng: </div>
			<input class="form-control" type="text" placeholder="Tên người dùng" id="tenNguoiDung"/>
			<span id="errorTenNguoiDung" style="color: red"></span>
			<br/>
			<span style="margin-top: 30px;margin-bottom: 10px">Tên đăng nhập: </span>
			<input class="form-control" type="text" placeholder="Tên đăng nhập" id="tenDangNhap"/>
			<span id="errorTenDangNhap" style="color: red"></span>
			<br/>
			<span style="margin-top: 30px;margin-bottom: 10px">Mật khẩu: </span>
			<input class="form-control" type="password" placeholder="Mật khẩu" id="matKhau"/>
			<span id="errorMatKhau" style="color: red"></span>
			<br/>
			<span style="margin-top: 30px;margin-bottom: 10px"">Nhập lại mật khẩu: </span>
			<input class="form-control" type="password" placeholder="Nhập lại mật khẩu" id="nhapLaiMatKhau"/>
			<span id="errorNhapLaiMatKhau" style="color: red"></span>
			<br/>
			<span style="margin-top: 30px;margin-bottom: 10px"">Địa chỉ: </span>
			<input class="form-control" type="text" placeholder="Địa chỉ" id="diaChi"/>
			<span id="errorDiaChi" style="color: red"></span>
			<br/>
			<span style="margin-top: 30px;margin-bottom: 10px"">Giới tính: </span>
			<div class="dropdown" style="width: 100%;">
				  <button id="btnGioiTinh" class="btn btn-light dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
				  style="border: 1px solid rgba(0, 0, 0, .2);width: 100%;text-align: left;">
				    Giới tính
				  </button>
				  <div id="selectGioTinh" class="dropdown-menu" aria-labelledby="dropdownMenuButton" style="width: 100%" date-value = "${taiKhoanDTO.getId() }">
				    <div class="dropdown-item" id="gioiTinhNam">Nam</div>
				    <div class="dropdown-item" id="gioiTinhNu">Nữ</div>
				  </div>
				</div>
			<span id="errorGioTinh" style="color: red"></span>
			<br/>
			<span style="margin-top: 30px;margin-bottom: 10px">Số điện thoại: </span>
			<input class="form-control" type="number" placeholder="Số điện thoại" id="sdt"/>
			<span id="errorSdt" style="color: red"></span>
			<br/>
			<span style="margin-top: 30px;margin-bottom: 10px"">Ngày sinh: </span>
			<input class="form-control" type="date" placeholder="Ngày sinh" id="ngaySinh"/>
			<span id="errorNgaySinh" style="color: red"></span>
			<br/>
			<button style="margin-top: 30px;width: 100%" class="btn btn-dark" id= "btnDangKi" style="width: 100%">Đăng kí</button>
		</div>
	

		<script type="text/javascript" src='<c:url value="/resources/js/DangKi.js" />'></script>
	
</body>
</html>