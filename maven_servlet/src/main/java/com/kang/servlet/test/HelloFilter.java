package com.kang.servlet.test;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(servletNames="wfh",filterName="HelloFilter",urlPatterns={"/*"} ,asyncSupported=true)
public class HelloFilter implements Filter {

	public void destroy() {
			System.out.println("hello filter destory!");
	}

	public void doFilter(ServletRequest servletrequest,
			ServletResponse servletresponse, FilterChain filterchain)
			throws IOException, ServletException {

		System.out.println("hello filtering !");
		
		
		
		HttpServletRequest  request = (HttpServletRequest)servletrequest;
		
		String usrName = request.getParameter("usrName");
		
		if(!"out".equals(usrName)){
			filterchain.doFilter(servletrequest, servletresponse);
		}else{
			
			HttpServletResponse response = (HttpServletResponse)servletresponse;
			response.sendRedirect("index.jsp");
			System.out.println("this is out!");
			
		}
		
	}

	public void init(FilterConfig filterconfig) throws ServletException {
		
		System.out.println(" hello filter  init!");
		
		ServletContext context = filterconfig.getServletContext();
		
		//context.
		//context.getr
		//doFilter();
		
	}

}
