package com.kang.batch2.ibmBlog.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.jdbc.core.RowMapper;

import com.kang.batch2.ibmBlog.model.Bill;
import com.kang.batch2.ibmBlog.model.User;

@Slf4j
public class BillDbMapper  implements RowMapper<Bill> {

	public Bill mapRow(ResultSet rs, int i) throws SQLException {
		
		log.info("had readed db,now it is mapping to Bill.class and User.class"
				+ " which user.id is"+rs.getLong("USER_ID") +" "
						+ "and bill.id is "+rs.getLong("ID"));
		
		Bill b = new Bill();
		b.setId(rs.getLong("ID"));
		User u = new User();
		u.setId(rs.getLong("USER_ID"));
		u.setName(rs.getString("NAME"));
		u.setBalance(rs.getDouble("BALANCE"));
		b.setUser(u);
		b.setFees(rs.getDouble("FEES"));
		b.setPaidFees(rs.getDouble("PAID_FEES"));
		b.setUnpaidFees(rs.getDouble("UNPAID_FEES"));
		b.setPayStatus(rs.getInt("PAY_STATUS"));
		return b;
	}

}
