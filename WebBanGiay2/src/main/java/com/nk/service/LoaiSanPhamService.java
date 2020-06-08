package com.nk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.nk.dto.DanhSachLoaiSanPhamDTO;
import com.nk.dto.LoaiSanPhamDTO;

@Service
public class LoaiSanPhamService {

	@Autowired
	private WebClient webClient;
	
	@HystrixCommand(fallbackMethod = "getFallBackLoaiSanPham",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")		
			}, threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize",value = "20")
			})
	public DanhSachLoaiSanPhamDTO getLoaiSanPham() {
		
		return webClient.get()
		        .uri("sanPham/user/loaiSanPham")
		        .retrieve()
		        .bodyToMono(DanhSachLoaiSanPhamDTO.class)
		        .block();
	}
	
	public DanhSachLoaiSanPhamDTO getFallBackLoaiSanPham() {
		return new DanhSachLoaiSanPhamDTO();
	}
	
}
