
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiết khách hàng</title>

</head>
<body>
	<jsp:include page="DauTrangAdmin.jsp"/>
	<fmt:setLocale value="vi_VN"/>
	<div class="container" style="margin-top: 5%">
		<div>
				<h3 style="text-align: center;">Thông tin khách hàng</h3>
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
			<button id="btnSuaThongTin" type="button" class="btn btn-dark" style="width: 100%;margin-bottom: 100px;">Sửa thông tin</button>
			
			<c:choose>
				<c:when test="${danhSachHoaDonDTO.getHoaDonDTOs().size() == 0 }">

						<div style="text-align: center">Không hoá đơn</div>
				</c:when>
				<c:otherwise>
					<table class="table">
					<thead>
					<tr>
						<td>ID</td>
						<td>Tên người nhận</td>
						<td>Địa chỉ</td>
						<td>Số điện thoại</td>
						<td>Ngày mua</td>
						<td>Tổng tiền</td>
						<td>Trạng thái</td>
						</tr>
					</thead>
						<tbody id="danhSachHoaDon">
							<c:forEach items="${danhSachHoaDonDTO.getHoaDonDTOs() }" var="i">
								<c:set var="tongTien" value="0"></c:set>
								<tr class="hang" data-value="${i.getId() }">
									<td>${i.getId() }</td>
									<td>${i.getTenNguoiNhan() }</td>
									<td>${i.getDiaChi() }</td>
									<td>${i.getSdt() }</td>
									<td>
										<c:choose>
											<c:when test="${i.getNgayMua().getDayOfMonth()<10 }">
												0${i.getNgayMua().getDayOfMonth() }
											</c:when>
											<c:otherwise>
												${i.getNgayMua().getDayOfMonth() } 
											</c:otherwise>
										</c:choose>
										-
										<c:choose>
											<c:when test="${i.getNgayMua().getMonthValue()<10 }">
												0${i.getNgayMua().getMonthValue() }
											</c:when>
											<c:otherwise>
												${i.getNgayMua().getMonthValue() } 
											</c:otherwise>
										</c:choose>
										-${i.getNgayMua().getYear() }
									</td>
									<c:forEach items="${i.getChiTietHoaDons() }" var="j">
										<c:set var="tongTien" value="${ tongTien+j.getGia() * j.getSoLuong() }"></c:set>
									</c:forEach>
									<td><fmt:formatNumber value="${tongTien }" /> d</td>
									<td>
										<c:choose>
											<c:when test="${i.getTrangThai() == 1 }">
												Đã gửi
											</c:when>
											<c:when test="${i.getTrangThai() == 0 }">
												Chưa gửi
											</c:when>
											<c:otherwise>
												Đã hủy
											</c:otherwise>
										</c:choose>
									</td>
								</tr>					
							</c:forEach>
						</tbody>
					</table>
					
				</c:otherwise>
			</c:choose>
			<c:if test="${danhSachHoaDonDTO.isCheck() && danhSachHoaDonDTO.getHoaDonDTOs().size() != 0 }">
				<button id="xemThem" type="button" class="btn btn-dark" style="width: 100%;margin-bottom: 100px;">Xem thêm</button>
			</c:if>
		</div>
	
		<script type="text/javascript" src='<c:url value="/resources/js/ChiTietKhachHangAdmin.js" />'></script>
	
</body>
</html>