package com.nk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.nk.dto.DanhSachSanPhamAdmin;
import com.nk.dto.DanhSachSanPhamDTO;
import com.nk.dto.DanhSachTimKiemDTO;
import com.nk.dto.SanPhamDTO;
import com.nk.dto.TimKiemDTO;

import reactor.core.publisher.Mono;

@Service
public class SanPhamSerivce {

	@Autowired
	private WebClient webClient;
	
	@Autowired
	private JwtConfig jwtConfig;
	
	@HystrixCommand(fallbackMethod = "getFallBackSanPhamHot",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")		
			}, threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize",value = "20")
			})
	public DanhSachSanPhamDTO getSanPhamHot() {
		
		 return webClient.get()
		        .uri("/sanPham/user/sanPham/hot")
		        .retrieve()
		        /*.onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus),
		                clientResponse -> Mono.empty())*/
		        .bodyToMono(DanhSachSanPhamDTO.class)
		        .block();
	}
	
	public DanhSachSanPhamDTO getFallBackSanPhamHot() {
		return new DanhSachSanPhamDTO();
	}
	
	@HystrixCommand(fallbackMethod = "getFallBackAllSanPham",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")		
			}, threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize",value = "20")
			})
	public DanhSachSanPhamDTO getAllSanPham(int n) {
		
		 return webClient.get()
		        .uri("/sanPham/user/sanPham/all/"+n)
		        .retrieve()
		        /*.onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus),
		                clientResponse -> Mono.empty())*/
		        .bodyToMono(DanhSachSanPhamDTO.class)
		        .block();
	}
	
	public DanhSachSanPhamDTO getFallBackAllSanPham(int n) {
		
		 return new DanhSachSanPhamDTO();
	}
	

	@HystrixCommand(fallbackMethod = "getFallBackAllSanPham",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")		
			}, threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize",value = "20")
			})
	public DanhSachTimKiemDTO getAllSanPhamLoai(int n,String tenLoai,TimKiemDTO timKiemDTO) {
		
		 return webClient.post()
		        .uri("/sanPham/user/sanPham/loai/"+tenLoai+"/timKiem/"+n)
		        .body(Mono.just(timKiemDTO),TimKiemDTO.class)
		        .retrieve()
		        /*.onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus),
		                clientResponse -> Mono.empty())*/
		        .bodyToMono(DanhSachTimKiemDTO.class)
		        .block();
	}
	
	public DanhSachSanPhamDTO getFallBackAllSanPhamLoai(int n,String tenLoai) {
		
		 return new DanhSachSanPhamDTO();
	}
	
	@HystrixCommand(fallbackMethod = "getFallBackSanPhamById",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")		
			}, threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize",value = "20")
			})
	public SanPhamDTO getSanPhamById(int id) {
		
		 return webClient.get()
		        .uri("/sanPham/user/sanPham/"+id)
		        .retrieve()
		        /*.onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus),
		                clientResponse -> Mono.empty())*/
		        .bodyToMono(SanPhamDTO.class)
		        .block();
	}
	
	public SanPhamDTO getFallBackSanPhamById(String tenLoai) {
		
		 return new SanPhamDTO();
	}
	
	@HystrixCommand(fallbackMethod = "getFallBackSanPhamByIdAdmin",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")		
			}, threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize",value = "20")
			})
	public SanPhamDTO getSanPhamByIdAdmin(int id,String token) {
		
		 return webClient.get()
				
		        .uri("/sanPham/admin/sanPham/"+id)
		        .header(jwtConfig.getHeader(), jwtConfig.getPrefix()+" "+token)
		        .retrieve()
		        /*.onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus),
		                clientResponse -> Mono.empty())*/
		        .bodyToMono(SanPhamDTO.class)
		        .block();
	}
	
	public SanPhamDTO getFallBackSanPhamByIdAdmin(int id,String token) {
		
		 return new SanPhamDTO();
	}
	
	@HystrixCommand(fallbackMethod = "findFallBackSanPhamByTen",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")		
			}, threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize",value = "20")
			})
	public DanhSachSanPhamDTO findSanPhamByTen(String tenSanPham) {
		
		 return webClient.get()
		        .uri("/sanPham/user/sanPham/timKiem/"+tenSanPham+"/0")
		        .retrieve()
		        /*.onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus),
		                clientResponse -> Mono.empty())*/
		        .bodyToMono(DanhSachSanPhamDTO.class)
		        .block();
	}
	
	public DanhSachSanPhamDTO findFallBackSanPhamByTen(String tenSanPham) {
		
		 return new DanhSachSanPhamDTO();
	}
	
	@HystrixCommand(fallbackMethod = "findFallBackSanPham",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")		
			}, threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize",value = "20")
			})
	public DanhSachSanPhamAdmin findSanPham(String token) {
		
		
		
		 return webClient.get()
		        .uri("/sanPham/admin/sanPham")
		        .header(jwtConfig.getHeader(), jwtConfig.getPrefix()+" "+token)
		        .retrieve()
		        /*.onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus),
		                clientResponse -> Mono.empty())*/
		        .bodyToMono(DanhSachSanPhamAdmin.class)
		        .block();
	}
	
	public DanhSachSanPhamAdmin findFallBackSanPham(String tenSanPham) {
		
		 return new DanhSachSanPhamAdmin();
	}
	
	
	
	
}
