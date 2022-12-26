package com.smartapps.smartprofile.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableCaching
@SpringBootApplication
@EnableEurekaClient
public class SmartprofileApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartprofileApplication.class, args);
	}

}
