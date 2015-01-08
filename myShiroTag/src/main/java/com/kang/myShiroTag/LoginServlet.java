package com.kang.myShiroTag;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;


@WebServlet(urlPatterns={"/loginServlet"})
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String usrName = req.getParameter("usrName");
		String usrPwd = req.getParameter("usrPwd");
		
		Subject subject = SecurityUtils.getSubject();
		
		 UsernamePasswordToken token = new UsernamePasswordToken(usrName, usrPwd); 
		
		 String errorStr = null;
		 
		 try {
			 subject.login(token);
		} catch (Exception e) {
			 errorStr = "login fail!";
		
		}
		 	if(errorStr != null){
		 		req.setAttribute("error", errorStr);
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
		 	}
			
		 
		 
		 req.getRequestDispatcher("/loginSuccess.jsp").forward(req, resp); 
		 
		
		
	}

	
	
	
	

}
