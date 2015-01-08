package com.kang.spring.thread;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class PrintThread extends Thread {

	@Override
	public void run() {
			
		System.out.println(" "+getName()+" is sleeping！");
		
		 try {
			Thread.sleep(5000);
			
		 
		 } catch (InterruptedException e) {
			e.printStackTrace();
		}
			
		 System.out.println(""+getName()+" have waked up！");
			
	}
	
	
}
