package com.nk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.nk.dto.DanhSachKhuyenMaiDTO;
import com.nk.dto.KhuyenMaiDTO;

@Service
public class KhuyenMaiService {

	@Autowired
	private WebClient webClient;
	
	@Autowired
	private JwtConfig jwtConfig;
	
	@HystrixCommand(fallbackMethod = "getFallBackAllKhuyenMai",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")		
			}, threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize",value = "20")
			})
	public DanhSachKhuyenMaiDTO getAllKhuyenMai(String token) {
		
		return webClient.get()
		        .uri("/khuyenMai/admin/khuyenMai/0")
		        .header(jwtConfig.getHeader(), jwtConfig.getPrefix()+" "+token)
		        .retrieve()
		        .bodyToMono(DanhSachKhuyenMaiDTO.class)
		        .block();
	}
	
	public DanhSachKhuyenMaiDTO getFallBackAllKhuyenMai(String token) {
		return new DanhSachKhuyenMaiDTO();
	}
	
	@HystrixCommand(fallbackMethod = "getFallBackKhuyenMaiById",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")		
			}, threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize",value = "20")
			})
	public KhuyenMaiDTO getKhuyenMaiById(String token,int id) {
		
		return webClient.get()
		        .uri("/khuyenMai/admin/khuyenMai/id/"+id)
		        .header(jwtConfig.getHeader(), jwtConfig.getPrefix()+" "+token)
		        .retrieve()
		        .bodyToMono(KhuyenMaiDTO.class)
		        .block();
	}
	
	public KhuyenMaiDTO getFallBackKhuyenMaiById(String token,int id) {
		return null;
	}
	
}
