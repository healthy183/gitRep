package com.kang.conversion.test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.number.CurrencyFormatter;

public class SpiConverterTest {

	
	
	public static void main(String[] args) {
	
		//CurrencyFormatter：实现货币样式的格式化/解析 
		CurrencyFormatter currencyFormatter = new CurrencyFormatter(); 
		
		currencyFormatter.setFractionDigits(2);//保留小数点后几位 
		
		currencyFormatter.setRoundingMode
				(RoundingMode.CEILING);//舍入模式（ceilling表示四舍五入)
		
		try {
			Object object = currencyFormatter.parse("$123.125", Locale.US);
		
			String usNumString = object.toString();
			System.out.println("usNumString:"+usNumString);//123.13
			
			//将BigDecimal("123")格式化为字符串“$123.00”展示 
			String usNumStringPrint =  currencyFormatter.print
						(new BigDecimal("123"), Locale.US);
			
			System.out.println("usNumStringPrint:"+usNumStringPrint);//$123.00
			
			String chinaNumString = currencyFormatter.print
					(new BigDecimal("234"), Locale.CHINA); //the same as JAPAN
			
			System.out.println("chinaNumString:"+chinaNumString);//￥234.00
		
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
