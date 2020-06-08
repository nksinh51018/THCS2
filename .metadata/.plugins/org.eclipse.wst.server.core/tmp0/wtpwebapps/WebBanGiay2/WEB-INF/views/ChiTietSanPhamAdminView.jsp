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
	<jsp:include page="DauTrangAdmin.jsp"/>
	<fmt:setLocale value="vi_VN"/>
	<div class="container" style="margin-top: 5%">
		<h2 data-value="${sanPhamDTO.getId()}" style="text-align: center;">Sửa sản phẩm</h2>
		<div class="row">
			<div style="width: 100%" class="form-group">
					   <label for="id">Id: </label>
					    <input type="text" name="ten" class="form-control" id="id"  disabled value="${sanPhamDTO.getId() }">
					    
				</div>
				<div style="width: 100%" class="form-group">
					   <label for="tenSanPham">Tên sản phẩm: </label>
					    <input type="text" class="form-control" id="tenSanPham" placeholder="Nhập tên sản phẩm" value="${sanPhamDTO.getTenSanPham() }">
					   
				</div>
				<div style="width: 100%" class="form-group">
					   <label for="giaTien">Giá tiền: </label>
					    <input type="number" class="form-control" id="giaTien" placeholder="Nhập giá tiền" value="${sanPhamDTO.getGiaTien() }">
					    
				</div>
				<div style="width: 100%" class="form-group">
					   <label for="loaiSanPham">Loại sản phẩm: </label>
					    <select id = "loaiSanPham" class="form-control">
      						<c:forEach var="j" items="${danhSachLoaiSanPhamDTO.getLoaiSanPhamDTOs() }">
      							<c:choose>
      								<c:when test="${sanPhamDTO.getLoaiSanPhamDTO().getId() == j.getId() }">
      											<option data="${j.getId() }" selected>${j.getTenLoai() }</option>
      								</c:when>
      								<c:otherwise>
      											<option data="${j.getId() }">${j.getTenLoai() }</option>
      								</c:otherwise>
      							</c:choose>
      						</c:forEach>
      					</select>
				</div>
				<div style="width: 100%" class="form-group">
  					<label for="moTa">Mô tả:</label>
  					<textarea  class="form-control" rows="5" id="moTa">${sanPhamDTO.getMoTa() }</textarea>
  					 
				</div>
				<div style="width: 100%" class="form-group">
					   <label for="chatLieu">Chất liệu: </label>
					    <input type="text" class="form-control" id="chatLieu" placeholder="Nhập chất liệu" value="${sanPhamDTO.getChatLieu() }">
					    
				</div>
				<div style="width: 100%" class="form-group">
					   <label for="luotXem">Lượt xem: </label>
					    <input type="number" class="form-control" id="luotXem"  placeholder="Nhập lượt xem" value="${sanPhamDTO.getLuotXem() }">
					    
				</div>
				<div style="width: 100%" class="form-group">
					   <label for="khuyenMai">Khuyến mãi: </label>
					    <input type="number" class="form-control" disabled="disabled" id="khuyenMai" placeholder="Nhập lượt xem" value="${sanPhamDTO.getKhuyenMai() }">
					    
				</div>
				<h4  style="width: 100%">Chi tiết: </h4>
				<div id="containerChiTiet" class="container">
				<c:forEach var="i" items="${sanPhamDTO.getChiTietSanPhamDTOs() }">
					<div  class="row bao">
						<div class="col-md-11 hang">
					<div style="width: 100%" class="form-group">
     					<label for="mau">Màu :</label>
      					<input data="${i.getMauDTO().getId() }" type="text" class="form-control mau" disabled="disabled" value="${i.getMauDTO().getTenMau() }">
					</div>
					<div style="width: 100%" class="form-group">
					   <label for="size">Size: </label>
					    <input disabled="disabled" required type="text" class="form-control size" placeholder="Nhập size" value="${i.getSize() }">
					</div>
					<div style="width: 100%" class="form-group">
					   <label for="soLuong">Số lượng: </label>
					    <input required type="text" class="form-control soLuong" placeholder="Nhập số lượng" value="${i.getSoLuong() }">
					</div>
					</div>
					<!-- <div class="col-md-1 xoaB">Xóa</div> -->
					<div style="width:100%; border-top: 1px solid #343a40"></div>
					</div>
				</c:forEach>
				
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
				<c:forEach var="i" items="${sanPhamDTO.getHinhAnhDTOs() }">
				<div class="row baoHa">
					<div style="width: 100%" class="form-group">
     					<label for="mauHa">Màu :</label>   					
      						<c:forEach var="j" items="${danhSachMauDTO.getMauDTOs() }">
      							<c:if test="${i.getIdMau() == j.getId() }">
      								 <input data-idMau="${i.getIdMau() }" type="text" name="mauHa" class="form-control mauHa" aria-describedby="mauHaHelp" disabled value="${j.getTenMau() }">
      							</c:if>
      						</c:forEach>
					</div>
					<div style="width: 100%" class="form-group">
					    <input type="file" class="form-control hinhAnh" />
					</div>
					<img
											class="sanPham"
											data="${i.getId() }"
											src='<c:url value="http://localhost:8762/hinhAnh/image/${i.getUrl() }" />'
											style="background-size: cover;width: 100px;"/>
					<div style="width:100%; border-top: 1px solid #343a40"></div>
					</div>
				</c:forEach>
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
				<button id="suaSanPham" type="button" class="btn btn-dark" style="width: 100%;margin-bottom: 100px;margin-top: 50px	">Sửa sản phẩm</button>
				
				
	</div></div>
	<script type="text/javascript" src='<c:url value="/resources/js/ChiTietSanPhamAdmin.js" />'></script>
</body>