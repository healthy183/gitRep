package com.kang.conversion.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kang.conversion.annotation.PhoneNumber;
import com.kang.conversion.model.FormatterModel;
import com.kang.conversion.model.PhoneNumberModel;

@Scope("prototype")
@Controller
@RequestMapping("/format")
public class AnnotationFormattingConversionController {

	@RequestMapping("/formatPrototype")
	public ModelAndView formatPrototype(
			@ModelAttribute("model")FormatterModel formatterModel){
		
		ModelAndView model = new ModelAndView();
		model.setViewName("format/formatPrototype");
		return model;
	}
	
	@RequestMapping("/formatFielt")
	public ModelAndView formatFielt(@PhoneNumber @RequestParam("phoneNumber") 
		PhoneNumberModel phoneNumber){
		
		ModelAndView view = new ModelAndView();
		view.addObject("phoneNumber", phoneNumber);
		view.setViewName("format/formatFielt");
		return view;
	}
	
}
