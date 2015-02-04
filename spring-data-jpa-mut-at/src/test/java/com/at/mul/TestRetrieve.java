package com.at.mul;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import com.at.mul.domain.customer.Customer;
import com.at.mul.repository.customer.CustomerRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MainConfig.class)
@TransactionConfiguration(transactionManager = "transactionManager",defaultRollback=false)
@Transactional
public class TestRetrieve {

	@Autowired
	private PlatformTransactionManager transactionManager;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Test
	public void nativeQuery(){
		
		List<Customer> customerList =  customerRepository.nativeQuery();
		
		Assert.assertEquals(4, customerList.size());
		
	}
	
	//@Test
	public void queryPage(){
		
		Page<Customer> page = customerRepository.findAll(new PageRequest(0,2,new Sort(new Order(Direction.DESC,"id"))));
		
		Assert.assertEquals(2, page.getSize());
	}
	
	
	//@Test
	public void query(){
		
		Customer  customer = customerRepository.queryThisById(12);
		
		Assert.assertEquals("test-name",customer.getName());
		
		
		List<Customer> customerList = customerRepository.queryAll();
		
		Assert.assertTrue(customerList.size()>0);
		
		Customer  paramCustomer  =  customerRepository.queryByParam(12);
		
		Assert.assertEquals("test-name",paramCustomer.getName());
	}
	
	
	
	//@Test
	public void selectApi(){
		
		List<Customer> customerList = customerRepository.findAll();
		
		//Assert.assertEquals(1, customerRepository.findAll().size());
		
		Assert.assertTrue(customerList.size()>0);
		
		//more api pls check 'JpaRepository.java' and its parentClass;
	}
	
	//@Test
	public void autoSql(){
		
		List<Customer> customerList = customerRepository.findByName("test-name");
		
		Assert.assertTrue(customerList.size()>0);
		
		customerList = customerRepository.findByNameLike("test-name%");
		
		Assert.assertTrue(customerList.size()>0);
		
		Customer  customer = customerRepository.findByIdAndName(12,"test-name");
		
		Assert.assertEquals("test-name",customer.getName());
		/**** ----and so on!
		And --- 等价于 SQL 中的 and 关键字，比如 findByUsernameAndPassword(String user, Striang pwd)；
		Or --- 等价于 SQL 中的 or 关键字，比如 findByUsernameOrAddress(String user, String addr)；
		Between --- 等价于 SQL 中的 between 关键字，比如 findBySalaryBetween(int max, int min)；
		LessThan --- 等价于 SQL 中的 "<"，比如 findBySalaryLessThan(int max)；
		GreaterThan --- 等价于 SQL 中的">"，比如 findBySalaryGreaterThan(int min)；
		IsNull --- 等价于 SQL 中的 "is null"，比如 findByUsernameIsNull()；
		IsNotNull --- 等价于 SQL 中的 "is not null"，比如 findByUsernameIsNotNull()；
		NotNull --- 与 IsNotNull 等价；
		Like --- 等价于 SQL 中的 "like"，比如 findByUsernameLike(String user)；
		NotLike --- 等价于 SQL 中的 "not like"，比如 findByUsernameNotLike(String user)；
		OrderBy --- 等价于 SQL 中的 "order by"，比如 findByUsernameOrderBySalaryAsc(String user)；
		Not --- 等价于 SQL 中的 "！ ="，比如 findByUsernameNot(String user)；
		In --- 等价于 SQL 中的 "in"，比如 findByUsernameIn(Collection<String> userList) ，方法的参数可以是 Collection 类型，也可以是数组或者不定长参数；
		NotIn --- 等价于 SQL 中的 "not in"，比如 findByUsernameNotIn(Collection<String> userList) ，方法的参数可以是 Collection 类型，也可以是数组或者不定长参数
		*/
	}
	
	
}
