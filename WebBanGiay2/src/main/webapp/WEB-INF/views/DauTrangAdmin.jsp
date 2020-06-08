<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
	<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	<link rel="stylesheet" href='<c:url value="/resources/css/style.css" />'/> 
<fmt:setLocale value="vi_VN"/>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href='<c:url value="/" />' style="margin-left: 5%"> <img
			src="http://localhost:8762/hinhAnh/image/logo.png"
			style="background-size: cover; height: 50px" />
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto" >
				
					<li class="nav-item"><a class="nav-link" href='<c:url value="/admin/sanPham" />'>Sản phẩm</a></li>
					<li class="nav-item"><a class="nav-link" href='<c:url value="/admin/hoaDon" />'>Hóa đơn</a></li>
					<li class="nav-item"><a class="nav-link" href='<c:url value="/admin/khachHang" />'>Khách hàng</a></li>
					<li class="nav-item"><a class="nav-link" href='<c:url value="/admin/khuyenMai" />'>Khuyến mãi</a></li>
			</ul>
			<ul class="navbar-nav">
				
						<li class="nav-item active dropdown">
					        <a class="circle_avata nav-link dropdown" style="color:#343a40; text-transform: uppercase" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					        	 ${ten }
					        </a>
					      </li>
			</ul>
		</div>
	</nav>
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>