package com.muni.test.testing;

import java.text.MessageFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class CallingServiceDelegate {

	@HystrixCommand(fallbackMethod = "fallbackMethod_Fallback")
	public String getMethodImpl(int id, RestTemplate restAPI2) {
		String response = restAPI2.exchange("http://localhost:9090/called-service/{" + id + "}", HttpMethod.GET,
				null, new ParameterizedTypeReference<String>() {
				}, id).getBody();
		return MessageFormat.format("test pass for ID: {0} and here is what you got from srvc invoked: {1}", id,
				response);
	}

	@SuppressWarnings("unused")
	private String fallbackMethod_Fallback(int id, RestTemplate restAPI2) {
		return MessageFormat.format("welcome to circuit breaker..! for {0}", id);
	}

}
