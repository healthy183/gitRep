package com.kang.jdk.thread;

public class SimpleThread extends Thread {

	
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+" is the running man!");
	}

}
