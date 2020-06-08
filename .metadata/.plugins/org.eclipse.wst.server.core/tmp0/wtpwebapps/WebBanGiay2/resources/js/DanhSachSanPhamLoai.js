function format(n) {
  return n.toFixed(0).replace(/./g, function(c, i, a) {
    return i > 0 && c !== "." && (a.length - i) % 3 === 0 ? "." + c : c;
  });
}

var a= 0;
var minGia = 0;
var maxGia = 0;
var idMau = 0;
var size = null;
var tenLoai = "";
var maMau = "";

function findSanPham(a,b) {
	timKiemDTO = {};
	timKiemDTO["minGia"]=minGia;
	timKiemDTO["maxGia"]=maxGia;
	timKiemDTO["idMau"]=idMau;
	timKiemDTO["size"]=size;
	console.log(timKiemDTO);
	$.ajax({
		url:"http://localhost:8762/sanPham/user/sanPham/loai/"+tenLoai+"/timKiem/"+a,
		contentType: 'application/json; charset=utf-8',
		dataType: 'json',
		type:"POST",
		data:
			JSON.stringify(timKiemDTO),
		success: function(value){
			console.log(value);
			for(var j=0;j<value.danhSachSanPhamDTO.sanPhamDTOs.length;j++){
				var i= value.danhSachSanPhamDTO.sanPhamDTOs[j];
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
					var t = 'onmouseover="this.src= \'' + url2  + '\'" '+
					'onmouseout="this.src= \''+ url1 + '\'" '
					console.log(t);
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
									'onmouseover="this.src= \'' + url2  + '\'" '+
									'onmouseout="this.src= \''+ url1 + '\'" '+
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
			if(!value.danhSachSanPhamDTO.check){
				$("#xemThem").hide();
			}
			else{
				$("#xemThem").show();
			}
			//console.log(b);
			if(b==1){
				$("#danhSachMau").empty();
				$("#danhSachMau").append(
						'<button class="btn btnMau" style="background-color: #' + maMau + ';width: 25px;height: 25px;margin: 2px;"  data-value="'+ idMau + ' " data-maMau="'+maMau+'"></button>'
				);
				if(size == null){
					$("#danhSachSize").empty();
					for(var j = 0;j < value.danhSachSizeDTO.sizes.length;j++){
						var i = value.danhSachSizeDTO.sizes[j];
						$("#danhSachSize").append(
								'<button class="btn btnSize" style="margin: 2px;background-color: white;" data-text="' + i +'">'+i+'</button>'
						);
					}
				}
				if(minGia == 0 || maxGia == 0){
					$("#danhSachGia").empty()
					for(var j = value.giaDTO.minGia - 1;j < value.giaDTO.maxGia;j+=500000){
						
						$("#danhSachGia").append(
								'<input name="gia" class="chkGia" type="radio" data-min=' + j+ ' data-max='+(j+499999) +' >' + format(parseInt(j))+' d - '+ format(parseInt(j+499999)) + ' d<br/>'
						);
					}
				}
				
			}
			else if(b==2){
				if(idMau ==0){
					$("#danhSachMau").empty();
					for(var j = 0;j < value.danhSachMauDTO.mauDTOs.length;j++){
						var i = value.danhSachMauDTO.mauDTOs[j];
						$("#danhSachMau").append(
								'<button class="btn btnMau" style="background-color: #' + i.maMau + ';width: 25px;height: 25px;margin: 2px;"  data-value="'+ i.id + ' " data-maMau="'+i.maMau+'"></button>'
						);
					}
				}
				if(size == null){
					
					$("#danhSachSize").empty();
					for(var j = 0;j < value.danhSachSizeDTO.sizes.length;j++){
						var i = value.danhSachSizeDTO.sizes[j];
						$("#danhSachSize").append(
								'<button class="btn btnSize" style="margin: 2px;background-color: white;" data-text="' + i +'">'+i+'</button>'
						);
					}
				}
				
				$("#danhSachGia").empty()
				$("#danhSachGia").append(
						'<input name="gia" class="chkGia" type="radio" data-min=' + minGia+ ' data-max='+maxGia +' >' + format(parseInt(minGia))+' d - '+ format(parseInt(maxGia)) + ' d<br/>'
				);
				
			}
			else if(b==3){
				if(idMau ==0){
				$("#danhSachMau").empty();
				for(var j = 0;j < value.danhSachMauDTO.mauDTOs.length;j++){
					var i = value.danhSachMauDTO.mauDTOs[j];
					$("#danhSachMau").append(
							'<button class="btn btnMau" style="background-color: #' + i.maMau + ';width: 25px;height: 25px;margin: 2px;"  data-value="'+ i.id + ' " data-maMau="'+i.maMau+'"></button>'
					);
				}
				}
				$("#danhSachSize").empty();
				
				$("#danhSachSize").append(
						'<button class="btn btnSize" style="margin: 2px;background-color: white;" data-text="' + size +'">'+size+'</button>'
				);
				if(minGia == 0 || maxGia == 0){
				$("#danhSachGia").empty()
				//console.log( value.giaDTO.minGia);
					for(var j = value.giaDTO.minGia -1;j < value.giaDTO.maxGia;j+=500000){
					
					$("#danhSachGia").append(
							'<input name="gia" class="chkGia" type="radio" data-min=' + j+ ' data-max='+(j+499999) +' >' + format(parseInt(j))+' d - '+ format(parseInt(j+499999)) + ' d<br/>'
					);
				}
				}
			}
			else{
				$("#danhSachMau").empty();
				for(var j = 0;j < value.danhSachMauDTO.mauDTOs.length;j++){
					var i = value.danhSachMauDTO.mauDTOs[j];
					$("#danhSachMau").append(
							'<button class="btn btnMau" style="background-color: #' + i.maMau + ';width: 25px;height: 25px;margin: 2px;"  data-value="'+ i.id + ' " data-maMau="'+i.maMau+'"></button>'
					);
				}
				$("#danhSachSize").empty();
				for(var j = 0;j < value.danhSachSizeDTO.sizes.length;j++){
					var i = value.danhSachSizeDTO.sizes[j];
					$("#danhSachSize").append(
							'<button class="btn btnSize" style="margin: 2px;background-color: white;" data-text="' + i +'">'+i+'</button>'
					);
				}
				$("#danhSachGia").empty()
				//console.log( value.giaDTO.minGia);
					for(var j = value.giaDTO.minGia -1 ;j < value.giaDTO.maxGia;j+=500000){
					
					$("#danhSachGia").append(
							'<input name="gia" class="chkGia" type="radio" data-min=' + j+ ' data-max='+(j+499999) +' >' + format(parseInt(j))+' d - '+ format(parseInt(j+499999)) + ' d<br/>'
					);
				}
				
			}
			
		},
		
		
		error : function(e) {
	        console.log(e);
	      }
	});
}

$(document).ready(function(){
	tenLoai = $("#tenLoai").text();
	$("#xemThem").click(function() {
		a++;
		findSanPham(a,0);
	})
	
	
	
	$("#textlocSanPham").click(function() {
		if($('#locSanPham').css('display') == 'none'){ 
			   $('#locSanPham').show(); 
			} else { 
			   $('#locSanPham').hide(); 
		}
	})
	
	$("#textLamMoi").click(function() {
		console.log("ád")
		a= 0;
		minGia = 0;
		maxGia = 0;
		idMau = 0;
		size = null;
		maMau = "";
		$("#danhSachSanPham").empty();
		findSanPham(a,0);
		
	})
	
	$("body").on("click",".btnMau",function(){
		idMau = $(this).attr("data-value");
		maMau = $(this).attr("data-maMau");
		a=0;
		$("#danhSachSanPham").empty();
		findSanPham(a,1);
	})
	
	$("body").on("click",".chkGia",function(){
		minGia = $(this).attr("data-min");
		maxGia = $(this).attr("data-max");
		a=0;
		$("#danhSachSanPham").empty();
		findSanPham(a,2);
		
	})
	
	$("body").on("click",".btnSize",function(){
		size = $(this).attr("data-text");
		
		a=0;
		$("#danhSachSanPham").empty();
		findSanPham(a,3);
	})
});