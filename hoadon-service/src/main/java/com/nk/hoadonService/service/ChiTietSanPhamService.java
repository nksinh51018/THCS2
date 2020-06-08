package com.nk.hoadonService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.nk.hoadonService.dto.ChiTietSanPhamDTO;
import com.nk.hoadonService.security.JwtTokenAuthenticationFilter;

import reactor.core.publisher.Mono;

@Service
public class ChiTietSanPhamService {

	@Autowired
	private WebClient webClient;
	
	
	@HystrixCommand(fallbackMethod = "getFallBackIDChiTietSanPhamByIdSanPhamAndIdMauAndSize",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")		
			}, threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize",value = "20")
			})
	public int getIDChiTietSanPhamByIdSanPhamAndIdMauAndSize(ChiTietSanPhamDTO chiTietSanPhamDTO) {
		
		 return  webClient.post()
			        .uri("/sanPham/user/chiTietSanPham/id")
			        .body(Mono.just(chiTietSanPhamDTO),ChiTietSanPhamDTO.class)
			        .retrieve()
			        /*.onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus),
			                clientResponse -> Mono.empty())*/
			        .bodyToMono(Integer.class)
			        .block();
	}
	
	public int getFallBackIDChiTietSanPhamByIdSanPhamAndIdMauAndSize(ChiTietSanPhamDTO chiTietSanPhamDTO) {
		return 0;
	}
	
	
	@HystrixCommand(fallbackMethod = "getFallBackChiTietSanPhamById",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")		
			}, threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize",value = "20")
			})
	public ChiTietSanPhamDTO getChiTietSanPhamById(int id) {
		
		ChiTietSanPhamDTO chiTietSanPhamDTO=  webClient.get()
			        .uri("/sanPham/user/chiTietSanPham/"+id)
			        .retrieve()
			        /*.onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus),
			                clientResponse -> Mono.empty())*/
			        .bodyToMono(ChiTietSanPhamDTO.class)
			        .block();
		if(chiTietSanPhamDTO == null) {
			return new ChiTietSanPhamDTO();
		}
		return chiTietSanPhamDTO;
	}
	
	public ChiTietSanPhamDTO getFallBackChiTietSanPhamById(int id) {
		return new ChiTietSanPhamDTO();
	}
	
	@HystrixCommand(fallbackMethod = "themFallBackHoaDon",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")		
			}, threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize",value = "20")
			})
	public boolean themHoaDon(ChiTietSanPhamDTO chiTietSanPhamDTO) {
		
		 return  webClient.post()
			        .uri("/sanPham/user/themHoaDon")
			        .body(Mono.just(chiTietSanPhamDTO),ChiTietSanPhamDTO.class)
			        .retrieve()
			        /*.onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus),
			                clientResponse -> Mono.empty())*/
			        .bodyToMono(Boolean.class)
			        .block();
	}
	
	public boolean themFallBackHoaDon(ChiTietSanPhamDTO chiTietSanPhamDTO) {
		return false;
	}
	
	@HystrixCommand(fallbackMethod = "huyFallBackHoaDon",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")		
			}, threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize",value = "20")
			})
	public boolean huyHoaDon(ChiTietSanPhamDTO chiTietSanPhamDTO) {
			return webClient.post()
	        	.uri("/sanPham/admin/hoaDon")
	        	.header("Authorization", "Bearer "+" "+JwtTokenAuthenticationFilter.token)
	        	.body(Mono.just(chiTietSanPhamDTO),ChiTietSanPhamDTO.class)
	        	.retrieve()
	        	.bodyToMono(Boolean.class)
	        	.block();
	}
	
	public boolean huyFallBackHoaDon(ChiTietSanPhamDTO chiTietSanPhamDTO) {
		return false;
	}
	
}
