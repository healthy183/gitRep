package com.kang.batch2.ibmBlog.model;

import lombok.Data;

@Data
public class Bill implements java.io.Serializable {

	private Long id;
	private User user;
	private Double fees;
	private Double paidFees;
	private Double unpaidFees;
	private int payStatus;
	
	

}
