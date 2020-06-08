package com.nk.hinhanhService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

import com.nk.hinhanhService.service.FileStorageProperties;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableConfigurationProperties({
	FileStorageProperties.class
})
public class HinhAnhServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HinhAnhServiceApplication.class, args);
	}

}
