package com.nk.taikhoanService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableHystrixDashboard
public class TaikhoanServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaikhoanServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public WebClient getBuilder(){
		
		HttpClient httpClient = HttpClient.create()
	            .tcpConfiguration(client ->
	                    client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
	                    .doOnConnected(conn -> conn
	                            .addHandlerLast(new ReadTimeoutHandler(10))
	                            .addHandlerLast(new WriteTimeoutHandler(10))));
	     
	    ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);     
	    return WebClient.builder()
	            .baseUrl("http://localhost:8762")
	            .clientConnector(connector)
	            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	            .build();
	}
	
}
