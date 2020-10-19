package com.dojo.jdbchistoryrest.domain.user.dao;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Date;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dojo.jdbchistoryrest.domain.user.entity.User;

class UserJdbcDaoSupportTest {
	@Autowired
	DataSource dataSource;

	UserJdbcDaoSupport it;

	@BeforeEach
	void setUp() throws Exception {
		it = new UserJdbcDaoSupport();
		it.setDataSource(dataSource);
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

}
