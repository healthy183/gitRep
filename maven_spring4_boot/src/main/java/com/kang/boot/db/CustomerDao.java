package com.kang.boot.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kang.boot.db.po.Customer;

public interface CustomerDao extends JpaRepository<Customer, Integer>,CustomerDaoCustomer{

}
