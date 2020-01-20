package com.example.wdfrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.wdfrest.*", "com.example.common.*"})
public class WdfRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(WdfRestApplication.class, args);
    }

}
