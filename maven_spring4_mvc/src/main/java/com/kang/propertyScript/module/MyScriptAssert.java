package com.kang.propertyScript.module;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ java.lang.annotation.ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {PropertyScriptAssertValidator.class})
@Documented
public @interface MyScriptAssert {
	public abstract String message();

	public abstract Class<?>[] groups() default { };  ;

	public abstract Class<? extends Payload>[] payload() default { };  ;

	public abstract String lang();

	public abstract String script();

	public abstract String alias() default "_this";
	
	public abstract String property() default "confirmation";

	@Target({ java.lang.annotation.ElementType.TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	public static @interface List {
		public abstract MyScriptAssert[] value();
	}
}