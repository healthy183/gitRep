package com.kang.myShiroSevlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="helloServlet",urlPatterns={"/HelloServlet"},
initParams = {@WebInitParam(name="name",value="123")})
public class HelloServlet extends HttpServlet {
	
	
	/*value = {},
	 * urlPatterns={},loadOnStartup=1,
	  ,asyncSupported=false,
		 smallIcon="",largeIcon="",
			description="",displayName=""
	 * */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		    request.setCharacterEncoding("UTF-8");  
	        ServletConfig config = getServletConfig();  
	        PrintWriter out = response.getWriter();  
	        out.println("<html>");  
	        out.println("<body>");  
	        out.println("Hello world"+"<br/>");  
	        out.println(config.getInitParameter("name")+"<br/>");  
	        out.println(request.getParameter("usrName"));  
	        out.println("</body>");  
	        out.println("</html>");  
	}
	
}
