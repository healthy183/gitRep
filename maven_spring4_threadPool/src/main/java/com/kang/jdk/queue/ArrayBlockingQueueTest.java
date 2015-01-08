package com.kang.jdk.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.kang.jdk.thread.ThreadPoolTest;

public class ArrayBlockingQueueTest {
		
	public static void main(String[] args) {
		
		
		BlockingQueue queue = new ArrayBlockingQueue(4);
		
		 ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 6, 1, TimeUnit.DAYS, queue);  
		
		 //maximumPoolSize为3的时候，由于队列容量饱满，maximumPoolSize线程量亦爆满，故报RejectedExecutionException异常
		 //executor = new ThreadPoolExecutor(2, 3, 1, TimeUnit.DAYS, queue);  
		 
		 for (int i = 0; i < 10; i++) {     
             executor.execute(new Thread(new ThreadPoolTest(), "TestThread".concat(" "+i)));     
             int threadSize = queue.size();  
             System.out.println("线程队列大小为-->"+threadSize);  
         }     
         executor.shutdown();  
		 
	}
	

}
