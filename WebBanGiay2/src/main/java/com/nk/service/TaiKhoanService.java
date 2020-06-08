package com.nk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.nk.controller.DanhSachKhachHangAdminController;
import com.nk.dto.DanhSachLoaiSanPhamDTO;
import com.nk.dto.DanhSachSanPhamDTO;
import com.nk.dto.DanhSachTaiKhoanDTO;
import com.nk.dto.SanPhamDTO;
import com.nk.dto.TaiKhoanDTO;

import reactor.core.publisher.Mono;

@Service
public class TaiKhoanService {

	@Autowired
	private WebClient webClient;
	
	@Autowired
	private JwtConfig jwtConfig;
	
	private boolean kt = false;
		
	@HystrixCommand(fallbackMethod = "FallBackDangNhap",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")		
			}, threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize",value = "20")
			})
	public String DangNhap(String token) {
		String ten = webClient.get()
		        .uri("/taiKhoan/user/ten")
		        .header(jwtConfig.getHeader(), jwtConfig.getPrefix()+" "+token)
		        .exchange()
		        .doOnSuccess(clientResponse -> doSuccess(clientResponse))
		        .flatMap(clientResponse -> clientResponse.bodyToMono(String.class)).block();
		System.out.println(ten);
		System.out.println(kt);
		if(kt) {
			ten="";
		}
		System.out.println(ten);
		return ten;
	}

	private void doSuccess(ClientResponse clientResponse) {
		// TODO Auto-generated method stub
		System.out.println(clientResponse.statusCode().value());
		if(clientResponse.statusCode().value() !=200) {
			kt = true;
		}
		else {
			kt = false;
		}
	}

	public String FallBackDangNhap() {
		return "";
	}
	
	@HystrixCommand(fallbackMethod = "getFallBackThongTinTaiKhoan",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")		
			}, threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize",value = "20")
			})
	public TaiKhoanDTO getThongTinTaiKhoan(String token) {
		return webClient.get()
		        .uri("/taiKhoan/user/taiKhoan")
		        .header(jwtConfig.getHeader(), jwtConfig.getPrefix()+" "+token)
		        .retrieve()
		        /*.onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus),
		                clientResponse -> Mono.empty())*/
		        .bodyToMono(TaiKhoanDTO.class)
		        .block();
	}
	
	public TaiKhoanDTO getFallBackThongTinTaiKhoan(String token) {
		return new TaiKhoanDTO();
	}
	
	@HystrixCommand(fallbackMethod = "getFallBackTenDangNhapAdmin",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")		
			}, threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize",value = "20")
			})
	public String getTenDangNhapAdmin(String token) {
		String ten = webClient.get()
		        .uri("/taiKhoan/admin/ten")
		        .header(jwtConfig.getHeader(), jwtConfig.getPrefix()+" "+token)
		        .exchange()
		        .doOnSuccess(clientResponse -> doSuccess(clientResponse))
		        .flatMap(clientResponse -> clientResponse.bodyToMono(String.class)).block();
		System.out.println(ten);
		System.out.println(kt);
		if(kt) {
			ten="";
		}
		System.out.println(ten);
		return ten;
	}
	
	public String getFallBackTenDangNhapAdmin(String token) {
		return "";
	}
	
	@HystrixCommand(fallbackMethod = "getFallBackAllTaiKhoan",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")		
			}, threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize",value = "20")
			})
	public DanhSachTaiKhoanDTO getAllTaiKhoan(String token) {
		return webClient.get()
		        .uri("/taiKhoan/admin/taiKhoan/0")
		        .header(jwtConfig.getHeader(), jwtConfig.getPrefix()+" "+token)
		        .exchange()
		        .doOnSuccess(clientResponse -> doSuccess(clientResponse))
		        .flatMap(clientResponse -> clientResponse.bodyToMono(DanhSachTaiKhoanDTO.class)).block();
	}
	
	public DanhSachTaiKhoanDTO getFallBackAllTaiKhoan(String token) {
		return new DanhSachTaiKhoanDTO();
	}
	
	@HystrixCommand(fallbackMethod = "getFallBackTaiKhoanById",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")		
			}, threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize",value = "20")
			})
	public TaiKhoanDTO getTaiKhoanById(String token,int id) {
		return webClient.get()
		        .uri("/taiKhoan/admin/taiKhoan/id/"+id)
		        .header(jwtConfig.getHeader(), jwtConfig.getPrefix()+" "+token)
		        .exchange()
		        .flatMap(clientResponse -> clientResponse.bodyToMono(TaiKhoanDTO.class)).block();
	}
	
	public TaiKhoanDTO getFallBackTaiKhoanById(String token,int id) {
		return new TaiKhoanDTO();
	}
	
	
}
