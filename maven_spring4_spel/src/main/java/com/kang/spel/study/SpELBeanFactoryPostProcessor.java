package com.kang.spel.study;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.expression.StandardBeanExpressionResolver;

public class SpELBeanFactoryPostProcessor implements  BeanFactoryPostProcessor {

	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
			throws BeansException {
		
		
		StandardBeanExpressionResolver resolver = 
				(StandardBeanExpressionResolver) beanFactory.getBeanExpressionResolver();
		resolver.setExpressionPrefix("%{");  
		resolver.setExpressionSuffix("}");  
		
		
	}
	
	
	

}
