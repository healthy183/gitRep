package com.kang.jdk.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.kang.jdk.thread.ThreadFactory;

/**创建一个可缓存的线程池。
 
 如果线程池的大小超过了处理任务所需要的线程，那么就会回收部分空闲（60秒不执行任务）的线程，
当任务数增加时，此线程池又可以智能的添加新线程来处理任务。
此线程池不会对线程池大小做限制，线程池大小完全依赖于操作系统（或者说JVM）能够创建的最大线程大小。
*/
public class TestCachedThreadPool {

	public static void main(String[] args) {
		
		/**
		 * ThreadPoolExecutor的完整构造方法
		 * ThreadPoolExecutor(
		 * 		int corePoolSize,int maximumPoolSize,
		 * 	 			long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, 
		 *  						ThreadFactory threadFactory, RejectedExecutionHandler handler)
		 * 
		 * 
		 * corePoolSize表示线程池默认活跃线程数
		 * maximumPoolSize表示最多线程数
		 * 			当任务执行要求的线程数大于corePoolSize，则任务会被加入进Queue，
		 * 			当Queue无法在添加则创建新线程
		 * 			当新创建线程等于maximumPoolSize，则任务将被拒绝执行
		 * 
		 * keepAliveTime表示当任务执行要求的线程数大于corePoolSize时候，创建的线程执行完任务空闲后的寿命
		 * TimeUnit表示keepAliveTime的单位
		 * 
		 * 
		 * threadFactory 执行创建新线程的工厂类
		 * handler 由于超出线程范围和队伍容量而使用执行被阻塞时所使用的处理程序
		 * */
		
		//==============================================================
		
		/**而ThreadPoolExecutor下的CachedThreadPool创造方法如下
		 *  public static ExecutorService newCachedThreadPool() {
		        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
		                                      60L, TimeUnit.SECONDS,
		                                      new SynchronousQueue<Runnable>());
		 }*/
		
		ExecutorService  pool =	Executors.newCachedThreadPool();
		
		ThreadFactory.joinThread(pool);
        
        pool.shutdown();
		
	}
}
