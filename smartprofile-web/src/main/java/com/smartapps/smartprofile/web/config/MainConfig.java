package com.smartapps.smartprofile.web.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class MainConfig {

	private final Environment env;
	
	@Autowired
	public MainConfig(Environment env) {
		this.env = env;
	}
	
	@PostConstruct
	public void init() {
		log.info("Load MainConfig ***");
		String appName = env.getProperty("spring.application.name");
		String appPort = env.getProperty("server.port");
		log.info("App running @: " + appName + ":" + appPort);
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	/* This is required for Loadbalancer(lb:) to work */
    @Bean
    WebClient client() {
        return WebClient.builder()
            .build();
    }

	
}
