package com.kang.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.kang")
//@PropertySource
@SpringBootApplication//@Configuration @EnableAutoConfiguration
//@SpringApplicationConfiguration(classes = MainConfig.class)
public class ApplicationTest {

	
	/*private static final EntityManagerFactory INSTANCE =
            Persistence.createEntityManagerFactory("transactions-optional");*/
	
	public static void main(String[] args) {
		
		SpringApplication.run(ApplicationTest.class);
	}
}
