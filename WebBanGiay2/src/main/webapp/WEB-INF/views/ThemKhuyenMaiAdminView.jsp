
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm khuyến mãi</title>

</head>
<body>
	<jsp:include page="DauTrangAdmin.jsp"/>
	<fmt:setLocale value="vi_VN"/>
	<div class="container" style="margin-top: 5%">
		<div>
				<h3 style="text-align: center;">Thông tin khuyến mãi</h3>
			</div>
			<div style="margin-top: 30px;margin-bottom: 10px">Tên khuyến mãi: </div>
			<input class="form-control" type="text" placeholder="Tên khuyến mãi" id="tenKhuyenMai" />
			<span id="errorTenNguoiDung" style="color: red"></span>

			<div style="margin-top: 30px;margin-bottom: 10px">Ngày bắt đầu: </div>
			<input class="form-control" type="date" id="ngayBatDau" />
			<span id="errorTenNguoiDung" style="color: red"></span>
			<div style="margin-top: 30px;margin-bottom: 10px">Ngày kết thúc: </div>
			<input class="form-control" type="date" id="ngayKetThuc" />
			<span id="errorTenNguoiDung" style="color: red"></span>
			<div style="margin-top: 30px;margin-bottom: 10px">Phần trăm: </div>
			<input class="form-control" type="number" id="phanTram" />
			<span id="errorTenNguoiDung" style="color: red"></span>
			<div class="container">
			<h1 style="text-align: center;">Danh sách sản phẩm</h1>
		<input id="timKiem" class="form-control form-control-sm ml-3 w-100" type="text" placeholder="Search"
   				aria-label="Search">
		<div class="row" style="margin-top: 50px;margin-left: -200px;margin-right: -200px">
			<div class="col-md-2">
				<div id="lamMoi" style="text-align: right;">Làm mới</div>
				<div>Khuyến mãi</div>
				<div>
					<input id="all2" name="khuyenMai" data-value="0" checked  class="rdKhuyenMai" type="radio">Tất cả</input><br/>
					<input name="khuyenMai" data-value="1" class="rdKhuyenMai" type="radio">Trong khuyến mãi</input><br/>
					
				</div>
				
				<div style="margin-top: 30px">Danh mục</div>
				<div>
					<input id="all" name="danhMuc" data-value="0" checked  class="rdDanhMuc" type="radio">Tất cả</input><br/>
					<input name="danhMuc" data-value="1" class="rdDanhMuc" type="radio">Nam</input><br/>
					<input name="danhMuc" data-value="2" class="rdDanhMuc" type="radio">Nữ</input><br/>
					<input name="danhMuc" data-value="3" class="rdDanhMuc" type="radio">Trẻ em</input><br/>
				</div>
				
				<div style="margin-top: 30px">Màu</div>
				<div>
					<c:forEach var="i" items="${danhSachSanPhamAdmin.getDanhSachTimKiemDTO().getDanhSachMauDTO().getMauDTOs() }">
							<button class="btn btnMau" style="background-color: #${i.getMaMau()};width: 100%;box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);height: 25px;margin: 2px;" data-maMau="${i.getMaMau() }" data-value="${i.getId() }"></button>
						</c:forEach>
				</div>
				
				<div style="margin-top: 30px">Size</div>
				<div>
					<c:forEach var="i" items="${danhSachSanPhamAdmin.getDanhSachTimKiemDTO().getDanhSachSizeDTO().getSizes() }">
							<button class="btn btnSize" style="width:100%;margin: 2px;background-color: white;box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);" data-text="${i }">${i}</button>
					</c:forEach>
				</div>
				
				<div style="margin-top: 30px">Giá</div>
				<div>
					<c:forEach var="i" begin="1" end="${danhSachSanPhamAdmin.getDanhSachTimKiemDTO().getGiaDTO().getMaxGia() }" step="500000">
							<input name="gia" class="chkGia" type="radio" data-min=${i } data-max=${i+499999 }><fmt:formatNumber value = "${i }" /> d - <fmt:formatNumber value = "${i+499999 }" /> d<br/>
						</c:forEach>
				</div>
			</div>
			<c:choose>
				<c:when test="${danhSachSanPhamAdmin.getDanhSachTimKiemDTO().getDanhSachSanPhamDTO().getSanPhamDTOs() == null }">
					<div class="col-md-8">
						<div style="text-align: center">Không có sản phẩm</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="col-md-10">
					<div style="text-align: center">Danh sách sản phẩm</div>
					<table class="table">
						<thead>
							<tr>
								<td>ID</td>
								<td>Tên sản phẩm</td>
								<td>Giá tiền</td>
								<td>Chất liệu</td>
								<td>Lượt xem</td>
								<td>Khuyến mại</td>
								<td>Loại sản phẩm</td>
								<td>Hình ảnh</td>
								<td>
										<input type="checkbox" id="chkAll" />
									</td>
							</tr>
						</thead>
						<tbody id="bang">
							<c:forEach var="i" items="${danhSachSanPhamAdmin.getDanhSachTimKiemDTO().getDanhSachSanPhamDTO().getSanPhamDTOs() }">
								<tr data-value="${i.getId() }" class="hang">
									<td> ${i.getId() }</td>
									<td> ${i.getTenSanPham() }</td>
									<td> ${i.getGiaTien() }</td>
									<td> ${i.getChatLieu() }</td>
									<td> ${i.getLuotXem() }</td>
									<td> ${i.getKhuyenMai() }</td>
									<td> ${i.getLoaiSanPhamDTO().getTenLoai() }</td>
									<td>
										<img
											class="sanPham"
											data="${i.getId() }"
											src='<c:url value="http://localhost:8762/hinhAnh/image/${i.getHinhAnhDTOs().get(0).getUrl() }" />'
											style="background-size: cover;width: 100px;"/>
									</td>
									<td>
										<input class="chkItem" type="checkbox" />
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
			</div>
				
				
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<nav aria-label="Page navigation example" style="margin: 0 auto">
						  <ul  class="pagination justify-content-center">
						    <li style="display: none;" id="truoc" class="page-item"><a style="color: #343a40" class="page-link">Trước</a></li>
						    <li  class="page-item"><a id="hienTai" style="color: #343a40" class="page-link">1</a></li>
						   <c:if test="${danhSachSanPhamAdmin.getDanhSachTimKiemDTO().getDanhSachSanPhamDTO().isCheck() == true }">
								<li id="sau" class="page-item"><a style="color: #343a40" class="page-link">Sau</a></li>
							</c:if>
						    
						  </ul>
					</nav>
			<br/>
			<button id="btnThemKhuyenMai" type="button" class="btn btn-dark" style="width: 100%;margin-bottom: 20px;margin-top: 50px">Thêm khuyến mãi</button>

			
		</div>
	
		<script type="text/javascript" src='<c:url value="/resources/js/ThemKhuyenMaiAdmin.js" />'></script>
	
</body>
</html>