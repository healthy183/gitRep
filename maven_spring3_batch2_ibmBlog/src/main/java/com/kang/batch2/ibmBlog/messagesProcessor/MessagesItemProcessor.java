package com.kang.batch2.ibmBlog.messagesProcessor;

import org.springframework.batch.item.ItemProcessor;

import com.kang.batch2.ibmBlog.model.Message;
import com.kang.batch2.ibmBlog.model.User;

public class MessagesItemProcessor implements ItemProcessor<User, Message> {

	public Message process(User user) throws Exception {
		
		Message m = new Message();
		
		if(null == user.getName() || "".equals(user.getName().trim())){
			throw new RuntimeException();
		}
		
		m.setContent("Hello " + user.getName()
				+ ",please pay promptly at the end of this month.");
		return m;
	}

}
