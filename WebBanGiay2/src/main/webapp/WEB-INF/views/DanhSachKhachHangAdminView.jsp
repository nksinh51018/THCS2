<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách khách hàng</title>

</head>
<body>
	<jsp:include page="DauTrangAdmin.jsp"/>
	<fmt:setLocale value="vi_VN"/>
	<div class="container">
		<h1 style="text-align: center;margin-top: 30px">Danh sách khách hàng</h1>
  			<input id="timKiem" class="form-control form-control-sm ml-3 w-100" type="text" placeholder="Search"
   				aria-label="Search">
				
		<div class="row" style="margin-top: 50px;margin-left: -200px;margin-right: -200px">
		
			<div  class="col-md-2">
			<div id="lamMoi" style="text-align: right;">Làm mới</div>
				<div>Ngày sinh</div>
				
				<div>
					<input id="all1" name="thoiGian" data-value="0" checked  class="rdNgaySinh" type="radio">Tất cả</input><br/>
					<input id="check2" name="thoiGian" data-value="1" class="rdNgaySinh" type="radio">Lựa chọn khác</input><br/>
					<div id="ngay"  style="display: none; margin: 20px">
						<span>Ngày bắt đầu: </span>
						<input  class="form-control" type="date" id="ngayDatDau" placeholder="Ngày bắt đâu">
						<span>Ngày kết thúc: </span>
						<input  class="form-control" type="date" id="ngayKetThuc" placeholder="Ngày bắt đâu">
					</div>
				</div>
				
				<div>Tổng tiền</div>
				
				<div>
					<input id="all2" name="tongTien" data-value="0" checked  class="rdTongTien" type="radio">Tất cả</input><br/>
					<input id="check3" name="tongTien" data-value="1" class="rdTongTien" type="radio">Lựa chọn khác</input><br/>
					<div id="tien"  style="display: none; margin: 20px">
						<span>Từ: </span>
						<input  class="form-control" type="number" id="tienBatDau">
						<span>Đến: </span>
						<input  class="form-control" type="number" id="tienKetThuc">
					</div>
				</div>
			</div>
			<c:choose>
				<c:when test="${danhSachTaiKhoanDTO.getTaiKhoanDTOs() == null }">
					<div class="col-md-8">
						<div style="text-align: center">Không có khách hàng</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="col-md-10">
					<div style="text-align: center">Danh sách khách hàng</div>
					<table class="table">
					<thead>
					<tr>
						<td>ID</td>
						<td>Tên đăng nhập</td>
						<td>Tên</td>
						<td>Giới tính</td>
						<td>Địa chỉ</td>
						<td>SDT</td>
						<td>Ngày sinh</td>
						<td>Tổng tiền</td>
						</tr>
					</thead>
						<tbody id="danhSachKhachHang">
							
							<c:forEach items="${danhSachTaiKhoanDTO.getTaiKhoanDTOs() }" var="i">
								<tr class="hang" data-value="${i.getId() }">
									<td>${i.getId() }</td>
									<td>${i.getTenDangNhap() }</td>
									<td>${i.getTen() }</td>
									
									<td>
										
										<c:choose>
											<c:when test="${i.getGioiTinh() == 1 }">
												Nam
											</c:when>
											<c:when test="${i.getGioiTinh() == 0 }">
												Nữ
											</c:when>
										</c:choose>
									</td>
									<td>${i.getDiaChi() }</td>
									<td>${i.getSdt() }</td>
									<td>
										<c:choose>
											<c:when test="${i.getNgaySinh().getDayOfMonth()<10 }">
												0${i.getNgaySinh().getDayOfMonth() }
											</c:when>
											<c:otherwise>
												${i.getNgaySinh().getDayOfMonth() } 
											</c:otherwise>
										</c:choose>
										-
										<c:choose>
											<c:when test="${i.getNgaySinh().getMonthValue()<10 }">
												0${i.getNgaySinh().getMonthValue() }
											</c:when>
											<c:otherwise>
												${i.getNgaySinh().getMonthValue() } 
											</c:otherwise>
										</c:choose>
										-${i.getNgaySinh().getYear() }
									</td>
									
									<td><fmt:formatNumber value="${i.getTongTien() }" /> d</td>
								</tr>					
							</c:forEach>
						</tbody>
					</table>
					<nav aria-label="Page navigation example" style="margin: 0 auto">
						  <ul  class="pagination justify-content-center">
						    <li style="display: none;" id="truoc" class="page-item"><a style="color: #343a40" class="page-link">Trước</a></li>
						    <li  class="page-item"><a id="hienTai" style="color: #343a40" class="page-link">1</a></li>
						   <c:if test="${danhSachTaiKhoanDTO.isCheck() }">
								<li id="sau" class="page-item"><a style="color: #343a40" class="page-link">Sau</a></li>
							</c:if>
						    
						  </ul>
					</nav>
					</div>
					
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<script type="text/javascript" src='<c:url value="/resources/js/DanhSachKhachHangAdmin.js" />'></script>
</body>
</html>