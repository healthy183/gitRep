package com.kang.boot.model;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class User implements Serializable {

	 private Long id;  
	 private String name;  
	
}
