package com.at.mul.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;

@EnableAutoConfiguration
public class Main {

	
	public static void main(String[] args) {


		ApplicationContext ctx = SpringApplication.run(
				Main.class, args);
}
}
