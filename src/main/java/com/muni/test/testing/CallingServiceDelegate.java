package com.muni.test.testing;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class CallingServiceDelegate {

	@HystrixCommand(fallbackMethod = "fallbackMethod_Fallback")
	public String getMethodImpl(int id, RestTemplate restAPI2) {
		System.out.println("------URL-----" + serviceUrl());
		String response = restAPI2.exchange(this.serviceUrl() + "/called-service/kafka/{" + id + "}", HttpMethod.GET, null,
				new ParameterizedTypeReference<String>() {
				}, id).getBody();

		/*
		 * String response = restAPI2.exchange("http://localhost:9090/called-service/{"
		 * + id + "}", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
		 * }, id).getBody();
		 */

		return MessageFormat.format("test pass for ID: {0} and here is what you got from srvc invoked: {1}", id,
				response);
	}

	@SuppressWarnings("unused")
	private String fallbackMethod_Fallback(int id, RestTemplate restAPI2) {
		return MessageFormat.format("welcome to circuit breaker..! for {0}", id);
	}

	@Autowired
	private DiscoveryClient discoveryClient;

	private String serviceUrl() {
		List<ServiceInstance> list = discoveryClient.getInstances("calledservice");
		if (list != null && list.size() > 0) {
			return list.get(0).getUri().toString();
		}
		return null;
	}

	// Annotation required to listen the message from kafka server
	@KafkaListener(topics = "FIRST_TOPIC_ON_KAFKA", id = "id")
	public void publish(String message) {
		System.out.println("You have a new message--------------: " + message);
	}

}
