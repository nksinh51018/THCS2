function format(n) {
  return n.toFixed(0).replace(/./g, function(c, i, a) {
    return i > 0 && c !== "." && (a.length - i) % 3 === 0 ? "." + c : c;
  });
}
$(document).ready(function(){
	var a= 0;
	var tenSanPham = $("#tenTimKiem").attr("data-value")
	$("#xemThem").click(function() {
		a++;
		$.ajax({
			url:"http://localhost:8762/sanPham/user/sanPham/timKiem/"+tenSanPham+"/"+a,
			type:"GET",
			success: function(value){
				console.log(value);
				for(var j=0;j<value.sanPhamDTOs.length;j++){
					var i= value.sanPhamDTOs[j];
					var url1 = "http://localhost:8762/hinhAnh/image/"+i.hinhAnhDTOs[0].url;
					var url2;
					console.log(i.hinhAnhDTOs.length)
					if(i.hinhAnhDTOs.length < 2){
						url2 = "http://localhost:8080/WebBanGiay2/resources/image/unnamed.png";
					}
					else{
						url2 = "http://localhost:8762/hinhAnh/image/"+i.hinhAnhDTOs[1].url;
					}
					if(i.khuyenMai != 0){
						var kmf = parseFloat(i.giaTien) * ( 100- i.khuyenMai) /100;
						var km = Math.floor(kmf);
						$("#danhSachSanPham").append(
								'<div class="col-md-3">' +
							'<div style="padding: 5px;position: relative;" > '+
								'<a href= "/WebBanGiay2/chiTiet/'+i.id+'">'+
								'<div class="card" style="height: 400px">'+
									'<img '+
										'src="'+ url1 +'" '+
										'class="card-img-top"'+
										'onmouseover="this.src= \'' + url2  + '\'" '+
										'onmouseout="this.src= \''+ url1 + '\'" '+
										'style="transition:2s;background-size: cover;margin: auto;margin-bottom: 100px"'+
										 '/>'+
									'<div class="card-body" style="position: absolute;bottom: 0;color: black">'+
										'<h5 class="card-title">'+i.tenSanPham +'</h5>'+
										'<p class="card-text"><strike style="color: red">'+ format(parseInt(i.giaTien)) +' d</strike>'+
										' '+format(km)+' d</p>');
					}
					else{
						$("#danhSachSanPham").append(
								'<div class="col-md-3">' +
							'<div style="padding: 5px;position: relative;" > '+
								'<a href= "/WebBanGiay2/chiTiet/'+i.id+'">'+
								'<div class="card" style="height: 400px">'+
									'<img '+
										'src="'+ url1 +'" '+
										'class="card-img-top"'+
										'onmouseover="this.src= '+ url2 + '" '+
										'onmouseout="this.src= '+ url1 + '" '+
										'style="transition:2s;background-size: cover;margin: auto;margin-bottom: 100px"'+
										 '/>'+
									'<div class="card-body" style="position: absolute;bottom: 0;color: black">'+
										'<h5 class="card-title">'+i.tenSanPham +'</h5>'+
										'<p class="card-text">'+format(parseInt(i.giaTien)) +' d</p>');
					}
					
					$("#danhSachSanPham").append(
							'</div></div></a></div>'
					);
				}
				if(!value.check){
					$("#xemThem").remove();
				}
				
			},
		});
	})
});