<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách khuyến mại</title>

</head>
<body>
	<jsp:include page="DauTrangAdmin.jsp"/>
	<fmt:setLocale value="vi_VN"/>
	<div class="container">
		<h1 style="text-align: center;margin-top: 30px">Danh sách khuyến mại</h1>
  			<input id="timKiem" class="form-control form-control-sm ml-3 w-100" type="text" placeholder="Search"
   				aria-label="Search">
				<div class="row" style="margin-top: 50px;margin-left: -200px;margin-right: -200px">
				<div class="col-md-6">
				<button id="lamMoi" class="btn btn-dark" style="width: 100%">Làm mới</button>
				</div>
				<div class="col-md-6">
				<a href='<c:url value="/admin/themKhuyenMai" />'>
				<button id="themKhuyenMai" class="btn btn-dark" style="width: 100%">Thêm khuyến mại</button>
				</a>
				</div>
				</div>
		<div class="row" style="margin-top: 50px;margin-left: -200px;margin-right: -200px">
			<div class="col-md-2">
				<div>Thời gian</div>
				
				<div>
					<input id="all" name="thoiGian" data-value="0" checked  class="rdThoiGian" type="radio">Tất cả</input><br/>
					<input name="thoiGian" data-value="1" class="rdThoiGian" type="radio">Còn hạn</input><br/>
					<input name="thoiGian" data-value="2" class="rdThoiGian" type="radio">Hết hạn</input>
				</div>
			</div>
			<c:choose>
				<c:when test="${danhSachKhuyenMaiDTO.getKhuyenMaiDTOs() == null }">
					<div class="col-md-8">
						<div style="text-align: center">Không có khuyến mại</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="col-md-10">
					<div style="text-align: center">Danh sách khuyến mại</div>
					<table class="table">
						<thead>
							<tr>
								<td>ID</td>
								<td>Tên khuyến mại</td>
								<td>Ngày bắt đầu</td>
								<td>Ngày kết thúc</td>
								<td>Phần trăm</td>
							</tr>
						</thead>
						<tbody id="bang">
							<c:forEach var="i" items="${danhSachKhuyenMaiDTO.getKhuyenMaiDTOs() }">
								<tr data-value="${i.getId() }" class="hang">
									<td> ${i.getId() }</td>
									<td> ${i.getTenKhuyenMai() }</td>
									<td>
										<c:choose>
											<c:when test="${i.getNgayBatDau().getDayOfMonth()<10 }">
												0${i.getNgayBatDau().getDayOfMonth() }
											</c:when>
											<c:otherwise>
												${i.getNgayBatDau().getDayOfMonth() } 
											</c:otherwise>
										</c:choose>
										-
										<c:choose>
											<c:when test="${i.getNgayBatDau().getMonthValue()<10 }">
												0${i.getNgayBatDau().getMonthValue() }
											</c:when>
											<c:otherwise>
												${i.getNgayBatDau().getMonthValue() } 
											</c:otherwise>
										</c:choose>
										-${i.getNgayBatDau().getYear() }
									</td>
									<td>
										<c:choose>
											<c:when test="${i.getNgayKetThuc().getDayOfMonth()<10 }">
												0${i.getNgayKetThuc().getDayOfMonth() }
											</c:when>
											<c:otherwise>
												${i.getNgayKetThuc().getDayOfMonth() } 
											</c:otherwise>
										</c:choose>
										-
										<c:choose>
											<c:when test="${i.getNgayKetThuc().getMonthValue()<10 }">
												0${i.getNgayKetThuc().getMonthValue() }
											</c:when>
											<c:otherwise>
												${i.getNgayKetThuc().getMonthValue() } 
											</c:otherwise>
										</c:choose>
										-${i.getNgayKetThuc().getYear() }
									</td>
									<td>${i.getPhanTram() }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<nav aria-label="Page navigation example" style="margin: 0 auto">
						  <ul  class="pagination justify-content-center">
						    <li style="display: none;" id="truoc" class="page-item"><a style="color: #343a40" class="page-link">Trước</a></li>
						    <li  class="page-item"><a id="hienTai" style="color: #343a40" class="page-link">1</a></li>
						    <c:if test="${danhSachSanPhamAdmin.getDanhSachTimKiemDTO().getDanhSachSanPhamDTO().isCheck() == true }">
								<li id="sau" class="page-item"><a style="color: #343a40" class="page-link">Sau</a></li>
							</c:if>
						    
						  </ul>
					</nav>
			</div>
				
				
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<script type="text/javascript" src='<c:url value="/resources/js/DanhSachKhuyenMaiAdmin.js" />'></script>
</body>
</html>