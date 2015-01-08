package com.kang.customValidate.module;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/*@Target({ java.lang.annotation.ElementType.METHOD,
java.lang.annotation.ElementType.FIELD,
java.lang.annotation.ElementType.ANNOTATION_TYPE,
java.lang.annotation.ElementType.CONSTRUCTOR,
java.lang.annotation.ElementType.PARAMETER })*/

@Target({java.lang.annotation.ElementType.TYPE,
	java.lang.annotation.ElementType.ANNOTATION_TYPE}) 

@Retention(java.lang.annotation.RetentionPolicy.RUNTIME) 
@Constraint(validatedBy=CheckPasswordValidator.class)
@Documented
public @interface CheckPassword {
	
	
	//默认错误消息 
	String message() default "";  
	
	//分组  
	Class<?>[] groups() default { };  
	
	//负载  
	Class<? extends Payload>[] payload() default { };  
	
	//指定多个时使用
	@Target({ 
		java.lang.annotation.ElementType.FIELD, 
		java.lang.annotation.ElementType.METHOD, 
		java.lang.annotation.ElementType.PARAMETER,
		java.lang.annotation.ElementType.ANNOTATION_TYPE })  
	@interface List { 
		CheckPassword[] value();  
	}
}
