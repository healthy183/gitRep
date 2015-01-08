package com.kang.jdk.queue;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class QueueCategory {
	
	//SynchronousQueue
	public void  testSynchronousQueue(){
		
		//SynchronousQueue只等存储一个任务(A)，当有第二个任务(B)则直接将任务(A)提交给线程，
		//当corePoolSize超出，则线程永远被创建，空闲后回收
		//如果SynchronousQueue有任务存储，并线程量超过maximumPoolSizes，则会报异常
		//故次queue一般要求maximumPoolSizes无限大(无界)
		Queue queue = new SynchronousQueue();
		
		//该queue适用于CachedThreadPool
		ExecutorService executorService =  Executors.newCachedThreadPool();
	}
	
		
	public void testLinkedBlockingQueue(){
		
		//该queue可以无限量将任务加入队列，故使用其线程池的maximumPoolSize将无效
		//该queue适合瞬间大量数据并发访问
		Queue queue =  new LinkedBlockingQueue<Runnable>();
		
		//该queue适用于SingleThreadExecutor
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		//该queue适用于FixedThreadPool
		executorService = Executors.newFixedThreadPool(2);
	}
	
	
	public void testArrayBlockingQueue(){
		
		//队列有限大(2为队列大小)
		//使用其的线程池的maximumPoolSizes也有限大
		//使用大型队列和小型池可以最大限度地降低 CPU 使用率、操作系统资源和上下文切换开销，但是可能导致人工降低吞吐量
		//故性能调优比较复杂
		//
		Queue queue =  new ArrayBlockingQueue<Runnable>(2);
		
	}
	
}
