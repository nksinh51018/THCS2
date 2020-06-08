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
function format(n) {
	  return n.toFixed(0).replace(/./g, function(c, i, a) {
	    return i > 0 && c !== "." && (a.length - i) % 3 === 0 ? "." + c : c;
	  });
	}
let myChart2
function tongTien() {
	$.ajax({
		url:"http://localhost:8762/hoaDon/admin/chiTietHoaDon/tongTien",
		headers: {
	        'Authorization': "Bearer "+getCookie("tokenJWT")
	    },
		contentType: 'application/json; charset=utf-8',
		dataType: 'json',
		type:"GET",
		
		success: function(value){
			//console.log(value)
			var a= [];
			var b= [];
			for (var i = 0; i < value.chiTietSanPhamTheoDoanhThuDTOs.length; i++) {
				a.push(value.chiTietSanPhamTheoDoanhThuDTOs[i].chiTietSanPhamDTO.tenSanPham+' - '+value.chiTietSanPhamTheoDoanhThuDTOs[i].chiTietSanPhamDTO.mauDTO.tenMau+' - '+value.chiTietSanPhamTheoDoanhThuDTOs[i].chiTietSanPhamDTO.size)
				b.push(value.chiTietSanPhamTheoDoanhThuDTOs[i].tongTien)
			}
			//console.log(a);
			//console.log(b);
			
			let massPopChart2 = new Chart(myChart2, {
			      type:'horizontalBar',
			      data:{
			        labels:a,
			        datasets:[{
			          //label:'Population',
			          data:b,
			          //backgroundColor:'green',
			          backgroundColor: '#343a40',
			          borderWidth:1,
			          borderColor:'#777',
			          hoverBorderWidth:3,
			          hoverBorderColor:'#000'
			        }]
			      },
			      options:{
			        title:{
			          display:true,
			          text:'Top 10 sản phẩm bán chạy nhất',
			          fontSize:25
			        },
			        legend:{
			          display:false,
			        },
			        layout:{
			          padding:{
			            left:50,
			            right:0,
			            bottom:0,
			            top:0
			          }
			        },
			        tooltips:{
			        	enable:true,
			        	callbacks: {
			                label: function(tooltipItem, data) {
			                    return format(tooltipItem.xLabel) +' đ'
			                }
			            }
			        },
			        
			        scales: {
			            xAxes: [{
			                ticks: {
			                    beginAtZero:true,
			                    callback: function(value, index, values) {
			                        return format(value) + ' đ';
			                    }
			                }
			            }]
			        },
			      }
			    });
		}
    })
}

function soLuong() {
	$.ajax({
		url:"http://localhost:8762/hoaDon/admin/chiTietHoaDon/soLuong",
		headers: {
	        'Authorization': "Bearer "+getCookie("tokenJWT")
	    },
		contentType: 'application/json; charset=utf-8',
		dataType: 'json',
		type:"GET",
		
		success: function(value){
			//console.log(value)
			var a= [];
			var b= [];
			for (var i = 0; i < value.chiTietSanPhamTheoDoanhThuDTOs.length; i++) {
				a.push(value.chiTietSanPhamTheoDoanhThuDTOs[i].chiTietSanPhamDTO.tenSanPham+' - '+value.chiTietSanPhamTheoDoanhThuDTOs[i].chiTietSanPhamDTO.mauDTO.tenMau+' - '+value.chiTietSanPhamTheoDoanhThuDTOs[i].chiTietSanPhamDTO.size)
				b.push(value.chiTietSanPhamTheoDoanhThuDTOs[i].soLuong)
			}
			//console.log(a);
			//console.log(b);
			
			let massPopChart2 = new Chart(myChart2, {
			      type:'horizontalBar',
			      data:{
			        labels:a,
			        datasets:[{
			          //label:'Population',
			          data:b,
			          //backgroundColor:'green',
			          backgroundColor: '#343a40',
			          borderWidth:1,
			          borderColor:'#777',
			          hoverBorderWidth:3,
			          hoverBorderColor:'#000'
			        }]
			      },
			      options:{
			        title:{
			          display:true,
			          text:'Top 10 sản phẩm bán chạy nhất',
			          fontSize:25
			        },
			        legend:{
			          display:false,
			        },
			        layout:{
			          padding:{
			            left:50,
			            right:0,
			            bottom:0,
			            top:0
			          }
			        },
			        tooltips:{
			        	enable:true,
			        	callbacks: {
			                label: function(tooltipItem, data) {
			                    return format(tooltipItem.xLabel)
			                }
			            }
			        },
			        
			        scales: {
			            xAxes: [{
			                ticks: {
			                    beginAtZero:true,
			                    callback: function(value, index, values) {
			                        return format(value)
			                    }
			                }
			            }]
			        },
			      }
			    });
		}
    })
}

$(document).ready(function(){
	
	
	
	let myChart = document.getElementById('myChart').getContext('2d');
    // Global Options
    Chart.defaults.global.defaultFontFamily = 'Lato';
    Chart.defaults.global.defaultFontSize = 18;
    Chart.defaults.global.defaultFontColor = '#777';

    $.ajax({
		url:"http://localhost:8762/hoaDon/admin/hoaDon/tongTien/thang",
		headers: {
	        'Authorization': "Bearer "+getCookie("tokenJWT")
	    },
		contentType: 'application/json; charset=utf-8',
		dataType: 'json',
		type:"GET",
		
		success: function(value){
			
			var a= [];
			var b= [];
			for (var i = 0; i < value.tongTienThangs.length; i++) {
				//console.log(value.tongTienThangs[i])
				a.unshift('Tháng '+value.tongTienThangs[i].thang)
				b.unshift(value.tongTienThangs[i].tongTien)
			}
			
			
			let massPopChart = new Chart(myChart, {
			      type:'bar',
			      data:{
			        labels:a,
			        datasets:[{
			          //label:'Population',
			          data:b,
			          //backgroundColor:'green',
			          backgroundColor:'#343a40',
			          borderWidth:1,
			          borderColor:'#777',
			          hoverBorderWidth:3,
			          hoverBorderColor:'#000'
			        }]
			      },
			      options:{
			        title:{
			          display:true,
			          text:'Doanh thu các tháng gần đây',
			          fontSize:25
			        },
			        legend:{
			          display:false,
			        },
			        layout:{
			          padding:{
			            left:50,
			            right:0,
			            bottom:0,
			            top:0
			          }
			        },
			        tooltips:{
			        	enable:true,
			        	callbacks: {
			                label: function(tooltipItem, data) {
			                    return format(tooltipItem.yLabel) +" đ"
			                }
			            }
			        },
			        
			        scales: {
			            yAxes: [{
			                ticks: {
			                    beginAtZero:true,
			                    callback: function(value, index, values) {
			                        return format(value) + ' đ';
			                    }
			                }
			            }]
			        },
			      }
			    });
		}
    })
    
	myChart2 = document.getElementById('myChart2').getContext('2d');
    tongTien();
    
    $('#chon').change(function() {
		let data = $(this).find(":selected").attr("data-value");
		if(data==1){
			tongTien();
		}
		else{
			soLuong();
		}
	})
})