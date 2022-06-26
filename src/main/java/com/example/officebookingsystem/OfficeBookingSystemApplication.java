package com.example.officebookingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class OfficeBookingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(OfficeBookingSystemApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("api/**").allowedOrigins("http://localhost:3000");
                registry.addMapping("api/**").allowedOrigins("https://3.88.14.239:80");
            }
        };
    }

    }

