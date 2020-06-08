function format(n) {
  return n.toFixed(0).replace(/./g, function(c, i, a) {
    return i > 0 && c !== "." && (a.length - i) % 3 === 0 ? "." + c : c;
  });
}
function formatDate(n){
	  if(n <= 9){
	    return "0" + n;
	  }
	  return n
	}
function getCookie(cname) {
	  var name = cname + "=";
	  var decodedCookie = decodeURIComponent(document.cookie);
	  var ca = decodedCookie.split(';');
	  for(var i = 0; i <ca.length; i++) {
	    var c = ca[i];
	    while (c.charAt(0) == ' ') {
	      c = c.substring(1);
	    }
	    if (c.indexOf(name) == 0) {
	      return c.substring(name.length, c.length);
	    }
	  }
	  return "";
	}

var a= 0;
var ngayBatDau = null;
var ngayKetThuc = null;
var tienBatDau = null;
var tienKetThuc = null;
var kt = false;

function findKhachHang(a) {
	timKiemKhachHangDTO = {};
	timKiemKhachHangDTO["ngayBatDau"]=ngayBatDau;
	timKiemKhachHangDTO["ngayKetThuc"]=ngayKetThuc;
	timKiemKhachHangDTO["tienBatDau"]=tienBatDau;
	timKiemKhachHangDTO["tienKetThuc"]=tienKetThuc;
	console.log(timKiemKhachHangDTO);
	console.log(a);
	$.ajax({
		url:"http://localhost:8762/taiKhoan/admin/taiKhoan/timKiem/"+a,
		headers: {
	        'Authorization': "Bearer "+getCookie("tokenJWT")
	    },
		contentType: 'application/json; charset=utf-8',
		dataType: 'json',
		type:"POST",
		data:
			JSON.stringify(timKiemKhachHangDTO),
		success: function(value){
			console.log(value);
			$("#danhSachKhachHang").empty();
			for (var j = 0; j < value.taiKhoanDTOs.length; j++) {
				var i = value.taiKhoanDTOs[j];
				var tongTien = 0;
				var res = i.ngaySinh.split("-");
				var ngaySinhDate = new Date(parseInt(res[2]),parseInt(res[0]),parseInt(res[1]))
				var tt = i.gioiTinh;
				var gioiTinh = "";
				if(tt ==0 ){
					gioiTinh = "Nữ"
				}
				else if(tt == 1){
					gioiTinh = "Nam"
				}
				
				
				
				$("#danhSachKhachHang").append(
					'<tr class="hang" data-value="'+i.id+'">'+
						'<td>'+i.id +'</td>'+
						'<td>'+i.tenDangNhap + '</td>' +
						'<td>'+i.ten +'</td>'+
						
						'<td>'+ gioiTinh+
						'</td>'+
						'<td>'+i.diaChi +'</td>' +
						'<td>'+i.sdt +'</td>'+
						'<td>'
							+ formatDate(ngaySinhDate.getDate()) + '-' +formatDate(ngaySinhDate.getMonth())+'-'+ngaySinhDate.getFullYear() +
						'</td>' +
						
						'<td>'+format(i.tongTien)+' d</td>'
					+'</tr>	'	
				);
			}
			if(value.check){
				$("#sau").show();
			}
			else{
				$("#sau").hide();
			}
		},
		
		
		error : function(e) {
	        console.log(e);
	      }
	});
}

function findKhachHangByTen(ten,a) {
	$.ajax({
		url:"http://localhost:8762/taiKhoan/admin/taiKhoan/timKiem/"+ten+"/"+a,
		headers: {
	        'Authorization': "Bearer "+getCookie("tokenJWT")
	    },
		contentType: 'application/json; charset=utf-8',
		dataType: 'json',
		type:"POST",
		success: function(value){
			console.log(value);
			$("#danhSachKhachHang").empty();
			for (var j = 0; j < value.taiKhoanDTOs.length; j++) {
				var i = value.taiKhoanDTOs[j];
				var tongTien = 0;
				var res = i.ngaySinh.split("-");
				var ngaySinhDate = new Date(parseInt(res[2]),parseInt(res[0]),parseInt(res[1]))
				var tt = i.gioiTinh;
				var gioiTinh = "";
				if(tt ==0 ){
					gioiTinh = "Nữ"
				}
				else if(tt == 1){
					gioiTinh = "Nam"
				}
				
				
				
				$("#danhSachKhachHang").append(
					'<tr class="hang" data-value="'+i.id+'">'+
						'<td>'+i.id +'</td>'+
						'<td>'+i.tenDangNhap + '</td>' +
						'<td>'+i.ten +'</td>'+
						
						'<td>'+ gioiTinh+
						'</td>'+
						'<td>'+i.diaChi +'</td>' +
						'<td>'+i.sdt +'</td>'+
						'<td>'
							+ formatDate(ngaySinhDate.getDate()) + '-' +formatDate(ngaySinhDate.getMonth())+'-'+ngaySinhDate.getFullYear() +
						'</td>' +
						
						'<td>'+format(i.tongTien)+' d</td>'
					+'</tr>	'	
				);
			}
			if(value.check){
				$("#sau").show();
			}
			else{
				$("#sau").hide();
			}
		
		},
		error : function(e) {
	        console.log(e);
	      }
	});
}

