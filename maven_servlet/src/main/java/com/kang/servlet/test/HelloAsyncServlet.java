package com.kang.servlet.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"/helloAsyncServlet"},asyncSupported=true)
public class HelloAsyncServlet extends HttpServlet {

	
	@Override
	protected void service(HttpServletRequest request, 
			HttpServletResponse response)
					throws ServletException, IOException {
		
		request.setCharacterEncoding("GBK");
		
		response.setContentType("text/html;charset=GBK");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
        out.println("<body>");
        out.println("====页面加载开始====<hr />");
        AsyncContext actx = request.startAsync();
        actx.setTimeout(30 * 3000);
       actx.start(new MyThread(actx));
        
       // new Thread(new MyThread(actx)).start();
        out.println("====页面加载结束====<hr />");
        out.println("</body>");
        out.println("</html>");
        out.flush();
		//super.service(arg0, arg1);
	}
	
}


class MyThread implements Runnable{
	
	private AsyncContext actx;
	
	public MyThread(AsyncContext actx){
		this.actx = actx;
	}

	public void run() {
		
		try {
			Thread.sleep(5*1000);//5秒
			
		
			actx.dispatch("/testHelloAsyncServlet.jsp");
			// or  add code :	actx.complete();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}



