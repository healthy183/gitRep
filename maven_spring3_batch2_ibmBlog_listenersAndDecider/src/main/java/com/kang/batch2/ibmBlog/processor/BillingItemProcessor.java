package com.kang.batch2.ibmBlog.processor;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.item.ItemProcessor;

import com.kang.batch2.ibmBlog.model.Bill;
import com.kang.batch2.ibmBlog.model.User;

@Slf4j
public class BillingItemProcessor implements ItemProcessor<User, Bill> {

	public Bill process(User item) throws Exception {
		
		log.info("processing Bill.class and setUser(User.class),bill.payStatus is 0,means unpaid!");
		
		Bill b = new Bill();
		b.setUser(item);
		b.setFees(70.00);
		b.setPaidFees(0.0);
		b.setUnpaidFees(70.00);
		b.setPayStatus(0);/*unpaid*/
		
		log.info("process successfully,it will write in db,and step billingStep complate!");
		
		return b;
		
	}	
	
}
