package com.at.mul;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import com.at.mul.repository.customer.CustomerRepository;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MainConfig.class)
@TransactionConfiguration(transactionManager = "transactionManager",defaultRollback=false)
@Transactional
public class TestUpdate {

	@Autowired
	private PlatformTransactionManager transactionManager;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Test
	public void queryUpdate(){
		
		int row =  customerRepository.queryUpdate(13,"updateName2");
	}
	
	
	
}
