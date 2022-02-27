package com.muni.test.testing;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrix
@EnableCircuitBreaker
@EnableHystrixDashboard
public class CallingServiceApp {

	@Value("${my.env.name}")
	private static String prop;
	
	public static void main(String[] args) {
		System.out.println("---------prop value------- "+ prop);
		SpringApplication.run(CallingServiceApp.class, args);
	}

}
