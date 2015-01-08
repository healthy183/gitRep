package com.kang.jdk.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.kang.jdk.thread.SimpleThread;
import com.kang.jdk.thread.ThreadFactory;

/**
 * 创建一个单线程的线程池。这个线程池只有一个线程在工作，也就是相当于单线程串行执行所有任务。
 * 如果这个唯一的线程因为异常结束，那么会有一个新的线程来替代它。
 * 此线程池保证所有任务的执行顺序按照任务的提交顺序执行。
 */
public class TestSingleThreadExecutor {
	
	public static void main(String[] args) {
		
		ExecutorService  pool =	Executors.newSingleThreadExecutor();
		
		ThreadFactory.joinThread(pool);
        
        pool.shutdown();
	}
}
