package com.nk.sanphamService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class KhuyenMaiService {

	@Autowired
	private WebClient webClient;
	
	
	@HystrixCommand(fallbackMethod = "getFallBackKhuyenMaiByIdSanPham",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")		
			}, threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize",value = "20")
			})
	public int getKhuyenMaiByIdSanPham(int idSanPham) {
		
		 return webClient.get()
		        .uri("/khuyenMai/user/phanTram/"+idSanPham)
		        .retrieve()
		        /*.onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus),
		                clientResponse -> Mono.empty())*/
		        .bodyToMono(Integer.class)
		        .block();
	}
	
	public int getFallBackKhuyenMaiByIdSanPham(int idSanPham) {
		return 0;
	}
	
	
}
