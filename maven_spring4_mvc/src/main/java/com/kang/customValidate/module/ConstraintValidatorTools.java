package com.kang.customValidate.module;

import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderCustomizableContext;

public class ConstraintValidatorTools {

	
	
	public static void addValidateMsg(
			ConstraintValidatorContext context,String validateTemplate,String propertyNode){
		
		context.disableDefaultConstraintViolation();
		ConstraintViolationBuilder builder = 
				context.buildConstraintViolationWithTemplate("{"+validateTemplate+"}");
		NodeBuilderCustomizableContext nodeBuilderCustomizableContext =
				builder.addPropertyNode(propertyNode);
		nodeBuilderCustomizableContext.addConstraintViolation();
		
	}
	
}
