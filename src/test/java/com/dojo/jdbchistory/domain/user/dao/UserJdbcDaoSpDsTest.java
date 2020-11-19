package com.dojo.jdbchistory.domain.user.dao;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;

import java.sql.Date;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.dojo.jdbchistory.domain.user.dao.IUserDao;
import com.dojo.jdbchistory.domain.user.dao.UserJdbcDaoSpDs;
import com.dojo.jdbchistory.domain.user.entity.User;

@JdbcTest
@Transactional
public class UserJdbcDaoSpDsTest {

	IUserDao it;

	@Autowired
	DataSource dataSource;

	@BeforeEach
	void setUp() throws Exception {
		it = new UserJdbcDaoSpDs();
		((JdbcDaoSupport) it).setDataSource(dataSource);
	}

	@Test
	public void testFindAll_ReturnListContainsAllRecord() {

		User user1 = new User();
		user1.setUserId(1);
		user1.setUserName("user1");
		user1.setBirthDay(Date.valueOf("2001-01-01"));

		User user2 = new User();
		user2.setUserId(2);
		user2.setUserName("user2");
		user2.setBirthDay(Date.valueOf("2002-02-02"));

		assertThat(it.findAll(), is(containsInAnyOrder(samePropertyValuesAs(user1), samePropertyValuesAs(user2))));
	}

	@Test
	public void testFindById_ReturnBook_IfHasResult() {
		User user1 = new User();
		user1.setUserId(1);
		user1.setUserName("user1");
		user1.setBirthDay(Date.valueOf("2001-01-01"));

		assertThat(it.findById(1).orElse(null), is(samePropertyValuesAs(user1)));
	}

	@Test
	public void testDelete() {
		assertThat(it.delete(2), is(1));
		assertThat(it.findById(2).orElse(null), is(nullValue()));
	}

	@Test
	public void testCreate() {
		User user1 = new User();
		user1.setUserId(3);
		user1.setUserName("user3");
		user1.setBirthDay(Date.valueOf("2003-03-03"));

		assertThat(it.create(user1), is(1));
		assertThat(it.findById(3).orElse(null), is(samePropertyValuesAs(user1)));

	}

	@Test
	public void testUpdate() {
		User user1 = new User();
		user1.setUserId(1);
		user1.setUserName("user11");
		user1.setBirthDay(Date.valueOf("2000-01-01"));
		assertThat(it.update(1, user1), is(1));
		assertThat(it.findById(1).orElse(null), is(samePropertyValuesAs(user1)));

	}
}
