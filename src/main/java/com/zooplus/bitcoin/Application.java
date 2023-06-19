package com.zooplus.bitcoin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ServletComponentScan(value="com.zooplus.bitcoin.security")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}