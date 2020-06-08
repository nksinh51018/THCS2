package com.nk.config;


import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.nk.service.JwtConfig;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@Configuration
@EnableWebMvc
@EnableCircuitBreaker
@ComponentScan(basePackages = "com.nk")
public class ApplicationContextConfig {
	
	@Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
 
        return viewResolver;
    }
	
	@Bean
	public JwtConfig getJwtConfig() {
		return new JwtConfig();
	}
	
	@Bean
	@LoadBalanced
	public WebClient getBuilder(){
		
		HttpClient httpClient = HttpClient.create()
	            .tcpConfiguration(client ->
	                    client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 20000)
	                    .doOnConnected(conn -> conn
	                            .addHandlerLast(new ReadTimeoutHandler(100))
	                            .addHandlerLast(new WriteTimeoutHandler(10))));
	     
	    ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);     
	 
	    return WebClient.builder()
	            .clientConnector(connector)
	            .baseUrl("http://localhost:8762/")
	            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	            .build();
	}
	
}
