<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách sản phẩm</title>

</head>
<body>
	<jsp:include page="DauTrangAdmin.jsp"/>
	<fmt:setLocale value="vi_VN"/>
	<div class="container">
		<h1 style="text-align: center;margin-top: 30px">Danh sách sản phẩm</h1>
  			<input id="timKiem" class="form-control form-control-sm ml-3 w-100" type="text" placeholder="Search"
   				aria-label="Search">
   			<div style="width: 100%;margin-left: 1rem">Số lượng sản phẩm: ${danhSachSanPhamAdmin.getSoLuongSanPham() }</div>
				<div style="margin-left: 1rem">Số lượng sản phẩm trong kho: ${danhSachSanPhamAdmin.getSoLuongHang() }</div>
				<div class="row" style="margin-top: 50px;margin-left: -200px;margin-right: -200px">
				<div class="col-md-6">
				<button id="lamMoi" class="btn btn-dark" style="width: 100%">Làm mới</button>
				</div>
				<div class="col-md-6">
				<a href='<c:url value="/admin/themSanPham" />'>
				<button id="themSanPham" class="btn btn-dark" style="width: 100%">Thêm sản phẩm</button>
				</a>
				</div>
				</div>
		<div class="row" style="margin-top: 50px;margin-left: -200px;margin-right: -200px">
			<div class="col-md-2">
				<div>Danh mục</div>
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
					<!--<c:if test="${danhSachSanPhamAdmin.getDanhSachTimKiemDTO().getDanhSachSanPhamDTO().isCheck() == true }">
						<button id="xemThem" class="btn btn-dark" style="width: 100%">Xem thêm</button>
					</c:if> -->
			</div>
				
				
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<script type="text/javascript" src='<c:url value="/resources/js/DanhSachSanPhamAdmin.js" />'></script>
</body>
</html>