package com.example.demo.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Allow CORS for all endpoints
                .allowedOrigins("http://127.0.0.1:5500")  // Allow frontend URL
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Allow HTTP methods
                .allowedHeaders("*");  // Allow all headers
    }
}

