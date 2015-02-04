package com.kang.batch3.run;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.kang.batch3.model.Person;

@ComponentScan("com.kang")
@EnableAutoConfiguration
public class RunAllConfigurationApplication {

/*create table people(
	first_name varchar(10),
  last_name varchar(10)
)*/
	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(
				RunAllConfigurationApplication.class, args);

		/*List<Person> results = ctx.getBean(JdbcTemplate.class).query(
				"SELECT first_name, last_name FROM people",
				new RowMapper<Person>() {
					
					public Person mapRow(ResultSet rs, int row)
							throws SQLException {
						return new Person(rs.getString(1), rs.getString(2));
					}
				});

		for (Person person : results) {
			System.out.println("Found <" + person + "> in the database.");
		}*/

	}

}
