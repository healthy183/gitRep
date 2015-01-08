package com.kang.newThreePointOne.model;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

public class ReturnHandlerUser implements HandlerMethodReturnValueHandler  {

	public boolean supportsReturnType(MethodParameter returnType) {
		
		Class<?> type = returnType.getParameterType();  
		
		 if(IocNewUser.class.equals(type)){
			 return true;
		 }  
		
		return false;
	}
	
	public void handleReturnValue(Object object, MethodParameter methodParameter,
			ModelAndViewContainer modelAndViewContainer, 
				NativeWebRequest nativeWebRequest) throws Exception {
		
		modelAndViewContainer.setViewName("handlerController/testNewUser");
	}

	

}
