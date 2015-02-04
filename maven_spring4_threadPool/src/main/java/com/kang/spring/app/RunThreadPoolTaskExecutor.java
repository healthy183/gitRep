package com.kang.spring.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.kang.spring.appConfig.AppConfig;
import com.kang.spring.thread.PrintRunnable;
import com.kang.spring.thread.PrintThread;

public class RunThreadPoolTaskExecutor {

	
	public static void main(String[] args) {
		
		
		ApplicationContext tx  = new AnnotationConfigApplicationContext(AppConfig.class);

		ThreadPoolTaskExecutor taskExecutor =  tx.getBean(ThreadPoolTaskExecutor.class);
		
		PrintRunnable printThread = tx.getBean(PrintRunnable.class);
		printThread.setName("first Thread");
		taskExecutor.execute(printThread);
		
		PrintRunnable printThread2 = tx.getBean(PrintRunnable.class);
		printThread2.setName("second Thread");
		taskExecutor.execute(printThread2);
		
		PrintRunnable printThread3 = tx.getBean(PrintRunnable.class);
		printThread3.setName("third Thread");
		taskExecutor.execute(printThread3);
		
		while (true) {
			
			int count = taskExecutor.getActiveCount();
			System.out.println("active thread is " + count );
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			 if(count==0){  
                 taskExecutor.shutdown();  
                 break;  
         }
			
			
		}
		
	}
}
