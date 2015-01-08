package com.kang.myShiroFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.servlet.AdviceFilter;

public class MyAdviceFilter  extends AdviceFilter {

	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response)
			throws Exception {
		
		//the same as springAOP beforeAdvice
		System.out.println("this is preHandle!");
		return true;
	}

	@Override
	protected void postHandle(ServletRequest request, ServletResponse response)
			throws Exception {
		// the same as springAOP afterAdvice
		System.out.println("this is postHandle!");
	}

	@Override
	public void afterCompletion(ServletRequest request,
			ServletResponse response, Exception exception) throws Exception {
		// the same as springAOP finallyAdivce
		System.out.println("this is afterCompletion!");
	
	}

	
	
	
	

}
