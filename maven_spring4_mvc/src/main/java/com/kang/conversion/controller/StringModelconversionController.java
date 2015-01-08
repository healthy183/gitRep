package com.kang.conversion.controller;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kang.conversion.model.PhoneNumberModel;
import com.kang.conversion.model.StringToNumModel;


@Scope("prototype")
@Controller
public class StringModelconversionController {

	
	@RequestMapping(value="/conversion/stringtoModel")
	public ModelAndView StringtoModel(StringToNumModel model){
	
		PhoneNumberModel pmodel = model.getModel();
		System.out.println(pmodel.toString());
		
		
		Date date = model.getConversionDate();
		System.out.println("dates:"+date.toString());
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("thisModel", model);
		
		
		
		modelAndView.setViewName("conversion/stringtoModel");
		
		return modelAndView;
	}
}
