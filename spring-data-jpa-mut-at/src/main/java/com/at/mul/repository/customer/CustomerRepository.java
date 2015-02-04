package com.at.mul.repository.customer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.at.mul.domain.customer.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	List<Customer> findByName(String string);

	List<Customer> findByNameLike(String string);

	Customer findByIdAndName(int i, String string);
	
	@Query("SELECT new Customer(id,name) FROM Customer WHERE id = ?1 ")
	Customer queryThisById(int id);

	@Query("SELECT c FROM Customer c ")
	List<Customer> queryAll();

	@Query("SELECT new Customer(id,name) FROM Customer WHERE id = :id ")
	Customer queryByParam(@Param("id") int id);

	@Modifying
	@Query("UPDATE Customer SET name = :name WHERE id = :id ")
	int queryUpdate(@Param("id") int id,@Param("name") String name);

	@Query(value="select id,name,age from customer ",nativeQuery=true)
	List<Customer> nativeQuery();
}
