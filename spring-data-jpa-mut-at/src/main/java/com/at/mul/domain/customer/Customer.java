package com.at.mul.domain.customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@Data
@EqualsAndHashCode(exclude = { "id" })
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name="Customer.byNameQuery",query = "from Customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "age", nullable = false)
	private Integer age;

	public Customer(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
	

}
