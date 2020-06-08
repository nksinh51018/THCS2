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
	<jsp:include page="DauTrang.jsp"/>
	<fmt:setLocale value="vi_VN"/>
	<div class="container" style="margin-top: 5%">
		<h2 style="text-align: center;">Sản phẩm hot</h2>
		<div class="row">
		<div class="row">
			<c:forEach var="i" items="${sanPhamHot.getSanPhamDTOs() }">
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
										<p class="card-text"><strike style="color: red"><fmt:formatNumber value = "${i.getGiaTien() }" /> d</strike> <fmt:formatNumber value = "${(i.getGiaTien() * ( 100- i.getKhuyenMai()) /100)}" /> d</p>
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
		<h2 style="text-align: center;margin-top: 100px">Tất cả sản phẩm</h2>
		<div class="row">
		<div class="row" id= "danhSachSanPham">
			<c:forEach var="i" items="${allSanPham.getSanPhamDTOs() }">
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
		<c:if test="${allSanPham.check == true }">
			<div class="row">
				<button id="xemThem" type="button" class="btn btn-dark" style="width: 100%;margin-bottom: 100px;">Xem thêm</button>
			</div>
		</c:if>
	</div>
	<script type="text/javascript" src='<c:url value="/resources/js/DanhSachSanPham.js" />'></script>
</body>