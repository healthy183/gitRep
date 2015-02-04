package com.at.mul;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import com.at.mul.domain.customer.Customer;
import com.at.mul.repository.customer.CustomerRepository;
import com.at.mul.repository.order.OrderRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MainConfig.class)
@TransactionConfiguration(transactionManager = "transactionManager",defaultRollback=false)
@Transactional
public class TestCreate {

	@Autowired
	private PlatformTransactionManager transactionManager;

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Test
	public void testSave() {
		
		//int size = orderRepository.findAll().size();
		//Assert.assertEquals(1, orderRepository.findAll().size());
		
		int customerSize = customerRepository.findAll().size();
		
		Customer c = new Customer();
		c.setName("test-name2");
		c.setAge(30);
		Customer cust = customerRepository.save(c);
		
		Assert.assertEquals(++customerSize, customerRepository.findAll().size());

	}
	
}
