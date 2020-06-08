<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách hóa đơn</title>

</head>
<body>
	<jsp:include page="DauTrangAdmin.jsp"/>
	<fmt:setLocale value="vi_VN"/>
	<div class="container">
		<h1 style="text-align: center;margin-top: 30px">Danh sách hóa đơn</h1>
  			<input id="timKiem" class="form-control form-control-sm ml-3 w-100" type="number" placeholder="Search"
   				aria-label="Search">
				
		<div class="row" style="margin-top: 50px;margin-left: -200px;margin-right: -200px">
		
			<div  class="col-md-2">
			<div id="lamMoi" style="text-align: right;">Làm mới</div>
				<div>Thời gian</div>
				
				<div>
					<input id="all1" name="thoiGian" data-value="0" checked  class="rdThoiGian" type="radio">Tất cả</input><br/>
					<input id="check2" name="thoiGian" data-value="1" class="rdThoiGian" type="radio">Lựa chọn khác</input><br/>
					<div id="ngay"  style="display: none; margin: 20px">
						<span>Ngày bắt đầu: </span>
						<input  class="form-control" type="date" id="ngayDatDau" placeholder="Ngày bắt đâu">
						<span>Ngày kết thúc: </span>
						<input  class="form-control" type="date" id="ngayKetThuc" placeholder="Ngày bắt đâu">
					</div>
				</div>
				
				<div>Trạng thái</div>
				<div>
					<input id="all2" name="trangThai" data-value="2" checked  class="rdTrangThai" type="radio">Tất cả</input><br/>
					<input name="trangThai" data-value="1" class="rdTrangThai" type="radio">Đã gửi</input><br/>
					<input name="trangThai" data-value="0" class="rdTrangThai" type="radio">Chưa gửi</input><br/>
					<input name="trangThai" data-value="2" class="rdTrangThai" type="radio">Đã hủy</input><br/>
				</div>
			</div>
			<c:choose>
				<c:when test="${danhSachHoaDonDTO.getHoaDonDTOs() == null }">
					<div class="col-md-8">
						<div style="text-align: center">Không có hóa đơn</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="col-md-10">
					<div style="text-align: center">Danh sách hóa đơn</div>
					<table class="table">
					<thead>
					<tr>
						<td>ID</td>
						<td>Tên người nhận</td>
						<td>Địa chỉ</td>
						<td>Số điện thoại</td>
						<td>Ngày mua</td>
						<td>Tổng tiền</td>
						<td>
						<input id="chkAll" type="checkbox" />
						Trạng thái</td>
						</tr>
					</thead>
						<tbody id="danhSachHoaDon">
							
							<c:forEach items="${danhSachHoaDonDTO.getHoaDonDTOs() }" var="i">
								<c:set var="tongTien" value="0"></c:set>
								<tr class="hang" data-value="${i.getId() }">
									<td class="a">${i.getId() }</td>
									<td class="a">${i.getTenNguoiNhan() }</td>
									<td class="a">${i.getDiaChi() }</td>
									<td class="a">${i.getSdt() }</td>
									<td class="a">
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
									<td class="a"><fmt:formatNumber value="${tongTien }" /> d</td>
									
									<td>
										
										<c:choose>
											<c:when test="${i.getTrangThai() == 1 }">
												<input class="chkTrangThai" type="checkbox" checked="checked" />
											</c:when>
											<c:when test="${i.getTrangThai() == 0 }">
												<input class="chkTrangThai" type="checkbox" /> 
											</c:when>
											<c:otherwise>
												<span>Đã hủy</span>
											</c:otherwise>
										</c:choose>
									</td>
								</tr>					
							</c:forEach>
						</tbody>
					</table>
					<nav aria-label="Page navigation example" style="margin: 0 auto">
						  <ul  class="pagination justify-content-center">
						    <li style="display: none;" id="truoc" class="page-item"><a style="color: #343a40" class="page-link">Trước</a></li>
						    <li  class="page-item"><a id="hienTai" style="color: #343a40" class="page-link">1</a></li>
						    <c:if test="${danhSachHoaDonDTO.isCheck() }">
								<li id="sau" class="page-item"><a style="color: #343a40" class="page-link">Sau</a></li>
							</c:if>
						    
						  </ul>
					</nav>
					</div>
					
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<script type="text/javascript" src='<c:url value="/resources/js/DanhSachHoaDonAdmin.js" />'></script>
</body>
</html>