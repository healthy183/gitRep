package com.kang.supportedValidationTarget.module;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = CrossParameterValidator.class)  
@Target({java.lang.annotation.ElementType.METHOD,
		java.lang.annotation.ElementType.CONSTRUCTOR,
		java.lang.annotation.ElementType.ANNOTATION_TYPE }) 
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)  
@Documented
public @interface CrossParameter {

	
	String message() default "{password.confirmation.error}";  
	 Class<?>[] groups() default { };  
	 Class<? extends Payload>[] payload() default { }; 
}
