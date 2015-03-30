package com.kang.batch2.partition.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kang.batch2.partition.model.User;

@Component("userProcessor")
@Scope("step")
@Transactional
public class UserProcessor implements ItemProcessor<User,User> {
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Value("#{stepExecutionContext[name]}")
	private String excutionContextId;	
	
	public User process(User item) throws Exception {
		
		System.out.println("threadId:"+Thread.currentThread().getId() +",excutionContextId:"+excutionContextId+" processing: "+ item.getId()+","+item.getName());
		//jdbcTemplate.execute("update users set utype  = 1 where id = " + item.getId());
		
		/*try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		
		return item;
	}

	

}
