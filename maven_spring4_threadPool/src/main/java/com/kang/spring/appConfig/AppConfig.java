package com.kang.spring.appConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@ComponentScan("com.kang.spring.thread")
public class AppConfig {

	@Bean
	public ThreadPoolTaskExecutor taskExecutor(){
		
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		
		executor.setCorePoolSize(5);
		
		executor.setMaxPoolSize(10);
		
		executor.setWaitForTasksToCompleteOnShutdown(Boolean.TRUE);
		
		return executor;
		
	}
	
	public SyncTaskExecutor syncTaskExecutor(){
		
		return null;
	}
	
	
	
}
