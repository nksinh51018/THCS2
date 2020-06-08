<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
<link rel="stylesheet" href='<c:url value="/resources/css/Chart.min.css" />' />
</head>
<body>
	<jsp:include page="DauTrangAdmin.jsp"/>
	<fmt:setLocale value="vi_VN"/>
	<div class="container">
		<h1 style="text-align: center;margin-top: 30px">Kết quả bán hàng hôm nay</h1>
		<div class="row" style="margin-top: 30px">
			
			<div class="col-md-4">
			
				<div style="background-color: #dcdcdc;height: 100px;margin: auto">
					<div class="row">
						<div class="col-md-3" style="padding-right: 0px">
							<img style="width: 100%;background-size: cover;padding-left: 8px;padding-top: 14px" src='<c:url value="/resources/image/money.png" />' />
						</div>
						
						<div class="col-md-9">
							<div style="padding: 10px" >${doanhThuNgayDTO.getSoLuong() } Hóa đơn</div>
							<h3><fmt:formatNumber value = "${doanhThuNgayDTO.getTongTien() }" /> đ</h3>
						</div>
					</div>
				</div>
			
			
			</div>
			
			<div class="col-md-4">
			
				<div style="background-color: #dcdcdc;padding: :10;height: 100px">
					
					<div class="row">
						<div class="col-md-3" style="padding-right: 0px">
						<c:choose>
							<c:when test="${homQua > 0}">
								<img style="width: 100%;background-size: cover;padding-left: 8px;padding-top: 14px" src='<c:url value="/resources/image/upload.png" />' />
							</c:when>
							<c:otherwise>
								<img style="width: 100%;background-size: cover;padding-left: 8px;padding-top: 14px" src='<c:url value="/resources/image/down.png" />' />
							</c:otherwise>
						</c:choose>
							
						</div>
						
						<div class="col-md-9">
							<div style="padding: 10px" >So với hôm qua</div>
							<h3>${homQua } %</h3>
						</div>
					</div>
					
				</div>
			
			
			</div>
			
			<div class="col-md-4">
			
				<div style="background-color: #dcdcdc;padding: :10;height: 100px">
					
					<div class="row">
						<div class="col-md-3" style="padding-right: 0px">
						<c:choose>
							<c:when test="${thangTruoc > 0}">
								<img style="width: 100%;background-size: cover;padding-left: 8px;padding-top: 14px" src='<c:url value="/resources/image/upload.png" />' />
							</c:when>
							<c:otherwise>
								<img style="width: 100%;background-size: cover;padding-left: 8px;padding-top: 14px" src='<c:url value="/resources/image/down.png" />' />
							</c:otherwise>
						</c:choose>
							
						</div>
						
						<div class="col-md-9">
							<div style="padding: 10px" >So với cùng kì tháng trước</div>
							<h3>${thangTruoc } %</h3>
						</div>
					</div>
			
			
			</div>
		
		</div>
  			
	</div>
	<div class="row" style="margin-top: 50px">
				
				<canvas id="myChart"></canvas>
				
			</div>
			<div class="row" style="margin-top: 50px;margin-right: -60px;margin-left: -60px">
				 <select class="form-control" id="chon" style="width: 200px">
			      <option data-value=1>Theo doanh số</option>
			      <option data-value=2>Theo số lượng</option>
			    </select>
			<canvas id="myChart2"></canvas>
				
			</div>
	<script type="text/javascript" src='<c:url value="/resources/js/Admin.js" />'></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.min.js"></script>
	<!-- <script type="text/javascript" src='<c:url value="/resources/js/Chart.min.js" />'></script>
	<script type="text/javascript" src='<c:url value="/resources/js/Chart.js" />'></script>-->
</body>
</html>