package com.kang.servlet.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"/helloAsyncServletThree"},asyncSupported=true)
public class HelloAsyncServletThree extends HttpServlet {
	
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("text/plain;charset=UTF-8");  
		final PrintWriter writer = resp.getWriter();  
		
		 writer.println("异步之前输出的内容。");
		 
		 writer.flush();  
		 
		 //开始异步调用，获取对应的AsyncContext。
		 final AsyncContext asyncContext = req.startAsync();
		 
		 //设置当前异步调用对应的监听器  
		 asyncContext.addListener(new MyAsyncListener());
		//设置超时时间，当超时之后程序会尝试重新执行异步任务，即我们新起的线程。 
		 asyncContext.setTimeout(10*1000L);  
		//新起线程开始异步调用，start方法不是阻塞式的，它会新起一个线程来启动Runnable接口，之后主程序会继续执行 
		
		 asyncContext.start(new Runnable(){

			public void run() {
				
				 try {
					Thread.sleep(5*1000L);
					writer.println("异步调用之后输出的内容。"); 
					writer.flush(); 
					asyncContext.complete();  
				} catch (InterruptedException e) {
					e.printStackTrace();
				}  
				
				
			}
			
			 
			 
		 });
		 
	     writer.println("可能在异步调用前输出，也可能在异步调用之后输出，因为异步调用会新起一个线程。"); 
	     writer.flush();  
	}
	
	
	
	
	
}

 class MyAsyncListener implements AsyncListener{

	public void onComplete(AsyncEvent event) throws IOException {
		System.out.println("监听异步调用完成……");  
        event.getSuppliedResponse().getWriter().println("异步调用完成……");  
	}

	public void onError(AsyncEvent event) throws IOException {
		 System.out.println("监听异步调用出错……");  
         event.getSuppliedResponse().getWriter().println("异步调用出错……"); 
	}

	public void onStartAsync(AsyncEvent event) throws IOException {
		 System.out.println("监听异步调用开始……");  
         event.getSuppliedResponse().getWriter().println("异步调用开始……");  
	}

	public void onTimeout(AsyncEvent event) throws IOException {
		 System.out.println("监听异步调用超时……");  
         event.getSuppliedResponse().getWriter().println("异步调用超时……");  
	}
	
}

