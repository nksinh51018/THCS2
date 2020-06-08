$(document).ready(function(){
	
	$("#dangNhap").click(function() {
		var tenDangNhap = $("#TenDangNhap").val();
		var matKhau = $("#MatKhau").val();
		var taiKhoan = {};
		taiKhoan["username"] = tenDangNhap;
		taiKhoan["password"] = matKhau;
		
		var a =$.ajax({
	        type: "POST",
	        contentType: 'application/json; charset=utf-8',
			//dataType: 'json',
	        url: "http://localhost:8762/auth",
	        data: JSON.stringify(taiKhoan),
	        success: function(data) {
	        	 var d = new Date();
	        	  d.setTime(d.getTime() + (1*24*60*60*1000));
	        	  var expires = "expires="+ d.toUTCString();
	        	localStorage.token = a.getResponseHeader('Authorization');
	        	//document.cookie = "tokenJWT="+a.getResponseHeader('Authorization')+";"+expires;
	        	var data = a.getResponseHeader('Authorization').toString();
	        	console.log(data);
	        	$.ajax({
	    	        type: "POST",
	    	        contentType: 'application/json; charset=utf-8',
	    			dataType: 'json',
	    	        url: "/WebBanGiay2/cookie",
	    	        data: data
	    	        ,
	    	        success: function(data) {
	    	        	window.history.go(-1);
	    	        },
	    	        error: function(data) {
	    	        	alert("Sai tên đăng nhập hoặc mật khẩu")
	    	        },
	        	})
	        },
	        error: function(data, textStatus, request) {
	        	//alert(a.getResponseHeader('Authorization'));
	        	alert("Sai tên đăng nhập hoặc mật khẩu");
	        }
	      });
	})
	
})