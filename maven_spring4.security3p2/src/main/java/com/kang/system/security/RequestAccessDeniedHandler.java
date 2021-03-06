package com.kang.system.security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Message;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;

public class RequestAccessDeniedHandler implements AccessDeniedHandler {
	
	 private String errorPage;  
	
	public void handle(HttpServletRequest request,
			HttpServletResponse response,
			AccessDeniedException accessDeniedException)
			throws IOException, ServletException {
		
	
		
		String requestType = request.getHeader("X-Requested-With");  
		boolean isAjaxRequest =  StringUtils.equals(requestType,"XMLHttpRequest");
		
		 if(isAjaxRequest){  
	            //Message msg = MessageManager.exception(accessDeniedException);  
	           // ControllerTools.print(response, msg);  
	        }else if (!response.isCommitted()) {  
	            if (errorPage != null) {  
	                // Put exception into request scope (perhaps of use to a view)  
	                request.setAttribute(WebAttributes.ACCESS_DENIED_403, accessDeniedException);  
	  
	                // Set the 403 status code.  
	                response.setStatus(HttpServletResponse.SC_FORBIDDEN);  
	  
	                // forward to error page.  
	                RequestDispatcher dispatcher = request.getRequestDispatcher(errorPage);  
	                dispatcher.forward(request, response);  
	            } else {  
	                response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());  
	            }  
	        } 
      
      
	}
	
	 public void setErrorPage(String errorPage) {  
	        if ((errorPage != null) && !errorPage.startsWith("/")) {  
	            throw new IllegalArgumentException("errorPage must begin with '/'");  
	        }  
	  
	        this.errorPage = errorPage;  
	    } 

}
