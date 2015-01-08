package com.kang.jdk.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.kang.jdk.thread.ThreadFactory;

/**
        创建固定大小的线程池。
	每次提交一个任务就创建一个线程，直到线程达到线程池的最大大小。
	线程池的大小一旦达到最大值就会保持不变，如果某个线程因为执行异常而结束，那么线程池会补充一个新线程。
*/
public class TestFixedThreadPool {
	
	public static void main(String[] args) {
	
		ExecutorService  pool =	Executors.newFixedThreadPool(2);
		
		ThreadFactory.joinThread(pool);
        
        pool.shutdown();
		
		
	}
	
}
