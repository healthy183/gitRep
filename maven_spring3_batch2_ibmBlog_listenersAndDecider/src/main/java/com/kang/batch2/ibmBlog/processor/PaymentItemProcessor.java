package com.kang.batch2.ibmBlog.processor;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.item.ItemProcessor;

import com.kang.batch2.ibmBlog.exception.MoneyNotEnoughException;
import com.kang.batch2.ibmBlog.model.Bill;
import com.kang.batch2.ibmBlog.model.PayRecord;

@Slf4j
public class PaymentItemProcessor   implements ItemProcessor<Bill, PayRecord>{

	public PayRecord process(Bill item) throws Exception {
		
		    log.info("paying!");
		
		if (item.getUser().getBalance() <= 0) {
			
			log.info( "userId:"+item.getUser().getId()+" did not had any money!");
			
			return null;
		}
		if (item.getUser().getBalance() >= item.getUnpaidFees()) {
			// create payrecord
			PayRecord pr = new PayRecord();
			pr.setBill(item);
			pr.setPaidFees(item.getUnpaidFees());
			// update balance
			item.getUser().setBalance(item.getUser().getBalance() - item.getUnpaidFees());
			// update bill
			item.setPaidFees(item.getUnpaidFees());
			item.setUnpaidFees(0.0);
			item.setPayStatus(1);/* paid */
			
			log.info("userId:"+item.getUser().getId()+"pay successfully!");
			
			return pr;
			
		} else {
			log.info("userId:"+item.getUser().getId()+" did not had  enough money!");
			
			throw new MoneyNotEnoughException();
		}
	}

	
	
}
