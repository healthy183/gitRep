package com.kang.jdk.thread;

import java.util.concurrent.ExecutorService;

public class ThreadFactory {

	public ThreadFactory() {
		// TODO Auto-generated constructor stub
	}

		
	public static void joinThread(ExecutorService  pool){
		
		SimpleThread simpleThread1 = new SimpleThread();
		SimpleThread simpleThread2 = new SimpleThread();
		SimpleThread simpleThread3 = new SimpleThread();
		SimpleThread simpleThread4 = new SimpleThread();
		SimpleThread simpleThread5 = new SimpleThread();
		
		//将线程放入池中进行执行
		pool.execute(simpleThread1);
        pool.execute(simpleThread2);
        pool.execute(simpleThread3);
        pool.execute(simpleThread4);
        pool.execute(simpleThread5);
		
	}
	
	
}
