package com.kang.spring.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.kang.spring.appConfig.AppConfig;
import com.kang.spring.thread.PrintThread;

public class RunPrintThread {

	
	public static void main(String[] args) {
		
		ApplicationContext tx 
				= new AnnotationConfigApplicationContext(AppConfig.class);
		
		PrintThread printThread = tx.getBean(PrintThread.class);
		printThread.setName("first Thread");
		
		PrintThread  printThread2 = tx.getBean(PrintThread.class);
		printThread2.setName("second Thread");
		
		PrintThread  printThread3 = tx.getBean(PrintThread.class);
		printThread3.setName("third Thread");
		
		//调用线程的run(), 不会开始一个新线程
		printThread.run();
		printThread2.run();
		printThread3.run();
		
		//调用线程的start()，开始一个新线程
		/*printThread.start();
		printThread2.start();
		printThread3.start();*/
		
	  /**
		 first Thread is sleeping！
		 third Thread is sleeping！
		 second Thread is sleeping！
		 second Thread have waked up！
		 first Thread have waked up！
		 third Thread have waked up！*/
		
		
	
	}
	
	
	
}
