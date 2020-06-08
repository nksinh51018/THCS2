
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thông tin tài khoản</title>

</head>
<body>
	<jsp:include page="DauTrang.jsp"/>
	<div class="container" style="margin-top: 5%">
		<div>
				<h3 style="text-align: center;">Thông tin tài khoản</h3>
			</div>
			<div style="margin-top: 30px;margin-bottom: 10px">Tên người dùng: </div>
			<input data-vaiTro="${taiKhoanDTO.getVaiTro() }" data-value="${taiKhoanDTO.getId() }" class="form-control" type="text" placeholder="Tên người dùng" id="tenNguoiDung" value="${taiKhoanDTO.getTen() }"/>
			<span id="errorTenNguoiDung" style="color: red"></span>
			<br/>
			<span style="margin-top: 30px;margin-bottom: 10px">Tên đăng nhập: </span>
			<input class="form-control" type="text" placeholder="Tên đăng nhập" id="tenDangNhap" disabled value="${taiKhoanDTO.getTenDangNhap() }"/>
			<span id="errorTenDangNhap" style="color: red"></span>
			<br/>
			<span style="margin-top: 30px;margin-bottom: 10px">Địa chỉ: </span>
			<input class="form-control" type="text" placeholder="Địa chỉ" id="diaChi" value="${taiKhoanDTO.getDiaChi() }"/>
			<span id="errorDiaChi" style="color: red"></span>
			<br/>
			<span style="margin-top: 30px;margin-bottom: 10px;">Giới tính: </span>
			<div class="dropdown" style="width: 100%;">
				  <button data-value="${taiKhoanDTO.getGioiTinh() }" id="btnGioiTinh" class="btn btn-light dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
				  style="border: 1px solid rgba(0, 0, 0, .2);width: 100%;text-align: left;">
				  	<c:choose>
				  		<c:when test="${taiKhoanDTO.getGioiTinh()==0 }">
				  			Nam
				  		</c:when>
				  		<c:otherwise>
				  			Nữ
				  		</c:otherwise>
				  	</c:choose>
				  </button>
				  <div id="selectGioTinh" class="dropdown-menu" aria-labelledby="dropdownMenuButton" style="width: 100%" date-value = "${taiKhoanDTO.getId() }">
				    <div class="dropdown-item" id="gioiTinhNam">Nam</div>
				    <div class="dropdown-item" id="gioiTinhNu">Nữ</div>
				  </div>
				</div>
			<span id="errorGioTinh" style="color: red"></span>
			<br/>
			<span style="margin-top: 30px;margin-bottom: 10px">Số điện thoại: </span>
			<input class="form-control" type="number" placeholder="Số điện thoại" id="sdt"  value="${taiKhoanDTO.getSdt() }"/>
			<span id="errorSdt" style="color: red"></span>
			<br/>
			<span style="margin-top: 30px;margin-bottom: 10px"">Ngày sinh: </span>
			<input class="form-control" type="date" placeholder="Ngày sinh" id="ngaySinh"  value="${taiKhoanDTO.getNgaySinh() }"/>
			<span id="errorNgaySinh" style="color: red"></span>
			<br/>
			<button style="margin-top: 30px;width: 100%" class="btn btn-dark" id= "btnSuaThongTin" style="width: 100%">Sửa thông tin</button>
			<a href='<c:url value="/user/doiMatKhau" />'>
				<button style="margin-top: 30px;width: 100%" class="btn btn-dark" id= "btnThayDoiMatKhau" style="width: 100%">Thay đổi mật khẩu</button>
			</a>
			<a href='<c:url value="/user/danhSachHoaDon" />'>
				<button style="margin-top: 30px;width: 100%" class="btn btn-dark" id= "btnThongTinDonHang" style="width: 100%">Xem thông tin đơn hàng</button>
			</a>
		</div>
	
		<script type="text/javascript" src='<c:url value="/resources/js/ThongTin.js" />'></script>
	
</body>
</html>