package com.kang.shiro.myShiroSimpleWeb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

@WebServlet(urlPatterns={"/login"})
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String errorStr = null;
		
		
		String usrName = req.getParameter("usrName");
		String usrPwd = req.getParameter("usrPwd");
		
		Subject subject = SecurityUtils.getSubject();
		
		 UsernamePasswordToken token = new UsernamePasswordToken(usrName, usrPwd); 
		
		 try {
			 subject.login(token);
		} catch (UnknownAccountException e) {
			//errorStr="UnknownAccountException!";
			errorStr="UnknownAccountException or wrong password!";
		} catch (IncorrectCredentialsException e) {
			errorStr="UnknownAccountException or wrong password!";
		}catch (AuthenticationException e) {
			errorStr = "another error：" + e.getMessage();
        }
		 //ExcessiveAttemptsException  //try over 5 times
		 //LockedAccountException
		if(errorStr != null){
			
			req.setAttribute("error", errorStr);
			req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
			
		}else{// login successfully
			req.getRequestDispatcher("/WEB-INF/jsp/loginSuccess.jsp").forward(req, resp);
		}
		
		
	}
	
	
	

}
