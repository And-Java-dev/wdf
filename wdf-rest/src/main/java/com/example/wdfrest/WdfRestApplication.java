package com.example.wdfrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.wdfrest.*", "com.example.common.*"})
@EnableJpaRepositories(basePackages = "com.example.common.repository")
@EntityScan("com.example.common.model")
public class WdfRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(WdfRestApplication.class, args);
    }



}
