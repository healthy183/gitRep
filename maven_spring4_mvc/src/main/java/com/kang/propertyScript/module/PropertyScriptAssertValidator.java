package com.kang.propertyScript.module;

import javax.script.ScriptException;
import javax.validation.ConstraintDeclarationException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.validator.internal.util.Contracts;
import org.hibernate.validator.internal.util.logging.Log;
import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.hibernate.validator.internal.util.scriptengine.ScriptEvaluator;
import org.hibernate.validator.internal.util.scriptengine.ScriptEvaluatorFactory;

public class PropertyScriptAssertValidator
 implements ConstraintValidator<MyScriptAssert,Object> {

	// org.hibernate.validator.internal.constraintvalidators.ScriptAssertValidator v;
	 
	 private static final Log log = LoggerFactory.make();  
	
	private String script;
	private String languageName;
	private String alias;
	private String property;
	private String message; 
	 
	
	public void initialize(MyScriptAssert constraintAnnotation) {

		validateParameters(constraintAnnotation);
		
		this.script = constraintAnnotation.script();
		this.languageName = constraintAnnotation.lang();
		this.alias = constraintAnnotation.alias();
		this.property = constraintAnnotation.property();
		this.message = constraintAnnotation.message();
	}

	public boolean isValid(Object value,
			ConstraintValidatorContext constraintValidatorContext) {
		
		Object evaluationResult;  
		ScriptEvaluator scriptEvaluator; 
		
        try {
        	
        	 ScriptEvaluatorFactory evaluatorFactory = ScriptEvaluatorFactory.getInstance(); 
        	
			scriptEvaluator  = evaluatorFactory.getScriptEvaluatorByLanguageName(languageName);
		
        } catch (ScriptException e) {
        	 throw new ConstraintDeclarationException( e );  
		}
        
        try {
			evaluationResult = scriptEvaluator.evaluate( script, value, alias );
		} catch (ScriptException e) {
			throw log.getErrorDuringScriptExecutionException( script, e );  
		}
        
        if(!( evaluationResult instanceof Boolean )){
        	throw log.getScriptMustReturnTrueOrFalseException(  
                    script,  
                    evaluationResult,  
                    evaluationResult.getClass().getCanonicalName()  
            ); 
        }
        
        
        if(Boolean.FALSE.equals(evaluationResult)) {
        	
        	constraintValidatorContext.disableDefaultConstraintViolation(); 
     
        	constraintValidatorContext  
        		.buildConstraintViolationWithTemplate(message)  
            		.addPropertyNode(property)  
            			.addConstraintViolation();  
        }
        
		return Boolean.TRUE.equals( evaluationResult);
	}

	private void validateParameters(MyScriptAssert constraintAnnotation) {

		Contracts.assertNotEmpty(constraintAnnotation.script(),
				org.hibernate.validator.internal.util.logging.Messages.MESSAGES
						.parameterMustNotBeEmpty("script"));

		Contracts.assertNotEmpty(constraintAnnotation.lang(),
				org.hibernate.validator.internal.util.logging.Messages.MESSAGES
						.parameterMustNotBeEmpty("lang"));

		Contracts.assertNotEmpty(constraintAnnotation.alias(),
				org.hibernate.validator.internal.util.logging.Messages.MESSAGES
						.parameterMustNotBeEmpty("alias"));

		Contracts.assertNotEmpty(constraintAnnotation.property(),
				org.hibernate.validator.internal.util.logging.Messages.MESSAGES
						.parameterMustNotBeEmpty("property"));
		Contracts.assertNotEmpty(constraintAnnotation.message(),
				org.hibernate.validator.internal.util.logging.Messages.MESSAGES
						.parameterMustNotBeEmpty("message"));

	}

	
}
