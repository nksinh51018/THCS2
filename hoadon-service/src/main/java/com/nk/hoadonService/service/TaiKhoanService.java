package com.nk.hoadonService.service;

import java.sql.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.nk.hoadonService.dto.ChiTietSanPhamDTO;
import com.nk.hoadonService.security.JwtConfig;
import com.nk.hoadonService.security.JwtTokenAuthenticationFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import reactor.core.publisher.Mono;

@Service
public class TaiKhoanService {

	@Autowired
	private WebClient webClient;
	
	private boolean kt = false;
	
	
	@HystrixCommand(fallbackMethod = "getFallBackIdTaiKhoanByTenDangNhap",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")		
			}, threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize",value = "20")
			})
	public int getIdTaiKhoanByTenDangNhap(Authentication auth) {
		JwtConfig jwtConfig = JwtTokenAuthenticationFilter.jwtConfig;
		//System.out.println(jwtConfig.getSecret());
		Long now = System.currentTimeMillis();
		String token = JwtTokenAuthenticationFilter.token;
		
		int id = webClient.get()
		        .uri("/taiKhoan/user/id")
		        .header(jwtConfig.getHeader(), jwtConfig.getPrefix()+" "+token)
		        .exchange()
		        .doOnSuccess(clientResponse -> doSuccess(clientResponse))
		        .flatMap(clientResponse -> clientResponse.bodyToMono(Integer.class)).block();
		
//		int id = webClient.get()
//				.uri("/taiKhoan/user/id")
//				.retrieve()
//				/*.onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus),
//                clientResponse -> Mono.empty())*/
//				.bodyToMono(Integer.class)
//				.block();
		return id;
	}
	
	private void doSuccess(ClientResponse clientResponse) {
		// TODO Auto-generated method stub
		System.out.println(clientResponse.statusCode().value());
		if(clientResponse.statusCode().value() !=200) {
			kt = true;
		}
		else {
			kt = false;
		}
	}
	
	public int getFallBackIdTaiKhoanByTenDangNhap(Authentication auth) {
		return 0;
	}
}
