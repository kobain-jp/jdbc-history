package com.dojo.jdbchistoryrest.domain.user.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dojo.jdbchistoryrest.domain.user.entity.User;

public class UserDao implements IUserDao {

	private String driverClassName;
	private String url;
	private String userName;
	private String password;

	public UserDao(String driverClassName, String url, String userName, String password) {
		this.driverClassName = driverClassName;
		this.url = url;
		this.userName = userName;
		this.password = password;
	}

	@Override
	public List<User> findAll() {

		List<User> list = new ArrayList<User>();
		User user1 = new User();
		user1.setUserId(3);
		user1.setUserName("user3");
		user1.setBirthDay(Date.valueOf("2003-03-03"));
		list.add(user1);

		return list;
	}

	@Override
	public Optional<User> findById(long id) {
		// TODO Auto-generated method stub

		User user1 = new User();
		user1.setUserId(3);
		user1.setUserName("user3");
		user1.setBirthDay(Date.valueOf("2003-03-03"));

		return Optional.ofNullable(user1);
	}

	@Override
	public int count() {
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
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
