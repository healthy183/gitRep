package com.kang.advice;

public class AdviceClass {

	public void doingAfterAdvice() {
		System.out.println("AfterAdvice is here!");
	}

	
	
	public void testStatedAfterAdvice(java.lang.String param){
		System.out.println("this is after:" +param);
	}
	
	public void doingReturnAfterAdice(Object param) {
		System.out.println("after object:"+param.toString());
	}


	public void doingExceptionAfterAdice(Exception exception) {
		System.out.println(exception.getStackTrace());
	}

	
	
	

	
	
	
}
