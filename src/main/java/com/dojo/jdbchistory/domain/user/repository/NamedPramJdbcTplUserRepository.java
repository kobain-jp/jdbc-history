package com.dojo.jdbchistory.domain.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dojo.jdbchistory.domain.user.entity.User;

@Repository("namedPramJdbcTplUserRepository")
public class NamedPramJdbcTplUserRepository implements IUserRepositoty {

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public NamedPramJdbcTplUserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<User> findAll() {
		return jdbcTemplate.query("select * from user", new BeanPropertyRowMapper<User>(User.class));
	}

	@Override
	public void count() {
		// TODO Auto-generated method stub
		
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

	@Override
	public int insertGetId(User user) {
		// TODO Auto-generated method stub
		return 0;
	}



}
