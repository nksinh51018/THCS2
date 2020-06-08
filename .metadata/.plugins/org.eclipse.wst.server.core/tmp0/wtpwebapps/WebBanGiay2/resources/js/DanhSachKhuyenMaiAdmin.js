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
var b = 0;
var kt = false;

function findKhuyenMai(a) {
	$.ajax({
		url:"http://localhost:8762/khuyenMai/admin/khuyenMai/timKiem/"+b+"/"+a,
		headers: {
	        'Authorization': "Bearer "+getCookie("tokenJWT")
	    },
		contentType: 'application/json; charset=utf-8',
		dataType: 'json',
		type:"GET",
		success: function(value){
			console.log(value);
			$("#bang").empty();
			for (var j = 0; j < value.khuyenMaiDTOs.length; j++) {
				var i = value.khuyenMaiDTOs[j];
				var res = i.ngayBatDau.split("-");
				var ngayBatDauDate = new Date(parseInt(res[2]),parseInt(res[0]),parseInt(res[1]));
				res = i.ngayKetThuc.split("-");
				var ngayKetThucDate = new Date(parseInt(res[2]),parseInt(res[0]),parseInt(res[1]));
				$("#bang").append(
					'<tr data-value='+i.id +' class="hang">'+
						'<td>'+ i.id +'</td>' +
						'<td>'+ i.tenKhuyenMai +'</td>'+
						'<td>' + 
						formatDate(ngayBatDauDate.getDate()) + '-' +formatDate(ngayKetThucDate.getMonth())+'-'+ngayBatDauDate.getFullYear()+
						'</td>'+
						
						'<td>' + 
						formatDate(ngayKetThucDate.getDate()) + '-' +formatDate(ngayKetThucDate.getMonth())+'-'+ngayKetThucDate.getFullYear()+
						'</td>'+
						'<td>'+i.phanTram +'</td>'+
					'</tr>'	
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

function findKhuyenMaiByTen(ten,a) {
	$.ajax({
		url:"http://localhost:8762/khuyenMai/admin/khuyenMai/ten/"+ten+"/"+a,
		headers: {
	        'Authorization': "Bearer "+getCookie("tokenJWT")
	    },
		contentType: 'application/json; charset=utf-8',
		dataType: 'json',
		type:"GET",
		success: function(value){
			console.log(value);
			$("#bang").empty();
			for (var j = 0; j < value.khuyenMaiDTOs.length; j++) {
				var i = value.khuyenMaiDTOs[j];
				var res = i.ngayBatDau.split("-");
				var ngayBatDauDate = new Date(parseInt(res[2]),parseInt(res[0]),parseInt(res[1]));
				res = i.ngayKetThuc.split("-");
				var ngayKetThucDate = new Date(parseInt(res[2]),parseInt(res[0]),parseInt(res[1]));
				$("#bang").append(
					'<tr data-value='+i.id +' class="hang">'+
						'<td>'+ i.id +'</td>' +
						'<td>'+ i.tenKhuyenMai +'</td>'+
						'<td>' + 
						formatDate(ngayBatDauDate.getDate()) + '-' +formatDate(ngayKetThucDate.getMonth())+'-'+ngayBatDauDate.getFullYear()+
						'</td>'+
						
						'<td>' + 
						formatDate(ngayKetThucDate.getDate()) + '-' +formatDate(ngayKetThucDate.getMonth())+'-'+ngayKetThucDate.getFullYear()+
						'</td>'+
						'<td>'+i.phanTram +'</td>'+
					'</tr>'	
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
	
	$(".rdThoiGian").click( function(){
		b = $(this).attr("data-value");
		a=0;
		findKhuyenMai(a);
		$("#sau").show();
		$("#hienTai").text(1);
		$("#truoc").hide();
	});
	$("#xemThem").click(function() {
		a++;
		if(kt){
			findKhuyenMaiByTen($("#timKiem").val(),a)
		}
		else{
			findKhuyenMai(a);
		}
		$('body,html').animate({
			scrollTop: 0
		})
		
	})
	
	$("#sau").click(function() {
		a++;
		if(kt){
			findKhuyenMaiByTen($("#timKiem").val(),a)
		}
		else{
			findKhuyenMai(a);
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
			findKhuyenMaiByTen($("#timKiem").val(),a)
		}
		else{
			findKhuyenMai(a);
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
	
	
	$("#lamMoi").click(function() {
		a= 0;
		ngayBatDau = null;
		ngayKetThuc = null;
		tienBatDau = null;
		tienKetThuc = null;
		kt = false;
		findKhuyenMai(a);
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
		window.location.href = "/WebBanGiay2/admin/chiTietKhuyenMai/"+$(this).attr("data-value")
	})
	
	$("#timKiem").change(function() {
		a=0;
		kt=true;
		findKhuyenMaiByTen($(this).val(),a);
		$('body,html').animate({
			scrollTop: 0
		})
		$("#sau").show();
		$("#hienTai").text(1);
		$("#truoc").hide();
	})
	
});