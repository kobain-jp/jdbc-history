package com.dojo.jdbchistoryrest.domain.user.repository;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import com.dojo.jdbchistoryrest.domain.user.entity.User;

@JdbcTest
@Transactional
public class NamedPramJdbcTplUserRepositoryTest {

	IUserReposiroty it;

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@BeforeEach
	void setUp() throws Exception {
		it = new NamedPramJdbcTplUserRepository(namedParameterJdbcTemplate);
	}

	@Test
	public void testDelete() {
		assertThat(it.delete(2), is(1));
		assertThat(it.findById(2).orElse(null), is(nullValue()));
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
	public void testCreate() {
		User user1 = new User();
		user1.setUserId(3);
		user1.setUserName("user3");
		user1.setBirthDay(Date.valueOf("2003-03-03"));

		int beforeCount = JdbcTestUtils.countRowsInTable(jdbcTemplate, "user");

		assertThat(it.create(user1), is(1));

		int generatedId = jdbcTemplate.queryForObject("Select max(user_id) from user", Integer.class);
		user1.setUserId(generatedId);

		assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "user"), is(beforeCount + 1));
		assertThat(it.findById(generatedId).orElse(null), is(samePropertyValuesAs(user1)));

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
