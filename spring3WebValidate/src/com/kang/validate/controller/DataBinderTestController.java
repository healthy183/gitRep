package com.kang.validate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kang.validate.model.DataBinderTestModel;

@Controller
public class DataBinderTestController {

	@RequestMapping(value = "/dataBind")
	public String test(DataBinderTestModel command, Model model) {

		// 输出command对象看看是否绑定正确
		System.out.println(command);
		model.addAttribute("dataBinderTest", command);

		return "bind/success";
	}

}
