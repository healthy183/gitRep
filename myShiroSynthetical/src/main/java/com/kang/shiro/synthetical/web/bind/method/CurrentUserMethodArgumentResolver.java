package com.kang.shiro.synthetical.web.bind.method;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.kang.shiro.synthetical.web.bind.annotation.CurrentUser;


/**
 * <p>用于绑定@FormModel的方法参数解析器
 */
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

	public CurrentUserMethodArgumentResolver() {
	}

	public boolean supportsParameter(MethodParameter methodParameter) {
	
		if(methodParameter.hasParameterAnnotation(CurrentUser.class)){
			
			return true;
		}
				
		
		return false;
	}	
	
	public Object resolveArgument
		(MethodParameter parameter,
				ModelAndViewContainer mavContainer, 
					NativeWebRequest webRequest, 
						WebDataBinderFactory binderFactory) throws Exception {
		 
		CurrentUser currentUserAnnotation = parameter.getParameterAnnotation(CurrentUser.class);
	      return webRequest.getAttribute(currentUserAnnotation.value(), NativeWebRequest.SCOPE_REQUEST);
	    
	}

	

}
