package com.website.sunbpm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SunbpmApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(SunbpmApplication.class, args);
		System.out.println("\n\n\n\nWebsite running with no errors\n\n\n");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// registry.addMapping("/**").allowedOrigins("http://localhost:5182") 
		registry.addMapping("/**").allowedOrigins("http://15.207.163.30:5182") 
				// Replace with your frontend URL
				.allowedMethods("GET", "POST", "PUT", "DELETE").allowCredentials(true);
	}

}
