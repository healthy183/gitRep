package com.kang.service.impl;

import com.kang.service.AfterAdvice;

public class AfterAdviceImpl implements AfterAdvice {

	public void testAfterAdvice() {
		System.out.println("running!");
	}

	public void testStatedAfterAdvice(String param) {
		System.out.println("running!");
	}

	
	
	public String testReturnAfterAdice(String param) {
		System.out.println("running!");
		return param;
	}

	public void testExceptionAfterAdice() {
		
		System.out.println("before throw!");
		//throw new Exception();
		int i = 1/0;
	}

	
	
	
	
}
