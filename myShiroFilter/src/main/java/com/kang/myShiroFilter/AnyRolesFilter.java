package com.kang.myShiroFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

public class AnyRolesFilter extends AccessControlFilter  {

	 private String unauthorizedUrl = "/unauthorized.jsp";
	    private String loginUrl = "/";
	
	
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
	
		String[] roleStr = (String[])mappedValue;
		if(roleStr == null){
			return true;//
		}
		
		
		System.out.println(getSubject(request, response).getPrincipal());
		
		for(String role :roleStr){
			if(getSubject(request, response).hasRole(role)){
				return true;
			}
		}
		
		return false;// if false  to onAccessDenied!
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {

		 Subject subject = getSubject(request, response);
		
		 if(subject.getPrincipal() == null){//no login,redirect to index.jsp!
			 saveRequest(request);
			 WebUtils.issueRedirect(request, response, loginUrl);
		 }else{
			 if(StringUtils.hasText(unauthorizedUrl)){
				 WebUtils.issueRedirect(request, response, unauthorizedUrl);
			 }else{
				 WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
			 }
		 }
		 
		return false;
	}

	
	
	

}
