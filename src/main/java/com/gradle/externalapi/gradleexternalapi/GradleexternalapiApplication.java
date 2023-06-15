package com.gradle.externalapi.gradleexternalapi;

import java.time.Duration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCaching
public class GradleexternalapiApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(GradleexternalapiApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {	    
		HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();    
	    Duration timeout = Duration.ofSeconds(10); 
	    builder.setConnectTimeout(timeout);
	    builder.setReadTimeout(timeout);
        requestFactory.setConnectTimeout((int) timeout.toMillis());    
        builder.requestFactory(() -> requestFactory);
	    return builder.build();
	}
}
