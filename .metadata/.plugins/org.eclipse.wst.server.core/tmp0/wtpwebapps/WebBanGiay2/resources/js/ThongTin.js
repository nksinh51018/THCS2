/**
 * 
 */
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
$(document).ready(function(){
	
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1;
	var yyyy = today.getFullYear();
	 if(dd<10){
	        dd='0'+dd
	    } 
	    if(mm<10){
	        mm='0'+mm
	    } 

	today = yyyy+'-'+mm+'-'+dd;
	var gioiTinh =$("#btnGioiTinh").attr("data-value");
	
	
	$("#ngaySinh").attr("max",today);
	
	$("#gioiTinhNam").click(function() {
		var text = $(this).text();
		$("#btnGioiTinh").text(text);
		gioiTinh= 0;
	});
	$("#gioiTinhNu").click(function() {
		var text = $(this).text();
		$("#btnGioiTinh").text(text);
		gioiTinh=1;
	});
	
	console.log(getCookie("tokenJWT"))
	$("#btnSuaThongTin").click(function() {
		var ten = $("#tenNguoiDung").val();
		var tenDangNhap = $("#tenDangNhap").val();
		var matKhau = $("#matKhau").val();
		var diaChi = $("#diaChi").val();
		var sdt = $("#sdt").val();
		var ngaySinhDate = new Date($("#ngaySinh").val());
		var ngaySinh = formatDate(ngaySinhDate.getMonth()+1) + '-' +formatDate(ngaySinhDate.getDate())+'-'+ngaySinhDate.getFullYear();
		var id = $("#tenNguoiDung").attr("data-value");
		var vaiTro = $("#tenNguoiDung").attr("data-vaiTro");
		var kt = true;
		if(ten==null){
			$("#errorTenNguoiDung").text("Vui lòng điền đầy đủ thông tin");
			kt = false;
		}
		if(tenDangNhap==""){
			$("#errorTenDangNhap").text("Vui lòng điền đầy đủ thông tin");
			kt = false;
		}
		if(diaChi==""){
			$("#errorDiaChi").text("Vui lòng điền đầy đủ thông tin");
			kt = false;
		}
		if(sdt==0){
			$("#errorSdt").text("Vui lòng điền đầy đủ thông tin");
			kt = false;
		}
		if(ngaySinh==null){
			$("#errorNgaySinh").text("Vui lòng điền đầy đủ thông tin");
			kt = false;
		}
		if(gioiTinh==-1){
			$("#errorGioTinh").text("Vui lòng điền đầy đủ thông tin");
			kt = false;
		}
		
		if(ngaySinh=="NaN-NaN-NaN"){
			$("#errorNgaySinh").text("Vui lòng điền đầy đủ thông tin");
			kt = false;
		}
		if(kt){
			var taiKhoanDTO={};
			taiKhoanDTO["ten"]=ten;
			taiKhoanDTO["tenDangNhap"]=tenDangNhap;
			taiKhoanDTO["diaChi"]=diaChi;
			taiKhoanDTO["sdt"]=sdt;
			taiKhoanDTO["ngaySinh"]=ngaySinh;
			taiKhoanDTO["gioiTinh"]=gioiTinh;
			taiKhoanDTO["id"]=id;
			taiKhoanDTO["vaiTro"]=vaiTro;
			console.log(taiKhoanDTO);
			$.ajax({
				url:"http://localhost:8762/taiKhoan/user/taiKhoan",
				 headers: {
				        'Authorization': "Bearer "+getCookie("tokenJWT")
				    },
			
				type:"PUT",
				dataType: 'json',
				contentType: 'application/json; charset=utf-8',
				data:JSON.stringify(taiKhoanDTO),
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
		$("#timkiem").click(function() {
		var a=$("#txtTimkiem").val();
		if(a.length>0&&a!=null){
			window.location.href = "/DuAn2/timkiem/"+a;
		}
		
	})
	
})