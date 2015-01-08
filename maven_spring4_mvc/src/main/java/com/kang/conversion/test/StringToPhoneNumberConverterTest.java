package com.kang.conversion.test;

import org.springframework.core.convert.support.DefaultConversionService;

import com.kang.conversion.conversion.StringToPhoneNumberConverter;
import com.kang.conversion.model.PhoneNumberModel;

public class StringToPhoneNumberConverterTest {

	
	public static void main(String[] args) {
	
		//new serice
		DefaultConversionService  service = new DefaultConversionService();
		
		service.addConverter(new StringToPhoneNumberConverter());
		
		String phoneNumberStr = "020-12345678";  
		//convert
		PhoneNumberModel model = service.convert(phoneNumberStr, PhoneNumberModel.class);
	
		System.out.println(model.toString());
	}
	
	
}
