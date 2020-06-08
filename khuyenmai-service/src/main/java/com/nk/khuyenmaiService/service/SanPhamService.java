package com.nk.khuyenmaiService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.nk.khuyenmaiService.dto.SanPhamDTO;

@Service
public class SanPhamService {

	@Autowired
	private WebClient webClient;
	
	
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
	public SanPhamDTO getSanPhamById(int idSanPham) {
		
		 return webClient.get()
		        .uri("/sanPham/user/sanPham/"+idSanPham)
		        .retrieve()
		        /*.onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus),
		                clientResponse -> Mono.empty())*/
		        .bodyToMono(SanPhamDTO.class)
		        .block();
	}
	
	public SanPhamDTO getFallBackSanPhamById(int idSanPham) {
		return null;
	}
	
	
}
