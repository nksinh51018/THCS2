<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiết sản phẩm</title>

</head>
<body>
	<jsp:include page="DauTrang.jsp"/>
	<fmt:setLocale value="vi_VN"/>
	<div class="container" style="margin-top: 5%">
		<h2 id="tenSanPham" data-value="${sanPhamDTO.getId()}" style="text-align: center;">${sanPhamDTO.getTenSanPham() }</h2>
		<div class="row">
			<div class="col-md-6">
				<div style="text-align: right; padding-right: 30px" >
					<div>Chất liệu: ${sanPhamDTO.getChatLieu() }</div>
					<div>Giành cho: ${sanPhamDTO.getLoaiSanPhamDTO().getTenLoai() }</div>
				</div>
			</div>
			
			<div class="col-md-6">
				<div style="padding-left: 30px; padding-top: 15px" >
					<c:choose>
									<c:when test="${sanPhamDTO.getKhuyenMai() != 0 }">
										<div id="gia" data-value="${sanPhamDTO.getGiaTien() }" data-khuyenMai= "${sanPhamDTO.getKhuyenMai()}" class="card-text">Giá: <strike style="color: red"><fmt:formatNumber value = "${sanPhamDTO.getGiaTien() }" /> d</strike> <fmt:formatNumber value = "${((sanPhamDTO.getGiaTien() ) * ( 100- sanPhamDTO.getKhuyenMai()) /100)}" /> d</div>
									</c:when>
									<c:otherwise>
										<div id="gia" data-value="${sanPhamDTO.getGiaTien() }" data-khuyenMai= "${sanPhamDTO.getKhuyenMai()}" class="card-text" data-valu>Giá: <fmt:formatNumber value = "${sanPhamDTO.getGiaTien() }" /> d</div>
									</c:otherwise>
								</c:choose>
				</div>
			</div>
		</div>
		
		<div class="row" style="margin-top: 5%">
			<div class="col-md-10">
				<img id="hinhAnhTo" src='<c:url value="http://localhost:8762/hinhAnh/image/${sanPhamDTO.getHinhAnhDTOs().get(0).getUrl() }" />'
				style="background-size: cover; width: 100%">
			</div>
			<div class="col-md-2">
				<c:forEach var="i" items="${sanPhamDTO.getHinhAnhDTOs() }">
					<div class="hinhAnh" data-value="${i.getUrl() }">
						<img src='<c:url value="http://localhost:8762/hinhAnh/image/${i.getUrl() } " />'
						style="background-size: cover; width: 100%; margin-bottom: 10px;">
					</div>
				</c:forEach>
				
			</div>	
		</div>
		<h4 style="text-align: center;margin-top: 30px" id="textMau">Chọn màu: </h4>
		<div id="danhSachMau" class="row">
			<div style="margin: 0 auto">
				<c:forEach var="i" items="${mauDTOs }">
					<button class="btn btnMau" style="background-color: #${i.getMaMau()};width: 25px;height: 25px;margin: 2px;box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);" data-maMau="${i.getMaMau() }" data-tenMau="${i.getTenMau() }" data-value="${i.getId() }"></button>
				</c:forEach>
			</div>
		</div>
		<h4 style="text-align: center;margin-top: 30px" id="textSize">Chọn size: </h4>
		<div id="danhSachSize" class="row">
			<div style="margin: 0 auto">
				<c:forEach var="i" items="${sizes }">
					<button class="btn btnSize" style="margin: 2px;background-color: white;box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);" data-value="${i }">${i}</button>
				</c:forEach>
				</div>
		</div>
		
		<h4 style="text-align: center;margin-top: 30px">Mô tả: </h4>
		<p style="text-align: center;">${sanPhamDTO.getMoTa() }</p>
		<h4 style="text-align: center;margin-top: 30px">Số lượng: </h4>
		<input id="soLuong" class="form-control" type="number" placeholder="Số lượng" value="1" min="1">
		<button id="themGioHang" type="button" class="btn btn-dark" style="width: 100%;margin-bottom: 100px;margin-top: 50px	">Thêm vào giỏ hàng</button>
		<span style="display: none;" id = "mau-hinhAnh">
		<c:choose>
			<c:when test="${sanPhamDTO.getHinhAnhDTOs().size()-2 >=0}">
				[
			<c:forEach var="i" begin="0" end="${sanPhamDTO.getHinhAnhDTOs().size()-2 }">
					{"mau":"${sanPhamDTO.getHinhAnhDTOs()[i].getIdMau()}","hinhAnh": "${sanPhamDTO.getHinhAnhDTOs()[i].getUrl()}"},
			</c:forEach>
			{"mau":"${sanPhamDTO.getHinhAnhDTOs()[sanPhamDTO.getHinhAnhDTOs().size()-1].getIdMau()}","hinhAnh": "${sanPhamDTO.getHinhAnhDTOs()[sanPhamDTO.getHinhAnhDTOs().size()-1].getUrl()}"}
			]
			</c:when>
			<c:otherwise>
				[
					{"mau":"${sanPhamDTO.getHinhAnhDTOs()[sanPhamDTO.getHinhAnhDTOs().size()-1].getIdMau()}","hinhAnh": "${sanPhamDTO.getHinhAnhDTOs()[sanPhamDTO.getHinhAnhDTOs().size()-1].getUrl()}"}
				]
				
			</c:otherwise>
		</c:choose>
		
		</span><br/>
		<span  style="display: none;" id = "mau-size">
			<c:choose>
				<c:when test="${sanPhamDTO.getChiTietSanPhamDTOs().size()-2 >=0}">
					[
					<c:forEach var="i" begin="0" end="${sanPhamDTO.getChiTietSanPhamDTOs().size()-2 }">
						{"mau":"${sanPhamDTO.getChiTietSanPhamDTOs()[i].getMauDTO().getId()}","size": "${sanPhamDTO.getChiTietSanPhamDTOs()[i].getSize()}"},
					</c:forEach>
						{"mau":"${sanPhamDTO.getChiTietSanPhamDTOs()[sanPhamDTO.getChiTietSanPhamDTOs().size()-1].getMauDTO().getId()}","size": "${sanPhamDTO.getChiTietSanPhamDTOs()[sanPhamDTO.getChiTietSanPhamDTOs().size()-1].getSize()}"}
					]
				
				</c:when>
				<c:otherwise>
					[
						{"mau":"${sanPhamDTO.getChiTietSanPhamDTOs()[sanPhamDTO.getChiTietSanPhamDTOs().size()-1].getMauDTO().getId()}","size": "${sanPhamDTO.getChiTietSanPhamDTOs()[sanPhamDTO.getChiTietSanPhamDTOs().size()-1].getSize()}"}
					]
					
				</c:otherwise>
			</c:choose>
			
			
		</span>
	</div>
	<script type="text/javascript" src='<c:url value="/resources/js/ChiTietSanPham.js" />'></script>
</body>