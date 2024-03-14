package com.popularsafi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AppMovilBackendApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AppMovilBackendApplication.class, args);
    }

    public SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder){
        return applicationBuilder.sources(AppMovilBackendApplication.class);
    }
}
