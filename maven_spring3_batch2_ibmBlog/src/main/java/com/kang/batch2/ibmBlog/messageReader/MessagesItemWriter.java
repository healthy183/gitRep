package com.kang.batch2.ibmBlog.messageReader;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.item.ItemWriter;

import com.kang.batch2.ibmBlog.model.Message;

@Slf4j
public class MessagesItemWriter implements ItemWriter<Message> {

	public void write(List<? extends Message> messageList) throws Exception {
			log.info("MessagesItemWriter is writing!");
			
			for(Message message : messageList){
				System.out.println(message.getContent());
			}
			
	}

}
