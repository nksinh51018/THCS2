function format(n) {
  return n.toFixed(0).replace(/./g, function(c, i, a) {
    return i > 0 && c !== "." && (a.length - i) % 3 === 0 ? "." + c : c;
  });
}
$(document).ready(function(){
	
	$(".hinhAnh").click(function() {
		var url = $(this).attr("data-value");
		console.log(url);
		$("#hinhAnhTo").attr("src","http://localhost:8762/hinhAnh/image/"+url);
	})
	
	var mauSize= $("#mau-size").text();
	var jsonMauSize= JSON.parse(mauSize);
	console.log(jsonMauSize);
	
	var mauHinhAnh= $("#mau-hinhAnh").text();;
	var jsonMauHinhAnh= JSON.parse(mauHinhAnh);
	console.log(jsonMauHinhAnh);
	
	var idSanPham = $("#tenSanPham").attr("data-value");
	var idMau = 0;
	var size="";
	var u = $("#hinhAnhTo").attr('src');
	
	var url=u.spili;
	var tenMau="";
	var tenSanPham = $("#tenSanPham").text();
	var gia=$("#gia").attr("data-value");
	var khuyenMai = $("#gia").attr("data-khuyenMai");
	var maMau="";
	
	$(".btnMau").click(function() {
		var a= $(this).attr("data-value");
		idMau = a;
		console.log(a);
		tenMau = $(this).attr("data-tenMau");
		maMau = $(this).attr("data-maMau");
		for(var i = 0; i< jsonMauHinhAnh.length; i++){
			if( jsonMauHinhAnh[i].mau==a){
				$("#hinhAnhTo").attr('src',"http://localhost:8762/hinhAnh/image/"+jsonMauHinhAnh[i].hinhAnh);
				url = jsonMauHinhAnh[i].hinhAnh;
			}
		}
		var b=[];
		$("#textMau").text("Chọn màu: "+ $(this).attr("data-tenMau"));
		$("#textSize").text("Chọn size: ");
		$("#textMau").attr("data-value",$(this).attr("data-value"));
		$("#textMau").attr("data-tenMau",$(this).attr("data-tenMau"));
		$("#textMau").attr("data-maMau",$(this).attr("data-maMau"));
		for(var i = 0; i< jsonMauSize.length; i++){
			if( jsonMauSize[i].mau==a){
				b.push(jsonMauSize[i].size);
			}
		}
		console.log(url);
		console.log(b);
		$(".btnSize").each(function() {
			var c =  $(this).attr("data-value");
			$(this).attr("disabled", true);
			for(var i = 0; i< b.length; i++){
				if( c==b[i]){
					$(this).attr("disabled", false);
				}
			}
		})
	});
	$(".btnSize").click(function() {
		size = $(this).attr("data-value");
		$("#textSize").text("Chọn size: "+ $(this).text());
		$("#textSize").attr("data-value",$(this).attr("data-value"));
		//$("#textSize").attr("data-text",$(this).attr("data-text"));
		
	});
	
	$("#themGioHang").click(function() {
		
		if(idMau <= 0 || size ==""){
			alert("Vui lòng chọn màu và size của giày")
		}
		else{
			$('body,html').animate({
				scrollTop: 0
			})
			var gioHangDTO={};
			var soLuong = $("#soLuong").val();
			gioHangDTO["idSanPham"] = idSanPham;
			gioHangDTO["idMau"] =idMau;
			gioHangDTO["size"] =size;
			gioHangDTO["soLuong"] =soLuong;
			gioHangDTO["url"] =url;
			gioHangDTO["tenMau"] =tenMau;
			gioHangDTO["gia"] =gia;
			gioHangDTO["khuyenMai"] =khuyenMai;
			gioHangDTO["tenSanPham"] =tenSanPham;
			gioHangDTO["maMau"] =maMau;
			$.ajax({
				url:"/WebBanGiay2/api/gioHang",
				contentType: 'application/json; charset=utf-8',
				dataType: 'json',
				type:"POST",
				data:
					JSON.stringify(gioHangDTO),
				success: function(value){
					console.log(value);
					$("#soluonggiohang").addClass("circle_so");
					var l = $("#soluonggiohang").text();
					var t = 0;
					if(l != ""){
						t = parseInt(l);
					}
					if(!value){
						t++;
					}
					$("#soluonggiohang").text(t);
					alert("Thêm sản phẩm thành công");
					
					if(!value){
						$("#menu-content").append('<div>'
								+'<div class="container">'
									+'<div class="row rowGioHang">'
										+'<div class="col-md-3">'
											+'<img '
											+'src="http://localhost:8762/hinhAnh/image/'+url+'" '
											+'style="background-size: cover;width: 50%;"/>'
										+'</div>'
										+'<div class="col-md-8">'
											+'<span data="'+idSanPham+'" class="sanPham">Tên sản phẩm: '+tenSanPham+'</span><br/>'
											+'<span data="'+idMau+'" class="mau">Màu: '+tenMau+'</span><br/>'
											+'<span data="'+  size+'" class="size">Size: '+ size+'</span><br/>'
											+'<span>Số lượng: '+soLuong +'</span><br/>'
											+'<span>Tổng tiền: '+format(soLuong *gia *(100 - khuyenMai) / 100) +' d</span>'
										+'</div>'
										+'<div class="xoa col-md-1">'
										+	'<span>Xoá</span>'
										+'</div>'
									+'</div>'
								+'</div>'
							+'</div>'
							+'<div class="dropdown-divider"></div>');
					}
					else{
						$.ajax({
							url:"/WebBanGiay2/api/gioHang",
							
							type:"GET",
							
							success: function(value){
								$("#menu-content").empty();
								var length = value.gioHangDTOs.length;
								for(var i=0;i<length;i++){
									var gh = value.gioHangDTOs[i];
									$("#menu-content").append('<div>'
											+'<div class="container">'
												+'<div class="row rowGioHang">'
													+'<div class="col-md-3">'
														+'<img '
														+'src="http://localhost:8762/hinhAnh/image/'+gh.url+'" '
														+'style="background-size: cover;width: 50%;"/>'
													+'</div>'
													+'<div class="col-md-8">'
														+'<span data="'+gh.idSanPham+'" class="sanPham">Tên sản phẩm: '+gh.tenSanPham+'</span><br/>'
														+'<span data="'+gh.idMau+'" class="mau">Màu: '+gh.tenMau+'</span><br/>'
														+'<span data="'+  gh.size+'" class="size">Size: '+ gh.size+'</span><br/>'
														+'<span>Số lượng: '+gh.soLuong +'</span><br/>'
														+'<span>Tổng tiền: '+format(gh.soLuong *gh.gia *(100 - gh.khuyenMai) / 100) +' d</span>'
													+'</div>'
													+'<div class="xoa col-md-1">'
													+	'<span>Xoá</span>'
													+'</div>'
												+'</div>'
											+'</div>'
										+'</div>'
										+'<div class="dropdown-divider"></div>');
								}
								
							},
							});
					}
					
					}
				});
		}
		
		
	});
	
})