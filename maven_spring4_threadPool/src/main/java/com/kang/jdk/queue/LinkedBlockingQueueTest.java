package com.kang.jdk.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.kang.jdk.thread.ThreadPoolTest;

	


public class LinkedBlockingQueueTest {
	
	public static void main(String[] args) {
		
		BlockingQueue<Runnable>  queue = new LinkedBlockingQueue<Runnable>();
	
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3, 300, TimeUnit.DAYS,queue);
			
		for(int i = 0;i<10;i++){
			executor.execute(new Thread(new ThreadPoolTest(),"test thread".concat(""+i)));
			int size = queue.size();
			System.out.println("queue队列量为:"+size);
		}
		
		executor.shutdown();
	
	}
	
}
