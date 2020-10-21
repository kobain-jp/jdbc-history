package com.dojo.jdbchistoryrest.domain.user.repository;

import java.util.List;
import java.util.Optional;

import com.dojo.jdbchistoryrest.domain.user.entity.User;

public interface IUserReposiroty {

	public List<User> findAll();

	public Optional<User> findById(long id);

	public void count();

	public int delete(long id);

	public int create(User user);

	public int update(long id, User user);
	
	public int insertGetId(User user);
}
