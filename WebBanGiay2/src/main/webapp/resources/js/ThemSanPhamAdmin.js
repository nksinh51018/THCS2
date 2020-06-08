function uploadSingleFile(file) {
    var formData = new FormData();
    formData.append("file", file);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8082/uploadFile");
    xhr.send(formData);
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
	var idSanPham = $("#id").val();
	$("body").on("change",".mau",function(){
        var val = $(this).children("option:selected").attr("data-value");
        if(val == 1){
        	$(this).closest(".hang").find(".mauMoi").show();
        }
        else{
        	$(this).closest(".hang").find(".mauMoi").hide();
        }
        var maus=[];
        var tens=[];
        $("#containerHinhAnh").empty();
        $("#containerChiTiet> .bao").each(function() {
        	var m = true;
        	var cha = $(this);
        	var a = $(this).find(".mau").attr("data");
        	var b = $(this).find(".mau").val();
        	//var c = $(this).find(".mau").children("option:selected").attr("data-hinhAnh");
        	console.log(b);
        	console.log(a);
        	var kt = true;
        	for(var i = 0;i<maus.length;i++){
        		if(a == maus[i] && typeof a !== 'undefined')
        			kt=false;
        	}
        	if(kt){
        		maus.push(a);
            	var hinhAnhClone = $("#hinhAnhMoi").clone();
            	var child = hinhAnhClone.children();
            	child.find(".mauHa").val(b);
            	if(typeof a !== 'undefined'){
            		child.find(".mauHa").attr("data-idmau",a);
            		$.ajax({
            			url:"http://localhost:8762/hinhAnh/sanPham/"+idSanPham+"/"+a,
            			type:"GET",
            			success: function(value){
            				child.find(".sanPham").attr("src","http://localhost:8762/hinhAnh/image/"+value)
            			},
            		})
            	}
            	else{
            		var d = $(this).find(".mau").children("option:selected").attr("data");
            		console.log(d);
            		if(typeof d !== 'undefined'){
                		child.find(".mauHa").attr("data-idmau",d);
            		}
            		else{
            			var c = cha.find(".tenMauMoi").val();
            			var k = true
            			for(var i = 0;i<tens.length;i++){
                    		if(c == tens[i])
                    			k=false;
                    	}
            			if(k){
            				tens.push(c);
            				child.find(".mauHa").val("Màu mới: "+c);
		            		child.find(".mauHa").attr("data-tenMau",c);
            			}
            			else{
            				m = false;
            			}
	            		
            		}
            		
            		
            	}
            	$("#containerHinhAnh").append(child)
        	}
        })
        
    });
	
	$("body").on("change",".tenMauMoi",function(){
		 var maus=[];
		 var tens=[];
	        $("#containerHinhAnh").empty();
	        $("#containerChiTiet> .bao").each(function() {
	        	var m = true;
	        	var cha = $(this);
	        	var a = $(this).find(".mau").attr("data");
	        	var b = $(this).find(".mau").val();
	        	console.log(b);
	        	console.log(a);
	        	var kt = true;
	        	for(var i = 0;i<maus.length;i++){
	        		if(a == maus[i] && typeof a !== 'undefined')
	        			kt=false;
	        	}
	        	if(kt){
	        		
	            	var hinhAnhClone = $("#hinhAnhMoi").clone();
	            	var child = hinhAnhClone.children();
	            	child.find(".mauHa").val(b);
	            	child.find(".mauHa").attr("data-idmau",a);
	            	if(typeof a !== 'undefined'){
	            		maus.push(a);
	            		$.ajax({
	            			url:"http://localhost:8762/hinhAnh/sanPham/"+idSanPham+"/"+a,
	            			type:"GET",
	            			success: function(value){
	            				child.find(".sanPham").attr("src","http://localhost:8762/hinhAnh/image/"+value)
	            			},
	            		})
	            	}
	            	else{
	            		var d = $(this).find(".mau").children("option:selected").attr("data");
	            		console.log(d);
	            		if(typeof d !== 'undefined'){
	                		child.find(".mauHa").attr("data-idmau",d);
	            		}
	            		else{
	            			var c = cha.find(".tenMauMoi").val();
	            			var k = true
	            			for(var i = 0;i<tens.length;i++){
	                    		if(c == tens[i])
	                    			k=false;
	                    	}
	            			if(k){
	            				tens.push(c);
	            				child.find(".mauHa").val("Màu mới: "+c);
			            		child.find(".mauHa").attr("data-tenMau",c);
	            			}
	            			else{
	            				m = false;
	            			}
		            		
	            		}
	            		
	            	}
	            	if(m){
	            		$("#containerHinhAnh").append(child)
	            	}
	            	
	        	}
	        })
	})
	var files=[];
	var tenhinh="";
	$("body").on("change",".hinhAnh",function(){
		files=event.target.files;
		tenhinh=files[0].name;
		var s = $(this)
		console.log(files[0].type);
		if(files[0].type == "image/png" || files[0].type == "image/jpeg"){
			uploadSingleFile(files[0]);
			//console.log(s.closest(".baoHa"));
			setTimeout(() => {
				
			}, 10000);
			s.closest(".baoHa").find(".sanPham").attr("src","http://localhost:8762/hinhAnh/image/"+tenhinh);
		}
		
		//uploadSingleFile(files[0],s,tenhinh);
	})
	
	$("#themChiTiet").click(function() {
		var chiTietClone = $("#chiTietMoi").clone();
		$("#containerChiTiet").append(chiTietClone.children());
	})
	
	
	$("body").on("click",".xoaB",function(){
		$(this).closest(".bao").remove();
		var maus=[];
        $("#containerHinhAnh").empty();
        $("#containerChiTiet> .bao").each(function() {
        	var cha = this;
        	var a = $(this).find(".mau").attr("data");
        	var b = $(this).find(".mau").val();
        	//var c = $(this).find(".mau").children("option:selected").attr("data-hinhAnh");
        	console.log(b);
        	var kt = true;
        	for(var i = 0;i<maus.length;i++){
        		if(a == maus[i])
        			kt=false;
        	}
        	if(kt){
        		maus.push(a);
            	var hinhAnhClone = $("#hinhAnhMoi").clone();
            	var child = hinhAnhClone.children();
            	child.find(".mauHa").val(b);
            	if(typeof a !== 'undefined'){
            		child.find(".mauHa").attr("data-idmau",a);
            		$.ajax({
            			url:"http://localhost:8762/hinhAnh/sanPham/"+idSanPham+"/"+a,
            			type:"GET",
            			success: function(value){
            				child.find(".sanPham").attr("src","http://localhost:8762/hinhAnh/image/"+value)
            			},
            		})
            	}
            	else{
            		var d = $(this).find(".mau").children("option:selected").attr("data");
            		if(typeof d !== 'undefined'){
                		child.find(".mauHa").attr("data-idmau",d);
            		}
            	}
            	
            	$("#containerHinhAnh").append(child)
        	}
        })
	})
	
	$("body").on("click","#themSanPham",function(){
		var id = $("#id").val();
		var tenSanPham =  $("#tenSanPham").val();
		var giaTien = $("#giaTien").val();
		var moTa = $("#moTa").val();
		var chatLieu = $("#chatLieu").val();
		var luotXem = $("#luotXem").val();
		var loaiSanPhamDTO = {};
		var tenLoai = $("#loaiSanPham").val();
		var idLoai = $("#loaiSanPham").children("option:selected").attr("data")
		loaiSanPhamDTO["id"] = idLoai;
		loaiSanPhamDTO["tenLoai"] = tenLoai;
		var sanPhamDTO ={};
		sanPhamDTO["id"] = id;
		sanPhamDTO["tenSanPham"] = tenSanPham;
		sanPhamDTO["giaTien"] = giaTien;
		sanPhamDTO["moTa"] = moTa;
		sanPhamDTO["chatLieu"] = chatLieu;
		sanPhamDTO["luotXem"] = luotXem;
		sanPhamDTO["loaiSanPhamDTO"] = loaiSanPhamDTO;
		var kt = false;
		if(tenSanPham == "" || giaTien == "" ||moTa == "" ||chatLieu == "" ||luotXem == "" ){
			
			kt = true;
			return;
		}
		
		var chiTietSanPhams = [];
		$("#containerChiTiet> .bao").each(function() {
			var a = $(this).find(".mau").attr("data");
			var chiTietSanPham = {};
			var mauDTO = {};
			if(typeof a !== 'undefined'){
				mauDTO["id"]=a;
			}
			else{
				var b = $(this).find(".mau").children("option:selected").attr("data");
				console.log("b= "+ b);
				if(typeof b !== 'undefined'){
					mauDTO["id"]=b;
				}
				else{
					var tenMau = $(this).find(".tenMauMoi").val();
					var maMau = $(this).find(".maMauMoi").val();
					if(tenMau == "" || maMau == ""){
						kt = true;
						return;
					}
					mauDTO["tenMau"] = tenMau;
					mauDTO["maMau"] = maMau;
				}
			}
			chiTietSanPham["mauDTO"] = mauDTO;
			var size = $(this).find(".size").val();
			var soLuong = $(this).find(".soLuong").val();
			if(size == "" || soLuong == ""){
				kt = true;
				return;
			}
			
			chiTietSanPham["size"] = size;
			chiTietSanPham["soLuong"] = soLuong;
			chiTietSanPhams.push(chiTietSanPham);
		})
		
		sanPhamDTO["chiTietSanPhamDTOs"] = chiTietSanPhams;
		var hinhAnhDTOs = [];
		$("#containerHinhAnh> .baoHa").each(function() {
			var hinhAnhDTO = {};
			var idMau = $(this).find(".mauHa").attr("data-idmau");
			hinhAnhDTO["idMau"] = idMau;
			var url1 = $(this).find(".sanPham").attr("src");
			if(url1 == ""){
				kt = true;
				return;
			}
			var tenMau = $(this).find(".mauHa").attr("data-tenMau");
			hinhAnhDTO["tenMau"] = tenMau;
			var s = url1.split("/");
			var url = s[s.length-1];
			hinhAnhDTO["url"] = url;
			hinhAnhDTOs.push(hinhAnhDTO);
		})
		sanPhamDTO["hinhAnhDTOs"] = hinhAnhDTOs;
		console.log(sanPhamDTO);	
		if(kt){
			alert("Vui lòng điền đầy đủ thông tin");
			return;
		}
		$.ajax({
			url:"http://localhost:8762/sanPham/admin/sanPham",
			 headers: {
			        'Authorization': "Bearer "+getCookie("tokenJWT")
			    },
		
			type:"POST",
			//dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			data:JSON.stringify(sanPhamDTO),
			success: function(value){
				if(value == true){
					alert("Thêm sản phẩm thành công")
				}
				else{
					alert("Thêm sản phẩm thất bại")
				}
				
			}
		})
	})
	
})