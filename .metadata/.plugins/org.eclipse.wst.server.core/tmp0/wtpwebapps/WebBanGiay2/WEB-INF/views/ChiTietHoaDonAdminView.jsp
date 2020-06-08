<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiết hóa đơn</title>

</head>
<body>
	<jsp:include page="DauTrangAdmin.jsp"/>
	<fmt:setLocale value="vi_VN"/>
	<div class="container">
		<h1 id="id" data-value="${hoaDonDTO.getId() }" style="text-align: center;margin-top: 30px">Chi tiết hóa đơn: ${hoaDonDTO.getId() } -</h1> 
			
			<c:choose>
				<c:when test="${hoaDonDTO.getTrangThai() == 1 }">
					<div style="display: block;
  					  position: relative;
					  
					  cursor: pointer;
					  font-size: 22px;"
				>
    			<input checked="checked" id="chk" data-value="${hoaDonDTO.getId() }" type="checkbox" style="position: absolute;
											  top: -40px;
											  right: 320px;
											  height: 25px;
											  width: 25px;
											  background-color: #eee;">
											  </div>
				</c:when>
				<c:otherwise>
					<div style="display: block;
  					  position: relative;
					  
					  cursor: pointer;
					  font-size: 22px;"
				>
    			<input id="chk" data-value="${hoaDonDTO.getId() }" type="checkbox" style="position: absolute;
											  top: -40px;
											  right: 320px;
											  height: 25px;
											  width: 25px;
											  background-color: #eee;">
											  </div>
				</c:otherwise>
			</c:choose> 

		<h3 style="margin-left: -200px;">Thông tin người nhận</h3>
		<div class="row" style="margin-left: -200px;margin-right: -200px">
					
					<div style="width: 100%">Tên người nhận: ${hoaDonDTO.getTenNguoiNhan() }</div>
					<div style="width: 100%">Địa chỉ: ${hoaDonDTO.getDiaChi() }</div>
					<div style="width: 100%">Số điện thoại: ${hoaDonDTO.getSdt() }</div>
					<div style="width: 100%">Ngày mua: 
						<c:choose>
											<c:when test="${hoaDonDTO.getNgayMua().getDayOfMonth()<10 }">
												0${hoaDonDTO.getNgayMua().getDayOfMonth() }
											</c:when>
											<c:otherwise>
												${hoaDonDTO.getNgayMua().getDayOfMonth() } 
											</c:otherwise>
										</c:choose>
										-
										<c:choose>
											<c:when test="${hoaDonDTO.getNgayMua().getMonthValue()<10 }">
												0${hoaDonDTO.getNgayMua().getMonthValue() }
											</c:when>
											<c:otherwise>
												${hoaDonDTO.getNgayMua().getMonthValue() } 
											</c:otherwise>
										</c:choose>
										-${hoaDonDTO.getNgayMua().getYear() }
					</div>
					<table class="table" style="margin-top: 50px">
					<thead>
						<tr>
							<td>Hình ảnh</td>
							<td>Thông tin sản phẩm</td>
							<td>Số lượng</td>
							<td>Giá</td>
						</tr>
					</thead>
						<tbody>
							<c:set var="soLuong" value="0"></c:set>
							<c:set var="tongTien" value="0"></c:set>
							<c:forEach var="i" items="${hoaDonDTO.getChiTietHoaDons() }">
								<c:set var="soLuong" value="${soLuong+i.getSoLuong() }"></c:set>
								<c:set var="tongTien" value="${tongTien + i.getSoLuong() * i.getGia() }"></c:set>
								<tr class="hang">
									<td><img
											class="sanPham"
											data="${i.getIdSanPham() }"
											src='<c:url value="http://localhost:8762/hinhAnh/image/${i.getUrl() }" />'
											style="background-size: cover;width: 100px;"/></td>
									<td>
									<a style="color: black;" href='<c:url value="/chiTiet/${i.getIdSanPham() }" />'>
										<span data="${i.getTenSanPham() }">Tên sản phẩm: ${i.getTenSanPham() }</span>
										</a><br/>
										<div>Màu: <button class="btn btnMau mau" style="background-color: #${i.getMauDTO().getMaMau()};width: 100px;height: 25px;margin: 2px;box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);" data-maMau="${i.getMauDTO().getMaMau() }" data-tenMau="${i.getMauDTO().getTenMau() }" data-value="${i.getMauDTO().getId() }"></button></div>
										<span data="${i.getSize() }" class="size">Size: ${i.getSize() }</span><br/>
									</td>
									<td><span class="soLuong_chitiet">
										${i.getSoLuong() }
									</span></td>
							
									<td>
										<span class="gia" data-value="${i.getGia()  }"><fmt:formatNumber value="${i.getGia()  }" /> d</span><br/>
									</td>
									
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<span style="width: 100%" id="soluong-dh" data="${soLuong }">Số lượng: ${soLuong }</span><br/>
					<span id="tongTien-dh" data="${tongTien }">Tổng tiền: <fmt:formatNumber value = "${tongTien }" /> d</span></br/>
					<c:if test="${hoaDonDTO.getTrangThai() == 0 }">
						<button id="huyHoaDon" type="button" class="btn btn-dark" style="width: 100%;margin-bottom: 100px;margin-top: 50px">Hủy hóa đơn</button>
					</c:if>
					
			</div>
				
	</div>
	<script type="text/javascript" src='<c:url value="/resources/js/ChiTietHoaDonAdmin.js" />'></script>
</body>
</html>