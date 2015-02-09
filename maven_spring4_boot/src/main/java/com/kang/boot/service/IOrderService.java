package com.kang.boot.service;

import java.util.List;

import com.kang.boot.dao.po.Order;
import com.kang.boot.db.po.Customer;

public interface IOrderService {

	List<Order> findAll();

	Order saveOrder();

	Order updateOrder();

	Order deleteOrder(int id);

	List<Order> findAllBySql();

	List<Customer> findCustomer();

}
