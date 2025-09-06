package com.matcarv.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * Configuration class for OpenAPI documentation.
 * 
 * @author Weslley Matos
 */
@Configuration
public class OpenApiConfig {
    
    /**
	 * Spring OpenAPI bean.
	 * 
	 * @return OpenAPI
	 */
	@Bean
	OpenAPI springOpenAPI() {
		return new OpenAPI().info(
			  new Info()
			  	.title("MatCarv - Mockito Test API")
			  	.description("MatCarv Mockito Test API - Uma Solução MatCarv")
			  	.version("1.0.0")
			  	.license(new License().name("Weslley Matos de Carvalho")
	    		.url("https://matcarv.com")));
	 }
}
