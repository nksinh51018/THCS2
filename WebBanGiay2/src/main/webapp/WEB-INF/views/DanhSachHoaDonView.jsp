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
	<jsp:include page="DauTrang.jsp"/>
	<fmt:setLocale value="vi_VN"/>
	<div class="container">
		<h1 style="text-align: center;margin-top: 30px">Danh sách hóa đơn</h1>
		<div class="row" style="margin-top: 50px;margin-left: -200px;margin-right: -200px">
			<c:choose>
				<c:when test="${danhSachHoaDonDTO.getHoaDonDTOs().size() == 0 }">

						<div style="text-align: center">Không hàng trong giỏ hàng</div>
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
											<c:otherwise>
												Chưa gửi
											</c:otherwise>
										</c:choose>
									</td>
								</tr>					
							</c:forEach>
						</tbody>
					</table>
					
				</c:otherwise>
			</c:choose>
			<nav aria-label="Page navigation example" style="margin: 0 auto">
						  <ul  class="pagination justify-content-center">
						    <li style="display: none;" id="truoc" class="page-item"><a style="color: #343a40" class="page-link">Trước</a></li>
						    <li  class="page-item"><a id="hienTai" style="color: #343a40" class="page-link">1</a></li>
						    <c:if test="${danhSachHoaDonDTO.isCheck() && danhSachHoaDonDTO.getHoaDonDTOs().size() != 0 }">
								<li id="sau" class="page-item"><a style="color: #343a40" class="page-link">Sau</a></li>
							</c:if>
						    
						  </ul>
					</nav>
		</div>
	</div>
	<script type="text/javascript" src='<c:url value="/resources/js/DanhSachHoaDon.js" />'></script>
</body>
</html>