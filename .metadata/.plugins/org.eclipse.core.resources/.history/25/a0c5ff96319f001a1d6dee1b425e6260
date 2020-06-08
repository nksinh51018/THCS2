package com.nk.sanphamService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.nk.sanphamService.DTO.DanhSachHinhAnhDTO;
import com.nk.sanphamService.DTO.HinhAnhDTO;
import com.nk.sanphamService.entity.Mau;
import com.nk.sanphamService.security.JwtTokenAuthenticationFilter;

import reactor.core.publisher.Mono;

@Service
public class HinhAnhService {

	@Autowired
	private WebClient webClient;
	
	@Autowired
	private MauService mauService;
	
	
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
	public DanhSachHinhAnhDTO getHinhAnhByIdSanPham(int idSanPham) {
		
		 return webClient.get()
		        .uri("/hinhAnh/sanPham/"+idSanPham)
		        .retrieve()
		        /*.onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus),
		                clientResponse -> Mono.empty())*/
		        .bodyToMono(DanhSachHinhAnhDTO.class)
		        .block();
	}
	
	public DanhSachHinhAnhDTO getFallBackKhuyenMaiByIdSanPham(int idSanPham) {
		return new DanhSachHinhAnhDTO();
	}
	
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
	
	@HystrixCommand(fallbackMethod = "suaFallBackHinhAnh",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")		
			}, threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize",value = "20")
			})
	public boolean suaHinhAnh(List<HinhAnhDTO> hinhAnhDTOs,int id) {
		System.out.println(hinhAnhDTOs.size());
		for (HinhAnhDTO hinhAnhDTO : hinhAnhDTOs) {
			if(hinhAnhDTO.getIdMau() == 0) {
				Mau mau = mauService.getMauByTenMau(hinhAnhDTO.getTenMau());
				hinhAnhDTO.setIdMau(mau.getId());
				System.out.println(mau.getId());
			}
			hinhAnhDTO.setIdSanPham(id);
			webClient.put()
	        .uri("/hinhAnh/admin/sanPham")
	        .header("Authorization", "Bearer "+" "+JwtTokenAuthenticationFilter.token)
	        .body(Mono.just(hinhAnhDTO),HinhAnhDTO.class)
	        .retrieve()
	        /*.onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus),
	                clientResponse -> Mono.empty())*/
	        .bodyToMono(Boolean.class)
	        .block();
		}
		
		 return true;
	}
	
	public boolean suaFallBackHinhAnh(List<HinhAnhDTO> hinhAnhDTOs,int id) {
		return false;
	}
	
	@HystrixCommand(fallbackMethod = "themFallBackHinhAnh",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")		
			}, threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize",value = "20")
			})
	public boolean themHinhAnh(List<HinhAnhDTO> hinhAnhDTOs,int id) {
		System.out.println(hinhAnhDTOs.size());
		for (HinhAnhDTO hinhAnhDTO : hinhAnhDTOs) {
			if(hinhAnhDTO.getIdMau() == 0) {
				Mau mau = mauService.getMauByTenMau(hinhAnhDTO.getTenMau());
				hinhAnhDTO.setIdMau(mau.getId());
				System.out.println(mau.getId());
			}
			System.out.println(hinhAnhDTO.getIdMau());
			hinhAnhDTO.setIdSanPham(id);
			webClient.post()
	        	.uri("/hinhAnh/admin/sanPham")
	        	.header("Authorization", "Bearer "+" "+JwtTokenAuthenticationFilter.token)
	        	.body(Mono.just(hinhAnhDTO),HinhAnhDTO.class)
	        	.retrieve()
	        	.bodyToMono(Boolean.class)
	        	.block();
		}
		 return true;
	}
	
	public boolean themFallBackHinhAnh(List<HinhAnhDTO> hinhAnhDTOs,int id) {
		return false;
	}
	
	@HystrixCommand(fallbackMethod = "xoaFallBackHinhAnh",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")		
			}, threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize",value = "20")
			})
	public boolean xoaHinhAnh(int idSanPham) {
		
		return  webClient.delete()
		        .uri("/hinhAnh/admin/sanPham/"+idSanPham)
		        .header("Authorization", "Bearer "+" "+JwtTokenAuthenticationFilter.token)
		        .retrieve()
		        .bodyToMono(Boolean.class)
		        .block();
	}
	
	public boolean xoaFallBackHinhAnh(int idSanPham) {
		return false;
	}
	
}
