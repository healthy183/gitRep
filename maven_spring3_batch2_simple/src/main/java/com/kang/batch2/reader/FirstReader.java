package com.kang.batch2.reader;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.kang.batch2.model.FirstModel;

@Slf4j
public class FirstReader implements  ItemReader<FirstModel> {

	private int count;
	
	public FirstModel read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
		
		log.info("this is my first Reader!");
		
		FirstModel model = null;
		
		  if ( count < 2) {
              model = new FirstModel();
              model.setDescription( "My Description");
              model.setId( "My ID");
              model.setName( "My Name "+count);
              count++;
         }
		
		return model;
	}

}
