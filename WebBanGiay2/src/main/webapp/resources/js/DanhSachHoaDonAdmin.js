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
var trangThai = 3;
var kt = false;

function findHoaDon(a) {
	timKiemHoaDonDTO = {};
	timKiemHoaDonDTO["ngayBatDau"]=ngayBatDau;
	timKiemHoaDonDTO["ngayKetThuc"]=ngayKetThuc;
	timKiemHoaDonDTO["trangThai"]=trangThai;
	console.log(timKiemHoaDonDTO);
	console.log(a);
	$.ajax({
		url:"http://localhost:8762/hoaDon/admin/hoaDon/timKiem/"+a,
		headers: {
	        'Authorization': "Bearer "+getCookie("tokenJWT")
	    },
		contentType: 'application/json; charset=utf-8',
		dataType: 'json',
		type:"POST",
		data:
			JSON.stringify(timKiemHoaDonDTO),
		success: function(value){
			console.log(value);
			$("#danhSachHoaDon").empty();
			for (var j = 0; j < value.hoaDonDTOs.length; j++) {
				var i = value.hoaDonDTOs[j];
				var tongTien = 0;
				for(var k=0;k<i.chiTietHoaDons.length;k++){
					var h= i.chiTietHoaDons[k];
					tongTien += h.gia * h.soLuong;
				}
				console.log(tongTien);
				var res = i.ngayMua.split("-");
				var ngayMuaDate = new Date(parseInt(res[2]),parseInt(res[0]),parseInt(res[1]))
				var tt = i.trangThai;
				var trangThai = "";
				
				if(tt ==0 ){
					$("#danhSachHoaDon").append(
							'<tr class="hang" data-value='+ i.id +'>'+
								'<td class="a">'+i.id+'</td>'+
								'<td class="a">'+i.tenNguoiNhan +'</td>'+
								'<td class="a">'+i.diaChi +'</td>'+
								'<td class="a">'+i.sdt+'</td>'+
								'<td class="a">'+
								
								formatDate(ngayMuaDate.getDate()) + '-' +formatDate(ngayMuaDate.getMonth())+'-'+ngayMuaDate.getFullYear()+
									
								'</td>'+
								
								'<td class="a">'+ format(tongTien)+ 'd</td>'
								+'<td>'+'<input class="chkTrangThai" type="checkbox" />'+'</td>'+
							'</tr>'	
						)
				}
				
				else if(tt== 1){
					$("#danhSachHoaDon").append(
							'<tr class="hang" data-value='+ i.id +'>'+
								'<td class="a">'+i.id+'</td>'+
								'<td class="a">'+i.tenNguoiNhan +'</td>'+
								'<td class="a">'+i.diaChi +'</td>'+
								'<td class="a">'+i.sdt+'</td>'+
								'<td class="a">'+
								
								formatDate(ngayMuaDate.getDate()) + '-' +formatDate(ngayMuaDate.getMonth())+'-'+ngayMuaDate.getFullYear()+
									
								'</td>'+
								
								'<td class="a">'+ format(tongTien)+ 'd</td>'
								+'<td>'+'<input class="chkTrangThai" type="checkbox" checked="checked" />'+'</td>'+
							'</tr>'	
						)
				}
				else{
					$("#danhSachHoaDon").append(
							'<tr class="hang" data-value='+ i.id +'>'+
								'<td class="a">'+i.id+'</td>'+
								'<td class="a">'+i.tenNguoiNhan +'</td>'+
								'<td class="a">'+i.diaChi +'</td>'+
								'<td class="a">'+i.sdt+'</td>'+
								'<td class="a">'+
								
								formatDate(ngayMuaDate.getDate()) + '-' +formatDate(ngayMuaDate.getMonth())+'-'+ngayMuaDate.getFullYear()+
									
								'</td>'+
								
								'<td class="a">'+ format(tongTien)+ 'd</td>'
								+'<td>'+'Đã hủy'+'</td>'+
							'</tr>'	
						)
				}
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

function findHoaDonById(id,a) {
	$.ajax({
		url:"http://localhost:8762/hoaDon/admin/hoaDon/id/"+id,
		headers: {
	        'Authorization': "Bearer "+getCookie("tokenJWT")
	    },
		contentType: 'application/json; charset=utf-8',
		dataType: 'json',
		type:"GET",
		success: function(i){
			$("#danhSachHoaDon").empty();
			$("#xemThem").hide();
			if(i.id == 0){
				return;
			}
			console.log(i);
			var tongTien = 0;
			for(var k=0;k<i.chiTietHoaDons.length;k++){
				var h= i.chiTietHoaDons[k];
				tongTien += h.gia * h.soLuong;
			}
			
			console.log(tongTien);
			var res = i.ngayMua.split("-");
			var ngayMuaDate = new Date(parseInt(res[2]),parseInt(res[0]),parseInt(res[1]))
			var tt = i.trangThai;
			var trangThai = "";
			if(tt ==0 ){
				$("#danhSachHoaDon").append(
						'<tr class="hang" data-value='+ i.id +'>'+
							'<td>'+i.id+'</td>'+
							'<td>'+i.tenNguoiNhan +'</td>'+
							'<td>'+i.diaChi +'</td>'+
							'<td>'+i.sdt+'</td>'+
							'<td>'+
							
							formatDate(ngayMuaDate.getDate()) + '-' +formatDate(ngayMuaDate.getMonth())+'-'+ngayMuaDate.getFullYear()+
								
							'</td>'+
							
							'<td>'+ format(tongTien)+ 'd</td>'
							+'<td>'+'<input class="chkTrangThai" type="checkbox" />'+'</td>'+
						'</tr>'	
					)
			}
			
			else if(tt== 1){
				$("#danhSachHoaDon").append(
						'<tr class="hang" data-value='+ i.id +'>'+
							'<td>'+i.id+'</td>'+
							'<td>'+i.tenNguoiNhan +'</td>'+
							'<td>'+i.diaChi +'</td>'+
							'<td>'+i.sdt+'</td>'+
							'<td>'+
							
							formatDate(ngayMuaDate.getDate()) + '-' +formatDate(ngayMuaDate.getMonth())+'-'+ngayMuaDate.getFullYear()+
								
							'</td>'+
							
							'<td>'+ format(tongTien)+ 'd</td>'
							+'<td>'+'<input class="chkTrangThai" type="checkbox" checked="checked" />'+'</td>'+
						'</tr>'	
					)
			}
			else{
				$("#danhSachHoaDon").append(
						'<tr class="hang" data-value='+ i.id +'>'+
							'<td>'+i.id+'</td>'+
							'<td>'+i.tenNguoiNhan +'</td>'+
							'<td>'+i.diaChi +'</td>'+
							'<td>'+i.sdt+'</td>'+
							'<td>'+
							
							formatDate(ngayMuaDate.getDate()) + '-' +formatDate(ngayMuaDate.getMonth())+'-'+ngayMuaDate.getFullYear()+
								
							'</td>'+
							
							'<td>'+ format(tongTien)+ 'd</td>'
							+'<td>'+'Đã hủy'+'</td>'+
						'</tr>'	
					)
			}
			$("#sau").hide();
			$("#hienTai").text(1);
			$("#truoc").hide();
		},
		error : function(e) {
	        console.log(e);
	      }
	});
}

$(document).ready(function(){
	//tenLoai = $("#tenLoai").text();
	
	$(".rdThoiGian").click( function(){
		var tg = $(this).attr("data-value");
		if(tg == 0){
			$("#ngay").hide();
			ngayBatDau = null;
			ngayKetThuc = null;
			a=0;
			kt= false;
			$("#danhSachHoaDon").empty();
			findHoaDon(a);
		}
		if(tg == 1){
			$("#ngay").show();
			
		}
		$('body,html').animate({
			scrollTop: 0
		})
		$("#sau").show();
		$("#hienTai").text(1);
		$("#truoc").hide();
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
			findHoaDon(a);
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
			findHoaDon(a);
			$("#sau").show();
			$("#hienTai").text(1);
			$("#truoc").hide();
		}
	})
	
	$("#xemThem").click(function() {
		a++;
		if(kt){
			findHoaDonById($("#timKiem").val(),a)
		}
		else{
			findHoaDon(a);
		}
		$('body,html').animate({
			scrollTop: 0
		})
	})
	
	$("#sau").click(function() {
		a++;
		if(kt){
			findHoaDonById($("#timKiem").val(),a)
		}
		else{
			findHoaDon(a);
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
			findHoaDonById($("#timKiem").val(),a)
		}
		else{
			findHoaDon(a);
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
		findHoaDon(a);
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
		trangThai = 3;
		kt = false;
		findHoaDon(a);
		$("input[type=date]").val("");
		$("#all1").prop("checked", true);
		$("#all2").prop("checked", true);
		$("#ngay").hide();
		$('body,html').animate({
			scrollTop: 0
		})
		$("#sau").show();
		$("#hienTai").text(1);
		$("#truoc").hide();
	})
	
	
	$("body").on("click",".a",function(){
		window.location.href = "/WebBanGiay2/admin/chiTietHoaDon/"+$(this).closest(".hang").attr("data-value")
	})
	
	$("#timKiem").change(function() {
		a=0;
		kt=true;
		findHoaDonById($(this).val(),a);
		$('body,html').animate({
			scrollTop: 0
		})
		$("#sau").show();
		$("#hienTai").text(1);
		$("#truoc").hide();
	})
	$("body").on("change","#chkAll",function(){
		if($(this).is(":checked")){
			$("input[type=checkbox]").prop("checked", true);
		}
		else{
			$("input[type=checkbox]").prop("checked", false);
		}
	})
	
	
	$("#chkAll").change(function() {
		if($(this).is(":checked")){
			$("input[type=checkbox]").prop("checked", true);
		}
		else{
			$("input[type=checkbox]").prop("checked", false);
		}
		$(".chkTrangThai").each(function() {
			var id = $(this).closest(".hang").attr("data-value");
			var trangThaiDTO = {};
			trangThaiDTO["id"] = id;
			
			if($(this).is(":checked")){
				trangThaiDTO["trangThai"] = 1;
				$.ajax({
					url:"http://localhost:8762/hoaDon/admin/hoaDon/trangThai",
					headers: {
				        'Authorization': "Bearer "+getCookie("tokenJWT")
				    },
				    data: JSON.stringify(trangThaiDTO),
					contentType: 'application/json; charset=utf-8',
					dataType: 'json',
					type:"PUT",
					success: function(value){
						console.log(value)
					}
				})
			}
			else{
				trangThaiDTO["trangThai"] = 0;
				$.ajax({
					url:"http://localhost:8762/hoaDon/admin/hoaDon/trangThai",
					headers: {
				        'Authorization': "Bearer "+getCookie("tokenJWT")
				    },
				    data: JSON.stringify(trangThaiDTO),
					contentType: 'application/json; charset=utf-8',
					dataType: 'json',
					type:"PUT",
					success: function(value){
						console.log(value)
					}
				})
			}
		})
		
	})
	$("body").on("change",".chkTrangThai",function(){
		var id = $(this).closest(".hang").attr("data-value");
		var trangThaiDTO = {};
		trangThaiDTO["id"] = id;
		console.log("asdsd")
		if($(this).is(":checked")){
			trangThaiDTO["trangThai"] = 1;
			$.ajax({
				url:"http://localhost:8762/hoaDon/admin/hoaDon/trangThai",
				headers: {
			        'Authorization': "Bearer "+getCookie("tokenJWT")
			    },
			    data: JSON.stringify(trangThaiDTO),
				contentType: 'application/json; charset=utf-8',
				dataType: 'json',
				type:"PUT",
				success: function(value){
					console.log(value)
				}
			})
		}
		else{
			trangThaiDTO["trangThai"] = 0;
			$.ajax({
				url:"http://localhost:8762/hoaDon/admin/hoaDon/trangThai",
				headers: {
			        'Authorization': "Bearer "+getCookie("tokenJWT")
			    },
			    data: JSON.stringify(trangThaiDTO),
				contentType: 'application/json; charset=utf-8',
				dataType: 'json',
				type:"PUT",
				success: function(value){
					console.log(value)
				}
			})
		}
	})
});