/**
 * 
 */
$(document).ready(function(){
	
	$("#timkiem").click(function() {
		var a=$("#txtTimkiem").val();
		if(a.length>0&&a!=null){
			window.location.href = "/WebBanGiay2/timKiem/"+a;
		}
	})
	
	$("#dangXuat").click(function() {
		console.log("sadsad");
		$.ajax({
	        type: "DELETE",
	        url: "/WebBanGiay2/cookie",
	        success: function(data) {
	        	location.reload();
	        }
    	})
	})
	
	//$(".xoa").click(function()
	$("body").on("click",".xoa",function() {
		var cha= $(this).closest(".row");
		var idSanPham = $(this).closest(".row").find(".sanPham").attr("data");
		var idMau = $(this).closest(".row").find(".mau").attr("data");
		var size = $(this).closest(".row").find(".size").attr("data");
		var gioHangDTO={};
		gioHangDTO["idSanPham"] = idSanPham;
		gioHangDTO["idMau"] =idMau;
		gioHangDTO["size"] =size;
		gioHangDTO["soLuong"] =0;
		gioHangDTO["url"] ="";
		gioHangDTO["tenMau"] ="";
		gioHangDTO["gia"] =0;
		gioHangDTO["khuyenMai"] =0;
		gioHangDTO["tenSanPham"] ="";
		console.log(gioHangDTO);
		$.ajax({
			url:"/WebBanGiay2/api/gioHang",
			type:"DELETE",
			contentType: 'application/json; charset=utf-8',
			dataType: 'json',
			data:JSON.stringify(gioHangDTO),
			success: function(value){
				cha.remove();
				var l = $("#soluonggiohang").text();
				var  t = parseInt(l) -1;
				if(t==0){
					$("#soluonggiohang").hide();
				}
				else{
					$("#soluonggiohang").addClass("circle_so");
					$("#soluonggiohang").text(t);
				}
			}
		});
	})
})