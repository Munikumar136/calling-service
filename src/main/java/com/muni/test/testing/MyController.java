package com.muni.test.testing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/test-check")
public class MyController extends RestTemplate {
	
	private static final Logger logger = LoggerFactory.getLogger(MyController.class);

	@Autowired
	private CallingServiceDelegate callingSvcDelegate;
	
	@Value("${my.env.name}")
	private String prop;
	
	@Autowired
	private RestTemplate restAPI;
	
	@GetMapping(value="/{id}", produces="text/plain")
	public String getImpl(@PathVariable int id) {
		logger.info(prop);
		return callingSvcDelegate.getMethodImpl(id, restAPI);
	}
}
