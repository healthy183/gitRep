package com.kang.batch2.ibmBlog.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.jdbc.core.RowMapper;

import com.kang.batch2.ibmBlog.model.User;

@Slf4j
public class UserDbMapper implements RowMapper<User> {

	public User mapRow(ResultSet rs, int i) throws SQLException {
		
		log.info("had readed db,now it is mapping to User.class which id is"+rs.getLong("ID"));
		
		User user = new User();
		user.setId(rs.getLong("ID"));
		user.setName(rs.getString("NAME"));
		user.setAge(rs.getInt("AGE"));
		user.setBalance(rs.getDouble("BALANCE"));
		return user;
	}

}
