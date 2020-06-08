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
	console.log(getCookie("tokenJWT"))
	
	$("#btnDoiMatKhau").click(function() {
		var matKhauCu = $("#matKhauCu").val();
		var matKhauMoi = $("#matKhauMoi").val();
		var nhapLaiMatKhau = $("#nhapLaiMatKhau").val();
		var kt = true;
		if(matKhauMoi != nhapLaiMatKhau){
			kt= false;
		}
		if(!kt){
			alert("Mật khẩu mới với nhập lại mật khẩu không trùng nhau")
		}
		if(kt){
			var matKhauDTO={};
			matKhauDTO["matKhauCu"]=matKhauCu;
			matKhauDTO["matKhauMoi"]=matKhauMoi;
			console.log(matKhauDTO);
			$.ajax({
				url:"http://localhost:8762/taiKhoan/user/taiKhoan/matKhau",
				 headers: {
				        'Authorization': "Bearer "+getCookie("tokenJWT")
				    },
				type:'PUT',
				dataType: 'json',
				contentType: 'application/json; charset=utf-8',
				data:JSON.stringify(matKhauDTO),
				success: function(value){
					if(value){
						alert("Đổi mật khẩu thành công");
					}
					else{

						alert("Đổi mật khẩu thất bại");
					}
				},
				error:function(value){
					alert("Đổi mật khẩu thất bại");
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