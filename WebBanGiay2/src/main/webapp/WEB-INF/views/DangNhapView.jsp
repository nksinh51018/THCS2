<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>

</head>
<body>
<jsp:include page="DauTrang.jsp"/>

	<div class="container">
		<div class="row" style="margin-top: 50px;">
			<div class="col-md-6 ">
				<div class="col-12">
      				<div class="form-group" >
      				<div style="font-weight: bold;font-size: 24px;">
      					<span>Đăng nhập</span>
      				</div>
      				<p>${message }</p>
			      	<input class="form-control" type="text" name="TenDangNhap" id="TenDangNhap" placeholder="Tên Đăng nhập"/><br/>
					<input class="form-control" type="password" name="MatKhau" id="MatKhau" placeholder="Mật khẩu"/><br/>
					
					<input id="dangNhap" type="button" style="width: 100%" class="btn btn-dark" value="Đăng nhập" />
			      </div>
    			</div> 
			</div>
			<div class="col-md-6">
				
      				<div style="font-weight: bold;font-size: 24px;">
      					<span>Khách hàng mới</span>
      				</div>
      				<br/>
      				<span>Nếu bạn chưa có tài khoản, hãy sử dụng tùy chọn này để đăng ký.</span>
      				<br/>
      				<br/>
      				<a href="dangKi">
      					<button class="btn btn-dark">Đăng kí</button>
      				</a>
			</div>
		</div>
	
	</div>
	
	<script type="text/javascript" src='<c:url value="/resources/js/DangNhap.js" />'></script>
	
</body>
</html>