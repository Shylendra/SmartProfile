package com.smartapps.smartprofile.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.smartapps.smartprofile.web.util.SmartProfileWebUtil;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

	@Value("${spring.application.name}")
	private String appName;

	/* OpenAPI Settings */
	public static final String TITLE = SmartProfileWebUtil.CONTEXT_ROOT.replaceAll("/", "");
	public static final String DESCRIPTION = "%s REST API Information";
	public static final String VERSION = "1.0.0";

	@Bean
	public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
		return new OpenAPI()
				.components(new Components())
				.info(new Info()
						.title(TITLE)
						.description(String.format(DESCRIPTION, this.appName))
						.version(VERSION));
	}
	
}
