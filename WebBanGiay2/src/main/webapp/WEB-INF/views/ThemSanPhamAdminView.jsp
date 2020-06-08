<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm sản phẩm</title>

</head>
<body>
	<jsp:include page="DauTrangAdmin.jsp"/>
	<fmt:setLocale value="vi_VN"/>
	<div class="container" style="margin-top: 5%">
		<h2 style="text-align: center;">Thêm sản phẩm</h2>
		<div class="row">
				<div style="width: 100%" class="form-group">
					   <label for="tenSanPham">Tên sản phẩm: </label>
					    <input type="text" class="form-control" id="tenSanPham"  placeholder="Nhập tên sản phẩm">
				</div>
				<div style="width: 100%" class="form-group">
					   <label for="giaTien">Giá tiền: </label>
					    <input type="number" class="form-control" id="giaTien" placeholder="Nhập giá tiền" >
					    
				</div>
				<div style="width: 100%" class="form-group">
					   <label for="loaiSanPham">Loại sản phẩm: </label>
					    <select id = "loaiSanPham" class="form-control">
      						<c:forEach var="j" items="${danhSachLoaiSanPhamDTO.getLoaiSanPhamDTOs() }">
      							<option data="${j.getId() }" >${j.getTenLoai() }</option>
      						</c:forEach>
      					</select>
				</div>
				<div style="width: 100%" class="form-group">
  					<label for="moTa">Mô tả:</label>
  					<textarea  class="form-control" rows="5" id="moTa"></textarea>
  					
				</div>
				<div style="width: 100%" class="form-group">
					   <label for="chatLieu">Chất liệu: </label>
					    <input type="text" class="form-control" id="chatLieu" placeholder="Nhập chất liệu" value="${sanPhamDTO.getChatLieu() }">
					    
				</div>
				<div style="width: 100%" class="form-group">
					   <label for="luotXem">Lượt xem: </label>
					    <input type="number" class="form-control" id="luotXem" placeholder="Nhập lượt xem" value="${sanPhamDTO.getLuotXem() }">
		
				</div>
				
				<h4  style="width: 100%">Chi tiết: </h4>
				<div id="containerChiTiet" class="container">
				<div  class="row bao">
						<div class="col-md-11 hang">
					<div style="width: 100%" class="form-group">
     					<label for="mau">Màu :</label>
      					<select class="form-control mau">
      						<c:forEach var="j" items="${danhSachMauDTO.getMauDTOs() }">
      									<option  data="${j.getId() }">${j.getTenMau() }</option>
      						</c:forEach>
      						<option data-value="1">Màu mới</option>
      					</select>
					</div>
					<div class="mauMoi" style="display: none;">
						<div style="width: 100%" class="form-group">
					  		 <label for="tenMauMoi">Tên màu: </label>
					    	<input required type="text" class="form-control tenMauMoi" placeholder="Nhập tên màu mới">
						</div>
						<div style="width: 100%" class="form-group">
					  	 <label for="maMauMoi">Mã màu: </label>
					   	 <input required type="text" class="form-control maMauMoi" placeholder="Nhập mã màu mới">
						</div>
					</div>
					<div style="width: 100%" class="form-group">
					   <label for="size">Size: </label>
					    <input required type="text" class="form-control size" placeholder="Nhập size" value="${i.getSize() }">
					</div>
					<div style="width: 100%" class="form-group">
					   <label for="soLuong">Số lượng: </label>
					    <input required type="text" class="form-control soLuong" placeholder="Nhập số lượng" value="${i.getSoLuong() }">
					</div>
					</div>
					
					<div style="width:100%; border-top: 1px solid #343a40"></div>
					</div>
				</div>
				<button id="themChiTiet" type="button" class="btn btn-dark" style="width: 100%;margin-bottom: 100px;margin-top: 50p">Thêm chi tiết sản phẩm</button>
				<div id="chiTietMoi" style="display: none; width: 100%">
				<div  class="row bao">
						<div class="col-md-11 hang">
					<div style="width: 100%" class="form-group">
     					<label for="mau">Màu :</label>
      					<select class="form-control mau">
      						<c:forEach var="j" items="${danhSachMauDTO.getMauDTOs() }">
      									<option  data="${j.getId() }">${j.getTenMau() }</option>
      						</c:forEach>
      						<option data-value="1">Màu mới</option>
      					</select>
					</div>
					<div class="mauMoi" style="display: none;">
						<div style="width: 100%" class="form-group">
					  		 <label for="tenMauMoi">Tên màu: </label>
					    	<input required type="text" class="form-control tenMauMoi" placeholder="Nhập tên màu mới">
						</div>
						<div style="width: 100%" class="form-group">
					  	 <label for="maMauMoi">Mã màu: </label>
					   	 <input required type="text" class="form-control maMauMoi" placeholder="Nhập mã màu mới">
						</div>
					</div>
					<div style="width: 100%" class="form-group">
					   <label for="size">Size: </label>
					    <input required type="text" class="form-control size" placeholder="Nhập size" value="${i.getSize() }">
					</div>
					<div style="width: 100%" class="form-group">
					   <label for="soLuong">Số lượng: </label>
					    <input required type="text" class="form-control soLuong" placeholder="Nhập số lượng" value="${i.getSoLuong() }">
					</div>
					</div>
					<div class="col-md-1 xoaB">Xóa</div>
					<div style="width:100%; border-top: 1px solid #343a40"></div>
					</div>
					</div>
				<h4  style="width: 100%">Hình ảnh: </h4>
				<div id="containerHinhAnh" class="container">
				<div class="row baoHa">
					<div style="width: 100%" class="form-group">
     					<label for="mauHa">Màu :</label>   					
      								 <input type="text" name="mauHa" class="form-control mauHa" aria-describedby="mauHaHelp" disabled data-idmau="${danhSachMauDTO.getMauDTOs()[0].getId() }" value="${danhSachMauDTO.getMauDTOs()[0].getTenMau() }">
					</div>
					<div style="width: 100%" class="form-group">
					    <input required type="file" class="form-control hinhAnh" placeholder="Nhập hình ảnh">
					</div>
					<img
											class="sanPham"
											data=""
											src=""
											style="background-size: cover;width: 100px;"/>
					<div style="width:100%; border-top: 1px solid #343a40"></div>
					</div>
				</div>
				<div id="hinhAnhMoi" style="display:none; width: 100%">
					<div class="row baoHa">
					<div style="width: 100%" class="form-group">
     					<label for="mauHa">Màu :</label>   					
      								 <input type="text" name="mauHa" class="form-control mauHa" aria-describedby="mauHaHelp" disabled>
					</div>
					<div style="width: 100%" class="form-group">
					    <input required type="file" class="form-control hinhAnh" placeholder="Nhập hình ảnh" >
					</div>
					<img
											class="sanPham"
											data=""
											src=""
											style="background-size: cover;width: 100px;"/>
					<div style="width:100%; border-top: 1px solid #343a40"></div>
					</div>
				</div>
				<button id="themSanPham" type="button" class="btn btn-dark" style="width: 100%;margin-bottom: 100px;margin-top: 50px	">Thêm sản phẩm</button>
				
				
	</div></div>
	<script type="text/javascript" src='<c:url value="/resources/js/ThemSanPhamAdmin.js" />'></script>
</body>