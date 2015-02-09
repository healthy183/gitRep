package com.kang.boot.db.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer  implements java.io.Serializable {

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
