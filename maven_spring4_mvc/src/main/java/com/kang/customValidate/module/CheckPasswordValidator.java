package com.kang.customValidate.module;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderCustomizableContext;

public class CheckPasswordValidator implements ConstraintValidator<CheckPassword, CustomValidateModel> {

	public void initialize(CheckPassword checkPassword) {
		
	}

	public boolean isValid
		(CustomValidateModel customValidateModel,
			ConstraintValidatorContext context) {
		
		if(customValidateModel == null){
			return true;
		}

		String passWord = customValidateModel.getPassword();
		String confirmation = customValidateModel.getConfirmation();
		//i suggest you validated through common.StringUtils;
		if(passWord == null || passWord.trim().equals("")){
			
			ConstraintValidatorTools.addValidateMsg
				(context, "checkPassword.password.null", "password");
			
			/*  pls attention "disableDefaultConstraintViolation"
			 * context.disableDefaultConstraintViolation();
			ConstraintViolationBuilder builder = 
					context.buildConstraintViolationWithTemplate("");
			NodeBuilderCustomizableContext nodeBuilderCustomizableContext =
					builder.addPropertyNode("confirmation");
			nodeBuilderCustomizableContext.addConstraintViolation();*/
			
			return false;
		}
		
		if(confirmation == null || confirmation.trim().equals("")){
			
			ConstraintValidatorTools.addValidateMsg
				(context, "checkPassword.confirmation.null", "confirmation");
		/*	context.disableDefaultConstraintViolation();
			ConstraintViolationBuilder builder = 
					context.buildConstraintViolationWithTemplate("");
			NodeBuilderCustomizableContext nodeBuilderCustomizableContext =
					builder.addPropertyNode("confirmation");
			nodeBuilderCustomizableContext.addConstraintViolation();*/
			return false;
		}
		
		String trimPassWord = passWord.trim();
		String trimConfirmation = confirmation.trim();
		
		if(!trimPassWord.equals(trimConfirmation)){
			ConstraintValidatorTools.addValidateMsg
				(context, "checkPassword.confirmation.unequals", "confirmation");
			return false;
		}
		
		return true;
	}

}
