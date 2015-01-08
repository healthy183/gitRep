package com.kang.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.kang")
@EnableAutoConfiguration
public class ApplicationTest {
	
	public static void main(String[] args) {
		
		SpringApplication.run(ApplicationTest.class);
	}
}
