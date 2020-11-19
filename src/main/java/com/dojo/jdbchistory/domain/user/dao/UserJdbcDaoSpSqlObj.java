package com.dojo.jdbchistory.domain.user.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.dojo.jdbchistory.domain.user.dao.sql.UserSelectQueryFindAll;
import com.dojo.jdbchistory.domain.user.entity.User;

public class UserJdbcDaoSpSqlObj extends JdbcDaoSupport implements IUserDao {

	@Override
	public List<User> findAll() {

		return new UserSelectQueryFindAll(getDataSource()).execute();

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
