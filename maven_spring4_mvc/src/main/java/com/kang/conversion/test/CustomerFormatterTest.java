package com.kang.conversion.test;

import org.springframework.format.support.DefaultFormattingConversionService;

import com.kang.conversion.conversion.PhoneNumberFormatter;
import com.kang.conversion.model.PhoneNumberModel;

public class CustomerFormatterTest {

	
	public static void main(String[] args) {
		
		
		  DefaultFormattingConversionService conversionService = 
				  new DefaultFormattingConversionService(); 
		  
		  conversionService.addFormatter(new PhoneNumberFormatter());  
		  
		  PhoneNumberModel phoneNumber = new PhoneNumberModel("010", "12345678"); 
		  
		 String num =  conversionService.convert(phoneNumber, String.class);
		 
		 System.out.println("num:"+num);
		 
		 PhoneNumberModel phoneNumberModel = conversionService.convert
				 ("010-12345678", PhoneNumberModel.class);
		 
		 System.out.println("phoneNumberModel:"+phoneNumberModel.toString());
	}
}