$(document).ready(function(){
	//tenLoai = $("#tenLoai").text();
	
	$(".rdNgaySinh").click( function(){
		var tg = $(this).attr("data-value");
		if(tg == 0){
			$("#ngay").hide();
			ngayBatDau = null;
			ngayKetThuc = null;
			a=0;
			kt= false;
			$("#danhSachHoaDon").empty();
			findKhachHang(a);
			$("#sau").show();
			$("#hienTai").text(1);
			$("#truoc").hide();
		}
		if(tg == 1){
			$("#ngay").show();
		}
		$('body,html').animate({
			scrollTop: 0
		})
	});
	
	$(".rdTongTien").click( function(){
		var tg = $(this).attr("data-value");
		if(tg == 0){
			$("#tien").hide();
			tienBatDau = null;
			tienKetThuc = null;
			a=0;
			kt= false;
			$("#danhSachHoaDon").empty();
			findKhachHang(a);
			$("#sau").show();
			$("#hienTai").text(1);
			$("#truoc").hide();
		}
		if(tg == 1){
			$("#tien").show();
			
		}
		$('body,html').animate({
			scrollTop: 0
		})
	});
	
	
	$("#ngayDatDau").change(function() {
		var ngayBD = $("#ngayDatDau").val();
		var ngayKT = $("#ngayKetThuc").val();
		console.log(ngayBD);
		if(ngayBD != "" && ngayKT !=""){
			ngayBatDau = ngayBD; 
			ngayKetThuc = ngayKT;
			a=0;
			kt= false;
			$("#danhSachHoaDon").empty();
			findKhachHang(a);
			$("#sau").show();
			$("#hienTai").text(1);
			$("#truoc").hide();
		}
	})
	
	$("#ngayKetThuc").change(function() {
		var ngayBD = $("#ngayDatDau").val();
		var ngayKT = $("#ngayKetThuc").val();
		if(ngayBD != "" && ngayKT !=""){
			ngayBatDau = ngayBD; 
			ngayKetThuc = ngayKT;
			a=0;
			kt= false;
			$("#danhSachHoaDon").empty();
			findKhachHang(a);
			$("#sau").show();
			$("#hienTai").text(1);
			$("#truoc").hide();
		}
	})
	
	$("#tienDatDau").change(function() {
		var tienBD = $("#tienBatDau").val();
		var tienKT = $("#tienKetThuc").val();
		if(tienBD != "" && tienKT !=""){
			tienBatDau = tienBD; 
			tienKetThuc = tienKT;
			a=0;
			kt= false;
			$("#danhSachKhachHang").empty();
			findKhachHang(a);
			$("#sau").show();
			$("#hienTai").text(1);
			$("#truoc").hide();
		}
	})
	
	$("#tienKetThuc").change(function() {
		var tienBD = $("#tienBatDau").val();
		var tienKT = $("#tienKetThuc").val();
		if(tienBD != "" && tienKT !=""){
			tienBatDau = tienBD; 
			tienKetThuc = tienKT;
			a=0;
			kt= false;
			$("#danhSachKhachHang").empty();
			findKhachHang(a);
			$("#sau").show();
			$("#hienTai").text(1);
			$("#truoc").hide();
		}
	})
	
	$("#xemThem").click(function() {
		a++;
		if(kt){
			findKhachHangByTen($("#timKiem").val(),a)
		}
		else{
			findKhachHang(a);
		}
		$('body,html').animate({
			scrollTop: 0
		})
	})
	
	$("#sau").click(function() {
		a++;
		if(kt){
			findKhachHangByTen($("#timKiem").val(),a)
		}
		else{
			findKhachHang(a);
		}
		$('body,html').animate({
			scrollTop: 0
		})
		$("#truoc").show();
		$("#hienTai").text(a+1);
	})
	
	$("#truoc").click(function() {
		a--;
		if(kt){
			findKhachHangByTen($("#timKiem").val(),a)
		}
		else{
			findKhachHang(a);
		}
		$('body,html').animate({
			scrollTop: 0
		})
		$("#sau").show();
		$("#hienTai").text(a+1);
		if(a==0){
			$(this).hide();
		}
	})
	
	
	$("body").on("click",".rdTrangThai",function(){
		trangThai = $(this).attr("data-value");
		a=0;
		kt= false;
		$("#danhSachHoaDon").empty();
		findKhachHang(a);
		$('body,html').animate({
			scrollTop: 0
		})
		$("#sau").show();
		$("#hienTai").text(1);
		$("#truoc").hide();
	})
	
	$("#lamMoi").click(function() {
		a= 0;
		ngayBatDau = null;
		ngayKetThuc = null;
		tienBatDau = null;
		tienKetThuc = null;
		kt = false;
		findKhachHang(a);
		$("input[type=date]").val("");
		$("#all1").prop("checked", true);
		$("#all2").prop("checked", true);
		$("#ngay").hide();
		$("#tien").hide();
		$("#timKiem").val("");
		$('body,html').animate({
			scrollTop: 0
		})
		$("#sau").show();
		$("#hienTai").text(1);
		$("#truoc").hide();
	})
	
	
	$("body").on("click",".hang",function(){
		window.location.href = "/WebBanGiay2/admin/chiTietKhachHang/"+$(this).attr("data-value")
	})
	
	$("#timKiem").change(function() {
		a=0;
		kt=true;
		findKhachHangByTen($(this).val(),a);
		$('body,html').animate({
			scrollTop: 0
		})
		$("#sau").show();
		$("#hienTai").text(1);
		$("#truoc").hide();
	})
	
});