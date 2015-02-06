package com.kang.boot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kang.boot.po.Order;

public interface IOrderDao extends JpaRepository<Order, Integer> {

	
	
	
}
