package com.kang.boot.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kang.boot.dao.OrderDao;
import com.kang.boot.dao.po.Order;
import com.kang.boot.db.CustomerDao;
import com.kang.boot.db.po.Customer;


@Service
@Transactional
public class OrderServiceImpl implements IOrderService {
	
	//@Autowired
	//private IOrderService OrderService;
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	
	
	public List<Order> findAll() {
		//return OrderService.findAll();
		
		/*List<Order> olist = new ArrayList<Order>();
		olist.add(new Order(2,3));*/
		
		return orderDao.findAll();
	}

	public Order saveOrder() {
		
		
		Order order = new Order();
		order.setCode(8080);
		order.setQuantity(9090);
		
		orderDao.save(order);
		
		
		//return orderDao.findByCode(8080);
		return orderDao.getOne(order.getId());
	}

	public Order deleteOrder(int id) {
		
		orderDao.delete(id);
		
		//return orderDao.findOne(id);
		//EntityNotFoundException
		return orderDao.getOne(id);
	}
	
	public Order updateOrder() {
		
		Order order = orderDao.getOne(7);
		
		int i = new Random().nextInt(999);
		
		order.setCode(i);
		
		orderDao.saveAndFlush(order);
		
		//exception!
		//return orderDao.findByCode(11114);
		//return  orderDao.getOne(7);
		Order orders = orderDao.getOne(order.getId());
		
		return	orders;
		//return null;
		/***
		 * 
		 * <html>
		
			<body>
				<h1>Whitelabel Error Page</h1>
				<p>This application has no explicit mapping for /error, so you are seeing this as a fallback.</p>
				<div id='created'>Fri Feb 06 16:14:45 CST 2015</div>
				<div>There was an unexpected error (type=Internal Server Error, status=500).</div>
				<div>
					Could not write content: No serializer found for class org.hibernate.proxy.pojo.javassist.JavassistLazyInitializer 
					and no properties discovered to create BeanSerializer 
					(to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS) )
					 (through reference chain: com.kang.boot.po.Order_$$_jvstede_0[&quot;handler&quot;]); 
					 nested exception is com.fasterxml.jackson.databind.JsonMappingException:
					  No serializer found for class org.hibernate.proxy.pojo.javassist.JavassistLazyInitializer 
					  and no properties discovered to create BeanSerializer 
					  (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS) ) 
					  (through reference chain: com.kang.boot.po.Order_$$_jvstede_0[&quot;handler&quot;])
				  </div>
			</body>
		</html>
		*/
		
		
		
	}

	public List<Order> findAllBySql() {
		
		return orderDao.findAllBySql();
		//List<Object[]> objs  = em.createNativeQuery("select * from orders").getResultList();
		//return null;
	//return null;
	}

	public List<Customer> findCustomer() {
		
		return customerDao.findAll();
	}

	//@Autowired
	//@Qualifier("orderEntityManager")
	//private EntityManager em;
	

}
