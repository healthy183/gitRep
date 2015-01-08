package com.kang.conversion.test;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.format.support.DefaultFormattingConversionService;

import com.kang.conversion.annotation.PhoneNumberFormatAnnotationFormatterFactory;
import com.kang.conversion.model.FormatterModel;
import com.kang.conversion.model.PhoneNumberModel;

public class AnnotationFormatterFactoryTest {

	
	
	
	public static void main(String[] args) throws SecurityException, NoSuchFieldException {
		
		DefaultFormattingConversionService conversionService =   
                new DefaultFormattingConversionService();//创建格式化服务
		
		conversionService.addFormatterForFieldAnnotation //添加自定义的注解格式化工厂  
			(new PhoneNumberFormatAnnotationFormatterFactory());
		
		FormatterModel model = new FormatterModel(); 
		
		TypeDescriptor descriptor =   
		        new TypeDescriptor(FormatterModel.class.getDeclaredField("phoneNumber"));
		
		TypeDescriptor stringDescriptor = TypeDescriptor.valueOf(String.class);
		////解析字符串"010-12345678"--> PhoneNumberModel 
		PhoneNumberModel value = (PhoneNumberModel) 
				conversionService.convert("010-12345678", stringDescriptor, descriptor); 
		
		model.setPhoneNumber(value); 
		
		String phoneNum = (String)conversionService.convert(model.getPhoneNumber(), descriptor, stringDescriptor);
		
		System.out.println("phoneNum:"+phoneNum);
		
		
	}
}
