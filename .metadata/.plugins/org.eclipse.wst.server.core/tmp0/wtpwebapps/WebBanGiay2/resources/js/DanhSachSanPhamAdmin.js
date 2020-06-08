function format(n) {
  return n.toFixed(0).replace(/./g, function(c, i, a) {
    return i > 0 && c !== "." && (a.length - i) % 3 === 0 ? "." + c : c;
  });
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
var minGia = 0;
var maxGia = 0;
var idMau = 0;
var size = null;
var maMau = "";
var tenLoai = null;
var kt = false;

function findSanPham(a) {
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
								+'</tr>'	
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

function findSanPhamByTen(ten,a) {
	console.log(ten)
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
								+'</tr>'	
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
		console.log($("#all"))
		$("#timKiem").val("")
		findSanPham(a);
		$('body,html').animate({
			scrollTop: 0
		})
		$("#sau").show();
		$("#hienTai").text(1);
		$("#truoc").hide();
	})
	
	
	$("body").on("click",".hang",function(){
		window.location.href = "/WebBanGiay2/admin/chiTietSanPham/"+$(this).attr("data-value")
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
});