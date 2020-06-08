<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Giỏ hàng</title>

</head>
<body>
	<jsp:include page="DauTrang.jsp"/>
	<fmt:setLocale value="vi_VN"/>
	<div class="container">
		<h1 style="text-align: center;margin-top: 30px">Giỏ hàng</h1>
		<div class="row" style="margin-top: 50px;margin-left: -200px;margin-right: -200px">
			<div class="col-md-4">
				<div style="text-align: center">THÔNG TIN GIAO HÀNG</div>
				<img alt="" src='<c:url value="/resources/image/so1.png" />' style="background-size: cover;width: 100%;margin-bottom: 10px;margin-top: 10px;">
				<div class="dropdown">
				  <button id="btnDiachi" class="btn btn-light dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
				  style="border: 1px solid;width: 100%">
				    ${taiKhoanDTO.getDiaChi() }
				  </button>
				  <div id="selectDiaChi" class="dropdown-menu" aria-labelledby="dropdownMenuButton" style="width: 100%" date-value = "${taiKhoanDTO.getId() }">
				    <div class="dropdown-item" id="diachicu">${taiKhoanDTO.getDiaChi() }</div>
				    <div class="dropdown-item" id="diachimoi">Địa chỉ mới</div>
				  </div>
				  <div id="formDiaChi" style="display: none">
				  	<div class="form-group">
					   <label for="txtTen">Tên: </label>
					    <input type="text" data="${taiKhoanDTO.getId() }" name="ten" class="form-control" id="txtTen" aria-describedby="tenHelp" placeholder="Nhập tên" value="${taiKhoanDTO.getTen() }">
					    <small id="tenHelp" class="form-text text-muted"></small>
					</div>
					<div class="form-group">
					   <label for="txtSDT">Số điện thoại: </label>
					    <input type="tel" name="sdt" class="form-control" id="txtSDT" aria-describedby="sdtHelp" placeholder="Nhập số điện thoại" value="${taiKhoanDTO.getSdt() }">
					    <small id="sdtHelp" class="form-text text-muted"></small>
					</div>
					<div class="form-group">
					   <label for="txtDiaChi">Địa chỉ: </label>
					    <input type="text" name="diaChi" class="form-control" id="txtDiaChi" aria-describedby="diaChiHelp" placeholder="Nhập địa chỉ" value="${taiKhoanDTO.getDiaChi() }">
					    <small id="diaChiHelp" class="form-text text-muted"></small>
					</div>
				  </div>
				</div>
			</div>
			<c:choose>
				<c:when test="${giohang == null }">
					<div class="col-md-8">
						<div style="text-align: center">Không hàng trong giỏ hàng</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="col-md-8">
					<div style="text-align: center">THÔNG TIN ĐƠN HÀNG</div>
					<img alt="" src='<c:url value="/resources/image/so2.png" />' style="background-size: cover;width: 100%;margin-bottom: 10px;margin-top: 10px;">	
					<table class="table">
						<tbody>
							<c:set var="soLuong" value="0"></c:set>
							<c:set var="tongTien" value="0"></c:set>
							<c:forEach var="i" items="${giohang }">
								<c:set var="soLuong" value="${soLuong+i.getSoLuong() }"></c:set>
								<c:set var="tongTien" value="${tongTien + i.getSoLuong() * i.getGia() * (100 - i.getKhuyenMai()) / 100 } "></c:set>
								<tr class="hang">
									<td><img
											class="sanPham"
											data="${i.getIdSanPham() }"
											src='<c:url value="http://localhost:8762/hinhAnh/image/${i.getUrl() }" />'
											style="background-size: cover;width: 100px;"/></td>
									<td>
										<a style="color: black;" href='<c:url value="/chiTiet/${i.getIdSanPham() }" />'><span data="${i.getTenSanPham() }">Tên sản phẩm: ${i.getTenSanPham() }</span></a><br/>
										<div>Màu: <button class="btn btnMau mau" style="background-color: #${i.getMaMau()};width: 100px;height: 25px;margin: 2px;box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);" data-maMau="${i.getMaMau() }" data-tenMau="${i.getTenMau() }" data-value="${i.getIdMau() }"></button></div>
										<span data="${i.getSize() }" class="size">Size: ${i.getSize() }</span><br/>
									</td>
									<td><span class="soLuong_chitiet">
										<input value="${i.getSoLuong() }" class="form-control soLuong" type="number" min="0" style="text-align: right;"/>
									</span></td>
							
									<td>
										<span class="gia" data-value="${i.getGia() * (100 - i.getKhuyenMai()) / 100 }"><fmt:formatNumber value="${i.getGia() * (100 - i.getKhuyenMai()) / 100 }" /> d</span><br/>
										<c:if test="${i.getKhuyenMai() != 0 }">
											<p class="card-text"><strike style="color: red"><fmt:formatNumber value = "${i.getGia() }" /> d</strike> - <fmt:formatNumber value = "${ i.getKhuyenMai() }" /> %</p>
										</c:if>
										
									</td>
									<!-- <td><span class="tongTien_chitiet"> ${i.getSoLuong() * i.getGia() * (100 - i.getKhuyenMai()) / 100 }</span></td> -->
									
									<td><span class="xoa-dh">xóa</span></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<span id="soluong-dh" data="${soLuong }">Số lượng: ${soLuong }</span><br/>
					<span id="tongTien-dh" data="${tongTien }">Tổng tiền: <fmt:formatNumber value = "${tongTien }" /> d</span></br/>
					<button id="dathang" class="btn btn-dark" style="width: 100%">Đặt hàng</button>
			</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<script type="text/javascript" src='<c:url value="/resources/js/GioHang.js" />'></script>
</body>
</html>