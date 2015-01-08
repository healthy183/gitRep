package com.kang.spring.thread;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class PrintRunnable implements Runnable {

	
	private String name;  
	  
    public void setName(String name) {  
            this.name = name;  
    } 
    
	public void run() {
		
		 System.out.println(name + " is running.");  
         try{  
                 Thread.sleep(5000);  
         }catch(InterruptedException e){  
                 e.printStackTrace();  
         }  
         System.out.println(name + " is running again."); 
		
		
	}
	
}
