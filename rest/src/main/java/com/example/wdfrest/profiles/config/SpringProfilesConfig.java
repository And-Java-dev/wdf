package com.example.wdfrest.profiles.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.example.wdfrest.profiles")
@PropertySource(value = "classpath:application.properties")
public class SpringProfilesConfig {
}
