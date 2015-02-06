package com.kang.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kang.boot.dao.IOrderDao;
import com.kang.boot.po.Order;


@Service
@Transactional
public class OrderServiceImpl implements IOrderService {
	
	//@Autowired
	//private IOrderService OrderService;
	@Autowired
	private IOrderDao orderDao;
	
	public List<Order> findAll() {
		//return OrderService.findAll();
		
		/*List<Order> olist = new ArrayList<Order>();
		olist.add(new Order(2,3));*/
		
		return orderDao.findAll();
	}

}
