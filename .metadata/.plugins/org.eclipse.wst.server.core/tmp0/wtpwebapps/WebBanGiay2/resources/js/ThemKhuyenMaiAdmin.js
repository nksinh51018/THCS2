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
function arrayRemove(arr, value) { return arr.filter(function(ele){ return ele != value; });}
var a= 0;
var minGia = 0;
var maxGia = 0;
var idMau = 0;
var size = null;
var maMau = "";
var tenLoai = null;
var kt = false;
var id=0;
var list = {};
var arr={};
arr["idSanPhams"]=[];

function findSanPham(a) {
	$("#chkAll").prop("checked", false);
	timKiemDTO = {};
	timKiemDTO["minGia"]=minGia;
	timKiemDTO["maxGia"]=maxGia;
	timKiemDTO["idMau"]=idMau;
	timKiemDTO["size"]=size;
	timKiemDTO["tenLoai"]=tenLoai;
	console.log(timKiemDTO);
	console.log(a);
	$.ajax({
		url:"http://localhost:8762/sanPham/admin/timKiem/"+a,
		headers: {
	        'Authorization': "Bearer "+getCookie("tokenJWT")
	    },
		contentType: 'application/json; charset=utf-8',
		dataType: 'json',
		type:"POST",
		data:
			JSON.stringify(timKiemDTO),
		success: function(value){
			console.log(value);
			$("#bang").empty();
			for (var j = 0; j < value.sanPhamDTOs.length; j++) {
				var i = value.sanPhamDTOs[j];
				if(list[i.id] == 1){
					$("#bang").append(
							'<tr class="hang" data-value="'+i.id+'">'
											+'<td>' + i.id +'</td>'
											+'<td>' +i.tenSanPham +'</td>'
											+'<td>' + i.giaTien +'</td>'
											+'<td>' + i.chatLieu +'</td>'
											+'<td>' + i.luotXem +'</td>'
											+'<td>' + i.khuyenMai +'</td>'
											+'<td>' + i.loaiSanPhamDTO.tenLoai +'</td>'
											+'<td>'
												+'<img '
													+'class="sanPham" '
													+'data="'+i.id+'" '
													+'src="http://localhost:8762/hinhAnh/image/'+ i.hinhAnhDTOs[0].url+'" '
													+'style="background-size: cover;width: 100px;" />'
											+'</td>'
											+'<td>'+
												'<input class="chkItem" type="checkbox" checked />'
											+'</td>'
										+'</tr>'	
						);
				}
				else{
					$("#bang").append(
							'<tr class="hang" data-value="'+i.id+'">'
											+'<td>' + i.id +'</td>'
											+'<td>' +i.tenSanPham +'</td>'
											+'<td>' + i.giaTien +'</td>'
											+'<td>' + i.chatLieu +'</td>'
											+'<td>' + i.luotXem +'</td>'
											+'<td>' + i.khuyenMai +'</td>'
											+'<td>' + i.loaiSanPhamDTO.tenLoai +'</td>'
											+'<td>'
												+'<img '
													+'class="sanPham" '
													+'data="'+i.id+'" '
													+'src="http://localhost:8762/hinhAnh/image/'+ i.hinhAnhDTOs[0].url+'" '
													+'style="background-size: cover;width: 100px;" />'
											+'</td>'
											+'<td>'+
												'<input class="chkItem" type="checkbox" />'
											+'</td>'
										+'</tr>'	
						);
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

function findSanPhamByTen(ten,a) {
	$("#chkAll").prop("checked", false);
	$.ajax({
		url:"http://localhost:8762/sanPham/user/sanPham/timKiem/"+ten+"/"+a,
		headers: {
	        'Authorization': "Bearer "+getCookie("tokenJWT")
	    },
		contentType: 'application/json; charset=utf-8',
		dataType: 'json',
		type:"GET",
		success: function(value){
			console.log(value);
			$("#bang").empty();
			for (var j = 0; j < value.sanPhamDTOs.length; j++) {
				var i = value.sanPhamDTOs[j];
				if(list[i.id] == 1){
					$("#bang").append(
							'<tr class="hang" data-value="'+i.id+'">'
											+'<td>' + i.id +'</td>'
											+'<td>' +i.tenSanPham +'</td>'
											+'<td>' + i.giaTien +'</td>'
											+'<td>' + i.chatLieu +'</td>'
											+'<td>' + i.luotXem +'</td>'
											+'<td>' + i.khuyenMai +'</td>'
											+'<td>' + i.loaiSanPhamDTO.tenLoai +'</td>'
											+'<td>'
												+'<img '
													+'class="sanPham" '
													+'data="'+i.id+'" '
													+'src="http://localhost:8762/hinhAnh/image/'+ i.hinhAnhDTOs[0].url+'" '
													+'style="background-size: cover;width: 100px;" />'
											+'</td>'
											+'<td>'+
												'<input class="chkItem" type="checkbox" checked />'
											+'</td>'
										+'</tr>'	
						);
				}
				else{
					$("#bang").append(
							'<tr class="hang" data-value="'+i.id+'">'
											+'<td>' + i.id +'</td>'
											+'<td>' +i.tenSanPham +'</td>'
											+'<td>' + i.giaTien +'</td>'
											+'<td>' + i.chatLieu +'</td>'
											+'<td>' + i.luotXem +'</td>'
											+'<td>' + i.khuyenMai +'</td>'
											+'<td>' + i.loaiSanPhamDTO.tenLoai +'</td>'
											+'<td>'
												+'<img '
													+'class="sanPham" '
													+'data="'+i.id+'" '
													+'src="http://localhost:8762/hinhAnh/image/'+ i.hinhAnhDTOs[0].url+'" '
													+'style="background-size: cover;width: 100px;" />'
											+'</td>'
											+'<td>'+
												'<input class="chkItem" type="checkbox" />'
											+'</td>'
										+'</tr>'	
						);
				}
				
			}
			if(value.check){
				$("#sau").show();
			}
			else{
				$("#sau").hide();
			}},
		error : function(e) {
	        console.log(e);
	      }
	});
}

$(document).ready(function(){
//	id= $("#tenKhuyenMai").attr("data-value");
//	$.ajax({
//		url:"http://localhost:8762/khuyenMai/admin/chiTietKhuyenMai/"+id,
//		headers: {
//	        'Authorization': "Bearer "+getCookie("tokenJWT")
//	    },
//		contentType: 'application/json; charset=utf-8',
//		dataType: 'json',
//		type:"GET",
//		success: function(value){
//			arr=value;
//			for (var i = 0; i < value.idSanPhams.length; i++) {
//				var j = value.idSanPhams[i];
//				var key = j.toString();
//				list[key] = 1;
//			}
//		}
//	})
//	.done(function() {
//		$(".hang").each(function() {
//			var data = $(this).attr("data-value");
//			if(list[data] == 1){
//				$(this).find(".chkItem").prop("checked", true);
//			}	
//		})
//	})
	
	$(".rdKhuyenMai").click(function() {
		$("#chkAll").prop("checked", false);
		var data = $(this).attr("data-value");
		if(data ==0){
			a=0;
			findSanPham(a);
		}
		else if(data == 1){
			$("#bang").empty();
			console.log(arr);
			for(var m = 0;m<arr.idSanPhams.length;m++){
				var n = arr.idSanPhams[m];
				$.ajax({
					url:"http://localhost:8762/sanPham/admin/sanPham/"+n,
					headers: {
				        'Authorization': "Bearer "+getCookie("tokenJWT")
				    },
					contentType: 'application/json; charset=utf-8',
					dataType: 'json',
					type:"GET",
					success: function(i){
						$("#bang").append(
								'<tr class="hang" data-value="'+i.id+'">'
												+'<td>' + i.id +'</td>'
												+'<td>' +i.tenSanPham +'</td>'
												+'<td>' + i.giaTien +'</td>'
												+'<td>' + i.chatLieu +'</td>'
												+'<td>' + i.luotXem +'</td>'
												+'<td>' + i.khuyenMai +'</td>'
												+'<td>' + i.loaiSanPhamDTO.tenLoai +'</td>'
												+'<td>'
													+'<img '
														+'class="sanPham" '
														+'data="'+i.id+'" '
														+'src="http://localhost:8762/hinhAnh/image/'+ i.hinhAnhDTOs[0].url+'" '
														+'style="background-size: cover;width: 100px;" />'
												+'</td>'
												+'<td>'+
													'<input class="chkItem" type="checkbox" checked />'
												+'</td>'
											+'</tr>'	
							);
					}
				})
			}
		}
	})
	
	//tenLoai = $("#tenLoai").text();
	$("#xemThem").click(function() {
		a++;
		if(kt){
			findSanPhamByTen($("#timKiem").val(),a)
		}
		else{
			findSanPham(a);
		}
		$('body,html').animate({
			scrollTop: 0
		})
	})
	
	$("#sau").click(function() {
		a++;
		if(kt){
			findSanPhamByTen($("#timKiem").val(),a)
		}
		else{
			findSanPham(a);
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
			findSanPhamByTen($("#timKiem").val(),a)
		}
		else{
			findSanPham(a);
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
	
	$("body").on("click",".btnMau",function(){
		idMau = $(this).attr("data-value");
		maMau = $(this).attr("data-maMau");
		a=0;
		kt= false;
		$("#danhSachSanPham").empty();
		findSanPham(a);
		$('body,html').animate({
			scrollTop: 0
		})
		$("#sau").show();
		$("#hienTai").text(1);
		$("#truoc").hide();
	})
	
	$("body").on("click",".chkGia",function(){
		minGia = $(this).attr("data-min");
		maxGia = $(this).attr("data-max");
		a=0;
		kt= false;
		$("#danhSachSanPham").empty();
		findSanPham(a);
		$('body,html').animate({
			scrollTop: 0
		})
		$("#sau").show();
		$("#hienTai").text(1);
		$("#truoc").hide();
	})
	
	$("body").on("click",".btnSize",function(){
		size = $(this).attr("data-text");
		kt= false;
		a=0;
		$("#danhSachSanPham").empty();
		findSanPham(a);
		$('body,html').animate({
			scrollTop: 0
		})
		$("#sau").show();
		$("#hienTai").text(1);
		$("#truoc").hide();
	})
	
	$("body").on("click",".rdDanhMuc",function(){
		d = $(this).attr("data-value");
		if(d == 0 ){
			tenLoai = null;
		}
		else if(d == 1 ){
			tenLoai = "Nam";
		}
		else if(d == 2){
			tenLoai = "Nữ";
		}
		else if(d == 3){
			tenLoai = "Trẻ em";
		}
		kt= false;
		a=0;
		$("#danhSachSanPham").empty();
		findSanPham(a);
		$('body,html').animate({
			scrollTop: 0
		})
		$("#sau").show();
		$("#hienTai").text(1);
		$("#truoc").hide();
	})
	
	$("#lamMoi").click(function() {
		a= 0;
		minGia = 0;
		maxGia = 0;
		idMau = 0;
		size = null;
		maMau = "";
		tenLoai = null;
		kt=false;
		$("#all").prop("checked", true);
		$("#all2").prop("checked", true);
		findSanPham(a);
		$('body,html').animate({
			scrollTop: 0
		})
		$("#sau").show();
		$("#hienTai").text(1);
		$("#truoc").hide();
	})
	
	$("body").on("click",".chkItem",function(){
		var data = $(this).closest(".hang").attr("data-value");
		if($(this).is(":checked")){
			arr.idSanPhams.push(data);
			list[data] = 1;
		}
		else{
			list[data] = 0;
			for (var i = 0; i < arr.idSanPhams.length; i++) {
				var j = arr.idSanPhams[i];
				if(j == data){
					arr.idSanPhams.splice(i, 1);
					i--;
				}
			}
		}
		console.log(arr.idSanPhams)
		console.log(list)
	})
	
	$("#chkAll").click(function() {
		if($(this).is(":checked")){
			$("input[type=checkbox]").prop("checked", true);
			$(".hang").each(function() {
				var data = $(this).attr("data-value");
				if(list[data] !=1 ){
					arr.idSanPhams.push(data);
					list[data] = 1;
				}
			})
		}
		else{
			$("input[type=checkbox]").prop("checked", false);
			$(".hang").each(function() {
				var data = $(this).attr("data-value");
				list[data] = 0;
				for (var i = 0; i < arr.idSanPhams.length; i++) {
					var j = arr.idSanPhams[i];
					if(j == data){
						arr.idSanPhams.splice(i, 1);
						i--;
					}
				}
			})
			
		}
		console.log(arr.idSanPhams)
		console.log(list)
		$(".hang").each(function() {
			
		})
	})
	
	
	$("#timKiem").change(function() {
		a=0;
		kt=true;
		findSanPhamByTen($(this).val(),a);
		$('body,html').animate({
			scrollTop: 0
		})
		$("#sau").show();
		$("#hienTai").text(1);
		$("#truoc").hide();
	})
	
	$("#btnThemKhuyenMai").click(function() {
		var khuyenMaiDTO = {};
		var id = $("#tenKhuyenMai").attr("data-value");
		khuyenMaiDTO["id"] = id;
		var tenKhuyenMai = $("#tenKhuyenMai").val();
		khuyenMaiDTO["tenKhuyenMai"] = tenKhuyenMai
		var ngayBatDauDate = new Date($("#ngayBatDau").val());
		var ngayBatDau = formatDate(ngayBatDauDate.getMonth()+1) + '-' +formatDate(ngayBatDauDate.getDate())+'-'+ngayBatDauDate.getFullYear();
		khuyenMaiDTO["ngayBatDau"] =ngayBatDau;
		var ngayKetThucDate = new Date($("#ngayKetThuc").val());
		var ngayKetThuc = formatDate(ngayKetThucDate.getMonth()+1) + '-' +formatDate(ngayKetThucDate.getDate())+'-'+ngayKetThucDate.getFullYear();
		khuyenMaiDTO["ngayKetThuc"] =ngayKetThuc;
		var phanTram = $("#phanTram").val();
		khuyenMaiDTO["phanTram"] = phanTram;
		if(tenKhuyenMai=="" || ngayBatDau==""|| ngayKetThuc==""|| phanTram==""){
			alert("Vui lòng nhập đầy đủ thông tin")
			return;
		}
		var chiTietKhuyenMaiDTOs =[];
		for (var i = 0; i < arr.idSanPhams.length; i++) {
			var j = arr.idSanPhams[i];
			chiTietKhuyenMaiDTO = {};
			chiTietKhuyenMaiDTO["idSanPham"]= j;
			chiTietKhuyenMaiDTOs.push(chiTietKhuyenMaiDTO);
		}
		khuyenMaiDTO["chiTietKhuyenMaiDTOs"] = chiTietKhuyenMaiDTOs;
		console.log(khuyenMaiDTO);
		$.ajax({
			url:"http://localhost:8762/khuyenMai/admin/khuyenMai",
			headers: {
		        'Authorization': "Bearer "+getCookie("tokenJWT")
		    },
			contentType: 'application/json; charset=utf-8',
			dataType: 'json',
			type:"POST",
			data:
				JSON.stringify(khuyenMaiDTO),
			success: function(value){
				if(value){
					alert("Thêm khuyến mại thành công")
				}
				else{
					alert("Thêm khuyến mại thất bại")
				}
				
			}
		    })
	})
	
	
});