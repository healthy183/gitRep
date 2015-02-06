package com.kang.boot.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@EqualsAndHashCode(exclude = { "id" })
@NoArgsConstructor
@AllArgsConstructor
public class Order  implements java.io.Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "code", nullable = false)
	private Integer code;

	@Column(name = "quantity", nullable = false)
	private Integer quantity;

	public Order(Integer code, Integer quantity) {
		super();
		this.code = code;
		this.quantity = quantity;
	}
	
	

}
