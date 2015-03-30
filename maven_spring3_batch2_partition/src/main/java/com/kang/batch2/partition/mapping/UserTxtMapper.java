package com.kang.batch2.partition.mapping;

import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.kang.batch2.partition.model.User;

@Slf4j
public class UserTxtMapper implements FieldSetMapper<User> {
	
	public User mapFieldSet(FieldSet fieldSet) throws BindException {
		
		User user = new User();
		user.setId(fieldSet.readLong(0));
		user.setName(fieldSet.readString(1));
		user.setAge(fieldSet.readInt(2));
		user.setBalance(fieldSet.readDouble(3));
		
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
		
	}
	
	

}
