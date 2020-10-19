package com.dojo.jdbchistoryrest.domain.user.repository;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dojo.jdbchistoryrest.domain.user.entity.User;

@Repository("dataSourceUserRepository")
public class DataSourceUserRepository implements IUserReposiroty {
	
	private DataSource dataSource;

	@Autowired
	public DataSourceUserRepository(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<User> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
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

}
