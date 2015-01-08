package cn.javass.chapter7.web.controller.validate;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.javass.chapter7.model.UserModel;

@Controller
public class HelloWorldController {
	/**
	 * org.springframework.validation.BeanPropertyBindingResult: 1 errors
Field error in object 'user' on field 'username': rejected value [null]; codes [NotNull.user.username,NotNull.username,NotNull.java.lang.String,NotNull]; arguments
 [org.springframework.context.support.DefaultMessageSourceResolvable: codes [user.username,username]; arguments []; default message [username]]; default message [用户名不能为空]
	 * 
	 * **/
	
	@RequestMapping("/validate/hello")
	public String validate(@Valid @ModelAttribute("user") UserModel user, Errors errors) {
		
		
		org.springframework.validation.BeanPropertyBindingResult result;
		org.springframework.context.support.DefaultMessageSourceResolvable abele;
		if(errors.hasErrors()) {
			return "validate/error";
		}
		return "redirect:/success";
	}
}
