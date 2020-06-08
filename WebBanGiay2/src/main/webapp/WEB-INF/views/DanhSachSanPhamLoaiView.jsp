<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách loại sản phẩm</title>

</head>
<body>
	<jsp:include page="DauTrang.jsp"/>
	<fmt:setLocale value="vi_VN"/>
	<div class="container" style="margin-top: 5%">
		<h2 id="tenLoai" style="text-align: center;">${tenLoai }</h2>
		<div id="textlocSanPham">Lọc sản phẩm</div><div id="textLamMoi" style="text-align: right;">Làm mới</div>
		<div class="row">
			
			<div id="locSanPham" class="row" style="width: 100%;margin-left: 15px;display: none;">
				<div class="row" style="width: 100%;height: 30px;background-color: rgba(0, 0, 0, 0.5)">
					<div class="col-md-4">
						MÀU SẮC CHÍNH
					</div>
					<div class="col-md-4">
						ĐƠN GIÁ
					</div>
					<div class="col-md-4">
						KÍCH CỠ
					</div>
				</div>
				<div class="row" style="width: 100%;background-color: rgba(0, 0, 0, 0.1)">
					<div class="col-md-4" id="danhSachMau">
						<c:forEach var="i" items="${danhSachTimKiemDTOs.getDanhSachMauDTO().getMauDTOs() }">
							<button class="btn btnMau" style="background-color: #${i.getMaMau()};width: 25px;height: 25px;margin: 2px;" data-maMau="${i.getMaMau() }" data-value="${i.getId() }"></button>
						</c:forEach>
					</div>
					<div class="col-md-4" id="danhSachGia">
						<c:forEach var="i" begin="1" end="${danhSachTimKiemDTOs.getGiaDTO().getMaxGia() }" step="500000">
							<input name="gia" class="chkGia" type="radio" data-min=${i } data-max=${i+499999 }><fmt:formatNumber value = "${i }" /> d - <fmt:formatNumber value = "${i+499999 }" /> d<br/>
						</c:forEach>
					</div>
					<div class="col-md-4" id="danhSachSize">
						<c:forEach var="i" items="${danhSachTimKiemDTOs.getDanhSachSizeDTO().getSizes() }">
							<button class="btn btnSize" style="margin: 2px;background-color: white;" data-text="${i }">${i}</button>
						</c:forEach>
					</div>
				</div>
			</div>
		<div class="row" id= "danhSachSanPham">
			<c:forEach var="i" items="${danhSachTimKiemDTOs.getDanhSachSanPhamDTO().getSanPhamDTOs() }">
				<div class="col-md-3">
					<div style="padding: 5px;position: relative;" >
						<a href='<c:url value="/chiTiet/${i.getId() }" />'>
						<c:url var="url0" value="http://localhost:8762/hinhAnh/image/${i.getHinhAnhDTOs().get(0).getUrl() }" />
						<c:choose>
							<c:when test="${i.getHinhAnhDTOs().size() < 2 }">
								<c:url var="url1" value="http://localhost:8080/WebBanGiay2/resources/image/unnamed.png" />
							</c:when>
							<c:otherwise>
								<c:url var="url1" value="http://localhost:8762/hinhAnh/image/${i.getHinhAnhDTOs().get(1).getUrl() }" />
							</c:otherwise>
						</c:choose>
						<div class="card" style="height: 400px">
							<img
								src='<c:url value="http://localhost:8762/hinhAnh/image/${i.getHinhAnhDTOs().get(0).getUrl() }" />'
								class="card-img-top"
								onmouseover="this.src='${url1}'"
								onmouseout="this.src='${url0}'"
								style="transition:2s;background-size: cover;margin: auto;margin-bottom: 100px"
								 />
							<div class="card-body" style="position: absolute;bottom: 0;color: black">
								<h5 class="card-title">${i.getTenSanPham()}</h5>
								<c:choose>
									<c:when test="${i.getKhuyenMai() != 0 }">
										<p class="card-text"><strike style="color: red"><fmt:formatNumber value = "${i.getGiaTien() }" /> d</strike> <fmt:formatNumber value = "${(i.getGiaTien()* ( 100- i.getKhuyenMai()) /100)}" /> d</p>
									</c:when>
									<c:otherwise>
										<p class="card-text"><fmt:formatNumber value = "${i.getGiaTien() }" /> d</p>
									</c:otherwise>
								</c:choose>
								
							</div>
						</div>
						</a>
					</div>

				</div>
			</c:forEach>

		</div>
		</div>
		<c:if test="${danhSachTimKiemDTOs.getDanhSachSanPhamDTO().check == true }">
			<div class="row">
				<button id="xemThem" type="button" class="btn btn-dark" style="width: 100%;margin-bottom: 100px;">Xem thêm</button>
			</div>
		</c:if>
	</div>
	<script type="text/javascript" src='<c:url value="/resources/js/DanhSachSanPhamLoai.js" />'></script>
</body>