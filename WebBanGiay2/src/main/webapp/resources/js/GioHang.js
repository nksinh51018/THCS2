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

function formatDate(n){
	  if(n <= 9){
	    return "0" + n;
	  }
	  return n
	}
$(document).ready(function(){
	console.log("asd" + getCookie("tokenJWT"));
	
	$("#diachicu").click(function() {
		var text = $(this).text();
		$("#formDiaChi").hide();
		$("#btnDiachi").text(text);
	})
	
	$("#diachimoi").click(function() {
		var text = $(this).text();
		$("#btnDiachi").text(text);
		$("#formDiaChi").show();
	})
	
	$("body").on("click",".xoa-dh",function() {
		var cha= $(this).closest(".hang");
		var idSanPham = $(this).closest(".hang").find(".sanPham").attr("data");
		var idMau = $(this).closest(".hang").find(".mau").attr("data-value");
		var size = $(this).closest(".hang").find(".size").attr("data");
		var gia = $(this).closest(".hang").find(".gia").attr("data-value");
		console.log(gia);
		var soLuong = $(this).closest(".hang").find(".soLuong").val();
		var gioHangDTO={};
		gioHangDTO["idSanPham"] = idSanPham;
		gioHangDTO["idMau"] =idMau;
		gioHangDTO["size"] =size;
		$.ajax({
			url:"/WebBanGiay2/api/gioHang",
			type:"DELETE",
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			data:JSON.stringify(gioHangDTO),
			success: function(value){
				cha.remove();
				var tongTienText = $("#tongTien-dh").attr("data");
				var t= gia * soLuong;
				var tongTien  = parseInt(tongTienText) - gia * soLuong;
				console.log(gia);
				$("#tongTien-dh").text("Tổng tiền: "+format(tongTien)+" d");
				$("#tongTien-dh").attr("data",tongTien);
				var soLuongText = $("#soluong-dh").attr("data");
				var tongSo  = parseInt(soLuongText) - soLuong;
				$("#soluong-dh").text("Số lượng: "+ tongSo);
				$("#soluong-dh").attr("data",tongSo);
				var l = $("#soluonggiohang").text();
				var  t = parseInt(l) -1;
				if(t==0){
					$("#soluonggiohang").hide();
				}
				else{
					$("#soluonggiohang").addClass("circle_so");
					$("#soluonggiohang").text(t);
				}
				$(".xoa").each(function() {
					var cha2= $(this).closest(".row");
					var idSanPham2 = $(this).closest(".row").find(".sanPham").attr("data");
					var idMau2 = $(this).closest(".row").find(".mau").attr("data");
					var idSize2 = $(this).closest(".row").find(".size").attr("data");
					if(idSanPham2 == idSanPham && idMau2== idMau &&  idSize2 == size){
						cha2.remove();
					}
					
				})
			}
		});
	})
	
	$("body").on("click","#dathang",function() {
		var idTaiKhoan=$("#selectDiaChi").attr("date-value");
		var tenNguoiNhan=$("#txtTen").val();
		var diaChi=$("#txtDiaChi").val();
		var sdt=$("#txtSDT").val();
		var today =new Date();
		var ngayMua= formatDate(today.getMonth()+1) + '-' +formatDate(today.getDate())+'-'+today.getFullYear();
		var trangThai=0;
		var hoaDonDTO={};
		hoaDonDTO["idTaiKhoan"]=idTaiKhoan;
		hoaDonDTO["tenNguoiNhan"]=tenNguoiNhan;
		hoaDonDTO["diaChi"]=diaChi;
		hoaDonDTO["sdt"]=sdt;
		hoaDonDTO["ngayMua"]=ngayMua;
		hoaDonDTO["trangThai"]=trangThai;
		if(diaChi=="" || tenNguoiNhan=="" || sdt==""){
			alert("Vui lòng điền đầy đủ thông tin người nhận");
		}
		else{
			var chiTietHoaDons=[];
			$(".hang").each(function(){
				var chiTietHoaDon={};
				var mauDTO={};
				chiTietHoaDon["idSanPham"]=$(this).find(".sanPham").attr("data");
				mauDTO["id"]=$(this).find(".mau").attr("data-value")
				chiTietHoaDon["mauDTO"]=mauDTO
				chiTietHoaDon["size"]=$(this).find(".size").attr("data");
				chiTietHoaDon["soLuong"]=$(this).find(".soLuong").val();
				chiTietHoaDon["gia"]=Math.ceil($(this).find(".gia").attr("data-value"));
				chiTietHoaDons.push(chiTietHoaDon);
			});
			hoaDonDTO["chiTietHoaDons"]=chiTietHoaDons;
			console.log(hoaDonDTO);
			$.ajax({
				url:"http://localhost:8762/hoaDon/user/hoaDon",
				 headers: {
				        'Authorization': "Bearer "+getCookie("tokenJWT")
				    },
			
				type:"POST",
				dataType: 'json',
				contentType: 'application/json; charset=utf-8',
				data:JSON.stringify(hoaDonDTO),
				success: function(value){
					if(value){
						alert("Đặt hàng thành công");
					}
					else{

						alert("Đặt hàng thất bại");
					}
				},
				error:function(value){
					alert("Đặt hàng thất bại");
				},
			})
		}
	})
	
	$(".soLuong").change(function(){
		var tongTien = 0;
		var tongSo = 0;
		
		$(".hang").each(function(){
			var gia = $(this).find(".gia").attr("data-value");
			console.log(gia);
			var soLuong = $(this).find(".soLuong").val();
			tongTien+= gia * soLuong;
			soLuong2= parseInt(soLuong);
			tongSo += soLuong2;
		});
		
		$("#tongTien-dh").text("Tổng tiền: "+format(tongTien)+" d");
		$("#soluong-dh").text("Số lượng: "+ tongSo);
		$("#tongTien-dh").attr("data",tongTien);
		$("#soluong-dh").attr("data",tongSo);
	});
		
})