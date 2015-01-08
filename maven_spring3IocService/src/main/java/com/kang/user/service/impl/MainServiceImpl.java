package com.kang.user.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kang.user.service.ImainService;


@Scope("prototype")
@Service("imainService")
@Transactional
public class MainServiceImpl implements ImainService {

	
	public String lgnin(){
		System.out.println("this is lgnin!");
		return "good";
	};
}
