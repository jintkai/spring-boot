package com.jon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@ComponentScan( basePackages = "com.jon")
public class Config {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Config.class, args);
    }
}
