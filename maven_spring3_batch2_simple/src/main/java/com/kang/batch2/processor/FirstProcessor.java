package com.kang.batch2.processor;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.item.ItemProcessor;

import com.kang.batch2.model.FirstModel;

@Slf4j
public class FirstProcessor implements ItemProcessor<FirstModel,String> {

	public String process(FirstModel item) throws Exception {
		
		log.info("this is FirstProcessor!");
		
		return item.toString();
	}

}
