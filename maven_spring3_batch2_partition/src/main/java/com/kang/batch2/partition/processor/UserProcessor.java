package com.kang.batch2.partition.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.kang.batch2.partition.model.User;

@Component("userProcessor")
@Scope("step")
public class UserProcessor implements ItemProcessor<User,User> {
	
	@Value("#{stepExecutionContext[name]}")
	private String threadName;	
	
	public User process(User item) throws Exception {
		
		System.out.println(threadName +" processing: "+ item.getId()+","+item.getName());
		
		return item;
	}

	

}
