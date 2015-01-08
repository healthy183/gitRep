package com.kang.conversion.test;

import java.util.Date;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.format.support.DefaultFormattingConversionService;

import com.kang.conversion.model.FormatterModel;

public class VariableFormatterTest {

	
	
	public static void main(String[] args) throws SecurityException, NoSuchFieldException {
		
		DefaultFormattingConversionService conversionService =   
                new DefaultFormattingConversionService();  
		
		  //准备测试模型对象  
	    FormatterModel model = new FormatterModel();  
	    model.setTotalCount(10000);  
	    model.setDiscount(0.51);  
	    model.setSumMoney(10000.13);  
	    model.setRegisterDate(new Date(2012-1900, 4, 1));  
	    model.setOrderDate(new Date(2012-1900, 4, 1, 20, 18, 18));  
		
	  /*** TypeDescriptor：拥有类型信息的上下文，
	   *   用于Spring3类型转换系统获取类型信息的（可以包含类、字段、方法参数、属性信息）；
	               通过TypeDescriptor，我们就可以获取（类、字段、方法参数、属性）的各种信息，如注解类型信息；
	    */
		//获取类型信息  
	    TypeDescriptor descriptor =   new TypeDescriptor
	    		(FormatterModel.class.getDeclaredField("totalCount"));  
	    
	    TypeDescriptor stringDescriptor = TypeDescriptor.valueOf(String.class); 
	    //将totalCount格式化为字符串类型，此处会根据totalCount字段的注解信息（通过descriptor对象获取）来进行格式化
	    String totalCountStr = (String)conversionService.convert(model.getTotalCount(), descriptor, stringDescriptor);
	    System.out.println("totalCountStr:"+totalCountStr);//10,000
	    
	    //将字符串“10,000”解析为totalCount字段类型，此处会根据totalCount字段的注解信息（通过descriptor对象获取）来进行解析
	    int totalCount = (Integer) conversionService.convert("10,000", stringDescriptor, descriptor);
		System.out.println("totalCount:"+totalCount);//10000
		
		
		descriptor = new TypeDescriptor(FormatterModel.class.getDeclaredField("registerDate")); 
		
		String dateStr = (String)conversionService.convert(model.getRegisterDate(), descriptor, stringDescriptor);
		 
		System.out.println("dateStr:"+dateStr);
		
		Date date = (Date)conversionService.convert("2012-05-01", stringDescriptor, descriptor);
		
		System.out.println("date:"+date.toString());
	}
	
}
