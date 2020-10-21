package com.dojo.jdbchistoryrest.domain.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.dojo.jdbchistoryrest.domain.user.entity.User;

public class UserJdbcDaoSpSqlObj extends JdbcDaoSupport implements IUserDao {

	@Override
	public List<User> findAll() {

		return getJdbcTemplate().query("select * from user", new RowMapper<User>() {
			
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setUserId(rs.getLong("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setBirthDay(rs.getDate("birthday"));
				return user;
			}
		});

	}

	@Override
	public Optional<User> findById(long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public int create(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(long id, User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}
}
