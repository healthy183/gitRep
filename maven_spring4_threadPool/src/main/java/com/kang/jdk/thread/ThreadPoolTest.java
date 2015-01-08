package com.kang.jdk.thread;

public class ThreadPoolTest implements Runnable {

	public void run() {
			
		synchronized (this) {
			
			try {
				System.out.println("当前线程名称:"+Thread.currentThread().getName());
				Thread.sleep(3000); //休眠是为了让该线程不至于执行完毕后从线程池里释放 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	

	
}
