package com.kang.supportedValidationTarget.module;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

@SupportedValidationTarget(ValidationTarget.PARAMETERS)  
public class CrossParameterValidator 
	implements ConstraintValidator<CrossParameter, Object[]> {

	public void initialize(CrossParameter arg0) {
		
	}

	public boolean isValid(Object[] value, 
			ConstraintValidatorContext context) {
		
		
		if(value != null && value.length != 2){
			throw new IllegalArgumentException("must have two args");  
		}
		
		if(value[0] == null && value[1] == null){
			return true;  
		}
		
		if(value[0].equals(value[1])){
			return true;  
		}
		

		return false;
	}

}
