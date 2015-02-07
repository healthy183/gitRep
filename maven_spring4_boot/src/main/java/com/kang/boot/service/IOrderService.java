package com.kang.boot.service;

import java.util.List;

import com.kang.boot.po.Order;

public interface IOrderService {

	List<Order> findAll();

	Order saveOrder();

	Order updateOrder();

	Order deleteOrder(int id);

	List<Order> findAllBySql();

}
