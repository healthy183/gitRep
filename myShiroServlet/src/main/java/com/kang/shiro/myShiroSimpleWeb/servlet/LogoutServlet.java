package com.kang.shiro.myShiroSimpleWeb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

@WebServlet(urlPatterns={"/logout","/logout2"})
public class LogoutServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		
		//be base on shiro.ini
		//if /logout to logout.jsp
		//if /logout2 to login.jsp
		
		req.getRequestDispatcher("/WEB-INF/jsp/logout.jsp").forward(req, resp);
		
		
	}

	
	
	
	
	

}
