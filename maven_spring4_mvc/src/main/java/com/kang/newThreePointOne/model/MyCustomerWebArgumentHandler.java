package com.kang.newThreePointOne.model;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

public class MyCustomerWebArgumentHandler implements WebArgumentResolver {

	public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest nativeWebRequest)
			throws Exception {
		
		if(methodParameter.getParameterType().equals(MyArgumentUser.class)){
			
			MyArgumentUser usr = new MyArgumentUser();
			usr.setArgumentUsrId(123);
			usr.setArgumentUsrName("ljk");
			usr.setArgumentUsrSwd("swdKang");
			return usr;
		}
		
		
		return UNRESOLVED;
	}

}
