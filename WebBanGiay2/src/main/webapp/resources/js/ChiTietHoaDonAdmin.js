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
	$("#huyHoaDon").click(function() {
		var id = $("#id").attr("data-value");
		$.ajax({
			url:"http://localhost:8762/hoaDon/user/hoaDon/id/"+id,
			headers: {
		        'Authorization': "Bearer "+getCookie("tokenJWT")
		    },
			contentType: 'application/json; charset=utf-8',
			dataType: 'json',
			type:"DELETE",
			success: function(value){
					if(value == true){
						alert("Hủy hóa đơn thành công");
						window.location.href = "http://localhost:8080/WebBanGiay2/admin/hoaDon"
					}
					else{
						alert("Hủy hóa đơn thất bại");
					}
				}
			});
	})
	
	$("#chk").change(function() {
		
		var id = $(this).attr("data-value");
		var trangThaiDTO = {};
		trangThaiDTO["id"] = id;
	
		if($(this).is(":checked")){
			trangThaiDTO["trangThai"] = 1;
			console.log(trangThaiDTO)
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
					$("#huyHoaDon").hide();
				}
			})
		}
		else{
			trangThaiDTO["trangThai"] = 0;
			console.log(trangThaiDTO)
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
					$("#huyHoaDon").show();
				}
			})
		}
	})
});