package com.kang.batch2.ibmBlog.processor;


import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.item.ItemProcessor;

import com.kang.batch2.ibmBlog.model.Bill;
import com.kang.batch2.ibmBlog.model.Message;

/**
 * @author kunrey
 * 
 */
@Slf4j
public class ArrearsMessagesItemProcessor implements
		ItemProcessor<Bill, Message> {

	public Message process(Bill item) throws Exception {
		if (item.getPayStatus() == 0) {/*unpaid*/
			Message m = new Message();
			m.setUser(item.getUser());
			m.setContent("Hello " + item.getUser().getName()
					+ ",please pay promptly at end of this month.");
		
			log.info("noted user:"+item.getUser().getId()+" pay promptly at end of this month.");
			
			return m;
		}
		return null;
	}

}
