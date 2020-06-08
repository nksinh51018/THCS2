package com.nk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.nk.dto.DanhSachMauDTO;
import com.nk.dto.DanhSachSanPhamDTO;

@Service
public class MauService {

	@Autowired
	private WebClient webClient;
	
	@Autowired
	private JwtConfig jwtConfig;
	
	@HystrixCommand(fallbackMethod = "getFallBackAllMau",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")		
			}, threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize",value = "20")
			})
	public DanhSachMauDTO getAllMau() {
		
		 return webClient.get()
		        .uri("/sanPham/user/mau")
		        .retrieve()
		        /*.onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus),
		                clientResponse -> Mono.empty())*/
		        .bodyToMono(DanhSachMauDTO.class)
		        .block();
	}
	
	public DanhSachSanPhamDTO getFallBackSanPhamHot() {
		return new DanhSachSanPhamDTO();
	}
	
}
