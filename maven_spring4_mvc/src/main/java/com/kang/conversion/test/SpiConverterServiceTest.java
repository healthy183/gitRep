package com.kang.conversion.test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.format.number.CurrencyFormatter;
import org.springframework.format.support.DefaultFormattingConversionService;

public class SpiConverterServiceTest {

	public static void main(String[] args) {
		
		 DefaultFormattingConversionService conversionService = 
				 new DefaultFormattingConversionService();  
		
		//conversionService 默认不自动注册任何Formatter 
		CurrencyFormatter currencyFormatter = new CurrencyFormatter(); 
		 
		currencyFormatter.setFractionDigits(2);//保留小数点后几位  
	    currencyFormatter.setRoundingMode
	    	(RoundingMode.CEILING);//舍入模式（ceilling表示四舍五入）  
	    
	    //注册Formatter SPI实现  
	    conversionService.addFormatter(currencyFormatter);  
	    
	   //绑定Locale信息到ThreadLocal  
	   //FormattingConversionService内部自动获取作为Locale信息，
	   //如果不设值默认是 Locale.getDefault() 	 
	   LocaleContextHolder.setLocale(Locale.US);   
		 //$1,234.13
	   String usString = conversionService.convert
			 (new BigDecimal("1234.128"), String.class);
	   
	   System.out.println("usString:"+usString);//$1,234.13
		
	   
	   LocaleContextHolder.setLocale(null);  //set default
	   
	   LocaleContextHolder.setLocale(Locale.CHINA);
	   
	   String chinaString = conversionService.convert
			   (new BigDecimal("1234.128"), String.class); 
	   System.out.println("chinaString:"+chinaString);//￥1,234.13
	}
	
	
}
