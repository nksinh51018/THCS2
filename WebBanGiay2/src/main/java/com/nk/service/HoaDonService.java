package com.nk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.nk.dto.DanhSachHoaDonDTO;
import com.nk.dto.DanhSachLoaiSanPhamDTO;
import com.nk.dto.DanhSachSanPhamDTO;
import com.nk.dto.DanhSachTongTienThangDTO;
import com.nk.dto.DoanhThuNgayDTO;
import com.nk.dto.HoaDonDTO;

@Service
public class HoaDonService {

	@Autowired
	private JwtConfig jwtConfig;
	
	@Autowired
	private WebClient webClient;
	
	@HystrixCommand(fallbackMethod = "getFallBackHoaDon",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")		
			}, threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize",value = "20")
			})
	public DanhSachHoaDonDTO getHoaDon(String token) {
		
		DanhSachHoaDonDTO danhSachHoaDonDTO =webClient.get()
		        .uri("/hoaDon/user/hoaDon/0")
		        .header(jwtConfig.getHeader(), jwtConfig.getPrefix()+" "+token)
		        .retrieve()
		        .bodyToMono(DanhSachHoaDonDTO.class)
		        .block();
		
		return danhSachHoaDonDTO;
	}

	public DanhSachHoaDonDTO getFallBackHoaDon(String token) {
		return new DanhSachHoaDonDTO();
	}
	
	@HystrixCommand(fallbackMethod = "getFallBackHoaDon",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")		
			}, threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize",value = "20")
			})
	public DanhSachHoaDonDTO getAllHoaDon(String token) {
		
		DanhSachHoaDonDTO danhSachHoaDonDTO =webClient.get()
		        .uri("/hoaDon/admin/hoaDon/0")
		        .header(jwtConfig.getHeader(), jwtConfig.getPrefix()+" "+token)
		        .retrieve()
		        .bodyToMono(DanhSachHoaDonDTO.class)
		        .block();
		
		return danhSachHoaDonDTO;
	}

	public DanhSachHoaDonDTO getFallBackAllHoaDon(String token) {
		return new DanhSachHoaDonDTO();
	}
	
	@HystrixCommand(fallbackMethod = "getFallBackHoaDonById",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")		
			}, threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize",value = "20")
			})
	public HoaDonDTO getHoaDonById(String token,int id) {
		System.out.println(token);
		HoaDonDTO hoaDonDTO =webClient.get()
		        .uri("/hoaDon/user/hoaDon/id/" + id)
		        .header(jwtConfig.getHeader(), jwtConfig.getPrefix()+" "+token)
		        .retrieve()
		        .bodyToMono(HoaDonDTO.class)
		        .block();
		
		return hoaDonDTO;
	}

	public HoaDonDTO getFallBackHoaDonById(String token,int id) {
		return new HoaDonDTO();
	}
	
	@HystrixCommand(fallbackMethod = "getFallBackHoaDonAdminById",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")		
			}, threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize",value = "20")
			})
	public HoaDonDTO getHoaDonAdminById(String token,int id) {
		System.out.println(token);
		HoaDonDTO hoaDonDTO =webClient.get()
		        .uri("/hoaDon/admin/hoaDon/id/" + id)
		        .header(jwtConfig.getHeader(), jwtConfig.getPrefix()+" "+token)
		        .retrieve()
		        .bodyToMono(HoaDonDTO.class)
		        .block();
		
		return hoaDonDTO;
	}

	public HoaDonDTO getFallBackHoaDonAdminById(String token,int id) {
		return new HoaDonDTO();
	}
	
	@HystrixCommand(fallbackMethod = "getFallBackHoaDonByIdTaiKhoan",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")		
			}, threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize",value = "20")
			})
	public DanhSachHoaDonDTO getHoaDonByIdTaiKhoan(String token,int id) {
		DanhSachHoaDonDTO hoaDonDTO =webClient.get()
		        .uri("/hoaDon/admin/taiKhoan/"+id+"/0")
		        .header(jwtConfig.getHeader(), jwtConfig.getPrefix()+" "+token)
		        .retrieve()
		        .bodyToMono(DanhSachHoaDonDTO.class)
		        .block();
		
		return hoaDonDTO;
	}

	public DanhSachHoaDonDTO getFallBackHoaDonByIdTaiKhoan(String token,int id) {
		return new DanhSachHoaDonDTO();
	}
	
	@HystrixCommand(fallbackMethod = "getFallBackTongTienByNgay",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")		
			}, threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize",value = "20")
			})
	public DoanhThuNgayDTO getTongTienByNgay(String token) {
		DoanhThuNgayDTO a =webClient.get()
		        .uri("/hoaDon/admin/hoaDon/tongTien")
		        .header(jwtConfig.getHeader(), jwtConfig.getPrefix()+" "+token)
		        .retrieve()
		        .bodyToMono(DoanhThuNgayDTO.class)
		        .block();
		
		return a;
	}

	public DoanhThuNgayDTO getFallBackTongTienByNgay(String token) {
		return null;
	}
	
	@HystrixCommand(fallbackMethod = "fallBacksoSachHomQua",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")		
			}, threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize",value = "20")
			})
	public float soSachHomQua(String token) {
		float a =webClient.get()
		        .uri("/hoaDon/admin/hoaDon/tongTien/homQua")
		        .header(jwtConfig.getHeader(), jwtConfig.getPrefix()+" "+token)
		        .retrieve()
		        .bodyToMono(Float.class)
		        .block();
		
		return a;
	}

	public float fallBacksoSachHomQua(String token) {
		return 0;
	}
	
	@HystrixCommand(fallbackMethod = "fallBacksoSachThangTruoc",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")		
			}, threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize",value = "20")
			})
	public float soSachThangTruoc(String token) {
		float a =webClient.get()
		        .uri("/hoaDon/admin/hoaDon/tongTien/thangTruoc")
		        .header(jwtConfig.getHeader(), jwtConfig.getPrefix()+" "+token)
		        .retrieve()
		        .bodyToMono(Float.class)
		        .block();
		
		return a;
	}

	public float fallBacksoSachThangTruoc(String token) {
		return 0;
	}
	

	@HystrixCommand(fallbackMethod = "getFallBackTongTienThang",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")		
			}, threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize",value = "20")
			})
	public DanhSachTongTienThangDTO getTongTienThang(String token) {
		DanhSachTongTienThangDTO a =webClient.get()
		        .uri("/hoaDon/admin/hoaDon/tongTien/thang")
		        .header(jwtConfig.getHeader(), jwtConfig.getPrefix()+" "+token)
		        .retrieve()
		        .bodyToMono(DanhSachTongTienThangDTO.class)
		        .block();
		
		return a;
	}

	public DanhSachTongTienThangDTO getFallBackTongTienThang(String token) {
		return null;
	}
}