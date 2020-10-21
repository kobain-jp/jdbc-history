package com.dojo.jdbchistoryrest.domain.user.dao.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.object.MappingSqlQuery;

import com.dojo.jdbchistoryrest.domain.user.entity.User;

public class UserSelectQueryFindAll extends MappingSqlQuery<User> {
	
	private static final String SQL_FIND_ALL = "select * from User";
	
	public UserSelectQueryFindAll(DataSource dataSource) {
		super(dataSource, SQL_FIND_ALL);
		compile();
	}

	@Override
	protected User mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		User user = new User();
		user.setUserId(rs.getLong("user_id"));
		user.setUserName(rs.getString("user_name"));
		user.setBirthDay(rs.getDate("birthday"));
		return user;
	}

}
