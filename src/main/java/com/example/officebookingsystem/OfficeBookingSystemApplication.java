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
                registry.addMapping("/**").allowedOrigins("http://localhost:3000");
<<<<<<< HEAD
=======
                registry.addMapping("/**").allowedOrigins("https://kanbo-web.netlify.app");
>>>>>>> 5c256700a83084414066c4c55e8050cc7b42a571
            }
        };
    }

    }

