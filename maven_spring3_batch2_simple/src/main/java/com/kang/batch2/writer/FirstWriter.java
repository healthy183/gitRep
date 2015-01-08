package com.kang.batch2.writer;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.item.ItemWriter;

import com.kang.batch2.model.FirstModel;

@Slf4j
public class FirstWriter implements ItemWriter<String> {

	public void write(List<? extends String> items) throws Exception {
		
		//log.info("this is first writer");
		
		for(String s: items){
			log.info("write to json:" + s);
		}
			
	}
	
	
}
