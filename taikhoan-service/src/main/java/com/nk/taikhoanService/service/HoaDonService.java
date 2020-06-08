package com.nk.taikhoanService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.nk.taikhoanService.security.JwtTokenAuthenticationFilter;

@Service
public class HoaDonService {


	@Autowired
	private WebClient webClient;
	
	
	@HystrixCommand(fallbackMethod = "getFallBackTongTienByIdTaiKhoan",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")		
			}, threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize",value = "20")
			})
	public int getTongTienByIdTaiKhoan(int id) {
		return webClient.get()
        .uri("/hoaDon/admin/taiKhoan/tien/"+id)
        .header("Authorization", "Bearer "+" "+JwtTokenAuthenticationFilter.token)

        .retrieve()
        /*.onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus),
                clientResponse -> Mono.empty())*/
        .bodyToMono(Integer.class)
        .block();
			}
	
	public int getFallBackTongTienByIdTaiKhoan(int id) {
		return 0;
			}
	
}
