package com.kang.myShiroFilter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;

public class FormLoginFilter  extends PathMatchingFilter {

	private String loginURL = "/login.jsp";
	private String successUrl = "/login.jsp";
	
	
	@Override
	protected boolean onPreHandle(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {

		boolean isAuthenticated = SecurityUtils.getSubject().isAuthenticated();	
		
		if(isAuthenticated){
        	System.out.println(SecurityUtils.getSubject().getPrincipal()+ " had login in!");
			return true;
		}
		
		HttpServletRequest httpServletRequest =  (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		
		if(isLoginRequest(httpServletRequest)){
			
			if("post".equalsIgnoreCase(httpServletRequest.getMethod())){
				boolean isloginSuccess =  login(httpServletRequest);
				
				if(isloginSuccess){
				     return redirectToSuccessUrl(httpServletRequest, httpServletResponse);
		                
				}
			}
			return true;
		}else{
			saveRequestAndRedirectToLogin(httpServletRequest, httpServletResponse);
			return false;
		}
		
		
	}
	
	
	private boolean redirectToSuccessUrl(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("to /!");
		WebUtils.redirectToSavedRequest(req, resp, successUrl);
		 return false;
	}
	
	private void saveRequestAndRedirectToLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
			WebUtils.saveRequest(req);
			System.out.println("to index.jsp!");
	        WebUtils.issueRedirect(req, resp, "index.jsp");
	    
	}
	
	private boolean login(HttpServletRequest req) {
		
		String username = req.getParameter("usrName");
        String password = req.getParameter("usrPwd");
		
        try {
            SecurityUtils.getSubject().login(new UsernamePasswordToken(username, password));
        } catch (Exception e) {
            req.setAttribute("shiroLoginFailure", e.getClass());
            return false;
        }
        
		return true;
	}
	
	
	private boolean isLoginRequest(HttpServletRequest req) {
		
		return pathsMatch(loginURL, WebUtils.getPathWithinApplication(req));
		
	}
	
	
	
	

}
