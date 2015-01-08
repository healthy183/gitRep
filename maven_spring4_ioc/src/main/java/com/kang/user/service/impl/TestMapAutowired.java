package com.kang.user.service.impl;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.kang.user.service.ImainService;


@Component
public class TestMapAutowired {

	@Autowired
	private Map<String,ImainService> MainServiceMap;
	
	public Map<String, ImainService> getMainServiceMap() {
		return MainServiceMap;
	}
	
	public static void main(String[] args) {
		
		BeanFactory  factory = new ClassPathXmlApplicationContext("applicationContext.xml");  
		TestMapAutowired testMapAutowired = (TestMapAutowired) factory.getBean("testMapAutowired");  
		
		Map<String, ImainService>  mapService = testMapAutowired.getMainServiceMap();

		Set<String> keySet =  mapService.keySet();
		
		ImainService imainService = null;
		
		for(String strKey : keySet){
			
			imainService = mapService.get(strKey);
		 
			System.out.println(strKey+":"+imainService.lgnin());
		}
		
		
	}
	
}
