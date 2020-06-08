/**
 * 
 */
function formatDate(n){
	  if(n <= 9){
	    return "0" + n;
	  }
	  return n
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
	var gioiTinh = -1;
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
	
	
	$("#btnDangKi").click(function() {
		var ten = $("#tenNguoiDung").val();
		var tenDangNhap = $("#tenDangNhap").val();
		var matKhau = $("#matKhau").val();
		var diaChi = $("#diaChi").val();
		var sdt = $("#sdt").val();
		var ngaySinhDate = new Date($("#ngaySinh").val());
		var ngaySinh = formatDate(ngaySinhDate.getMonth()+1) + '-' +formatDate(ngaySinhDate.getDate())+'-'+ngaySinhDate.getFullYear();
		console.log(ngaySinhDate);
		var kt = true;
		if(ten==null){
			$("#errorTenNguoiDung").text("Vui lòng điền đầy đủ thông tin");
			kt = false;
		}
		
		var nhapLaiMatKhau = $("#nhapLaiMatKhau").val();
		if(nhapLaiMatKhau != matKhau){
			kt = false;
			$("#errorNhapLaiMatKhau").text("Mật khẩu không trùng khớp");
		}
		
		
		if(tenDangNhap==""){
			$("#errorTenDangNhap").text("Vui lòng điền đầy đủ thông tin");
			kt = false;
		}
		if(matKhau==""){
			$("#errorMatKhau").text("Vui lòng điền đầy đủ thông tin");
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
			taiKhoanDTO["matKhau"]=matKhau;
			taiKhoanDTO["diaChi"]=diaChi;
			taiKhoanDTO["sdt"]=sdt;
			taiKhoanDTO["ngaySinh"]=ngaySinh;
			taiKhoanDTO["gioiTinh"]=gioiTinh;
			taiKhoanDTO["vaiTro"]="USER";
			console.log(taiKhoanDTO);
			$.ajax({
				url:"http://localhost:8762/taiKhoan/taiKhoan",
				type:"POST",
				contentType: 'application/json; charset=utf-8',
				data:JSON.stringify(taiKhoanDTO),
				success: function(value){
					if(value == ""){
						alert("Tên đăng nhập đã bị trùng");
					}
					else{
						alert("Đăng kí thành công");
			        	console.log(value);
			        	$.ajax({
			    	        type: "POST",
			    	        contentType: 'application/json; charset=utf-8',
			    			dataType: 'json',
			    	        url: "/WebBanGiay2/cookie",
			    	        data: value
			    	        ,
			    	        success: function(data) {
			    	        	window.history.go(-1);
			    	        }
			        	})
					}
					
				},
				error:function(value){
					console.log(value);
					alert("Đã có lỗi xảy ra");
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