package com.kang.newThreePointTwo.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kang.newThreePointOne.model.IocNewUser;


@Scope("prototype")
@Controller
@RequestMapping("/newAttributeattributeInThreePointTwo")
public class NewThreePointTwoController {

	
/*	@RequestMapping("/testMatrixVariable/{ownerId}/pets/{petId}")
	public String testMatrixVariable
		(@MatrixVariable Map<String,String> ownerId,
			@MatrixVariable(pathVar = "petId") Map<String,String> petId){
		
		System.out.println("ownerId:"+ownerId);
		System.out.println("petId:"+petId);
		
		return "newAttributeattributeInThreePointTwo/testMatrixVariable";
	}*/
	
	
	@RequestMapping(value = "/voidfindPet/pets/{petId}", method = RequestMethod.GET) 
	public String voidfindPet(@PathVariable String petId, @MatrixVariable int q) {
	
		System.out.println("petId:"+petId);
		System.out.println("q:"+q);
		
		return "newAttributeattributeInThreePointTwo/testMatrixVariable";
	};
	
	@RequestMapping("/testCtrlAdvice")
	public String testCtrlAdvice(){
		
		String a = "a";
		
		
		IocNewUser u = null;
		u.setUsrSwd("11");
		
		
		return "newAttributeattributeInThreePointTwo/testCtrlAdvice";
	}
	
	
}
