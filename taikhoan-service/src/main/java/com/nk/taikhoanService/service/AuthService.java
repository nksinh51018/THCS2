package com.nk.taikhoanService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ClientResponse.Headers;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Http.Header;

@Service
public class AuthService {

	
	@Autowired
	private WebClient webClient;
	
	private String token;
	
	@HystrixCommand(fallbackMethod = "getFallBackToken",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")		
			}, threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize",value = "20")
			})
	public String getToken(String tenDangNhap,String matKhau) {
		String body = "{\r\n" + 
				"    \"username\": \""+tenDangNhap+"\",\r\n" + 
				"    \"password\": \""+matKhau+"\"\r\n" + 
				"}";
		webClient.post()
        .uri("/auth")
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(body))
        .exchange()
        .doOnSuccess(clientResponse -> doSuccess(clientResponse))
        .flatMap(clientResponse -> clientResponse.bodyToMono(String.class)).block();
		return token;	}
	
	private void doSuccess(ClientResponse clientResponse) {
		// TODO Auto-generated method stub
		Headers header = clientResponse.headers();
		List<String> strings =header.header("Authorization");
		for (String string : strings) {
			token = string;
		}
	}

	public String getFallBackToken(int idSanPham) {
		return "";
	}
}
