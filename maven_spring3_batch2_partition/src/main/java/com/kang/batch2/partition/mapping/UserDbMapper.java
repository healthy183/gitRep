package com.kang.batch2.partition.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kang.batch2.partition.model.User;

@Slf4j
public class UserDbMapper implements RowMapper<User> {

	
	/*@Value("#{stepExecutionContext[name]}")
	private String threadName;	*/
	
	public User mapRow(ResultSet rs, int i) throws SQLException {
		
		log.info("had readed db,now it is mapping to User.class which id is"+rs.getLong("ID"));
		
		User user = new User();
		user.setId(rs.getLong("u.id"));
		user.setName(rs.getString("u.name"));
		user.setAge(rs.getInt("u.age"));
		user.setBalance(rs.getDouble("u.balance"));
		return user;
	}

}
