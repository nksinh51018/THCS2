package com.nk.hoadonService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class HinhAnhService {

	@Autowired
	private WebClient webClient;
	
	
	@HystrixCommand(fallbackMethod = "getFallBackHinhAnhByIdSanPhamAndIdMau",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")		
			}, threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize",value = "20")
			})
	public String getHinhAnhByIdSanPhamAndIdMau(int idSanPham,int idMau) {
		
		 return webClient.get()
		        .uri("/hinhAnh/sanPham/"+idSanPham+"/"+idMau)
		        .retrieve()
		        /*.onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus),
		                clientResponse -> Mono.empty())*/
		        .bodyToMono(String.class)
		        .block();
	}
	
	public String getFallBackHinhAnhByIdSanPhamAndIdMau(int idSanPham,int idMau) {
		return "";
	}
}
