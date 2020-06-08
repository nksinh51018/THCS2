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
	var a= 0;
	
	$("#xemThem").click(function() {
		a++;
		$.ajax({
			url:"http://localhost:8762/hoaDon/user/hoaDon/"+a,
			 headers: {
			        'Authorization': "Bearer "+getCookie("tokenJWT")
			    },
			type:"GET",
			success: function(value){
				console.log(value);
				$("#danhSachHoaDon").empty();
				for(var j=0;j<value.hoaDonDTOs.length;j++){
					var i = value.hoaDonDTOs[j];
					var tongTien = 0;
					for(var h=0;h< i.chiTietHoaDons.length;h++ ){
						var t = i.chiTietHoaDons[h];
						tongTien += t.soLuong*t.gia;
					}
					var res = i.ngayMua.split("-");
					var ngayMuaDate = new Date(parseInt(res[2]),parseInt(res[0]),parseInt(res[1]))
					if(i.trangThai ==1){
						$("#danhSachHoaDon").append(
								'<tr class="hang">'
										+'<td>'+i.id+'</td>'
										+'<td>'+i.tenNguoiNhan+'</td>'
										+'<td>'+i.diaChi+'</td>'
										+'<td>'+i.sdt+'</td>'
										+'<td>'
										+formatDate(ngayMuaDate.getDate()) + '-' +formatDate(ngayMuaDate.getMonth())+'-'+ngayMuaDate.getFullYear()
										+'</td>'
										
										+'<td>'+ format(tongTien) +' d</td>'
										+'<td>'+ 'Đã gửi' +'</td>'
										);
						
					}
					else{
						$("#danhSachHoaDon").append(
								'<tr class="hang">'
										+'<td>'+i.id+'</td>'
										+'<td>'+i.tenNguoiNhan+'</td>'
										+'<td>'+i.diaChi+'</td>'
										+'<td>'+i.sdt+'</td>'
										+'<td>'
										+formatDate(ngayMuaDate.getDate()) + '-' +formatDate(ngayMuaDate.getMonth())+'-'+ngayMuaDate.getFullYear()
										+'</td>'
										
										+'<td>'+ format(tongTien) +' d</td>'
										+'<td>'+ 'Chưa gửi' +'</td>'
										);
					}
					
					
					$("#danhSachHoaDon").append(
							'</tr>'
					);				
								
						
					if(!value.check){
						$("#sau").hide();
					}
					else{
						$("#sau").show();
					}
					
					
				}
				
			},
		});
	})
	
	$(".hang").click(function() {
		window.location.href = "/WebBanGiay2/user/chiTietHoaDon/"+ $(this).attr("data-value");
	})
	
	
});