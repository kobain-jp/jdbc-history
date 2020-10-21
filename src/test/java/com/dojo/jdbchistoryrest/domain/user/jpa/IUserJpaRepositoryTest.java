package com.dojo.jdbchistoryrest.domain.user.jpa;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.dojo.jdbchistoryrest.domain.user.entity.User;

@DataJpaTest
public class IUserJpaRepositoryTest {

	@Autowired
	IUserJpaRepository it;

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

		//assertThat(it.findAll(), is(containsInAnyOrder(samePropertyValuesAs(user1), samePropertyValuesAs(user2))));
	}

	@Test
	public void testFindById_ReturnBook_IfHasResult() {
		User user1 = new User();
		user1.setUserId(1);
		user1.setUserName("user1");
		user1.setBirthDay(Date.valueOf("2001-01-01"));

		//assertThat(it.findById(1L).orElse(null), is(samePropertyValuesAs(user1)));
	}
	
	@Test
	public void testDelete() {
		//assertThat(it.deleteById(2L), is(1));
		//assertThat(it.findById(2L).orElse(null), is(nullValue()));
	}

	@Test
	public void testCreate() {
		User user1 = new User();
		user1.setUserId(3);
		user1.setUserName("user3");
		user1.setBirthDay(Date.valueOf("2003-03-03"));

		//assertThat(it.create(user1), is(samePropertyValuesAs(user1));
		//assertThat(it.findById(3L).orElse(null), is(samePropertyValuesAs(user1)));

	}

	@Test
	public void testUpdate() {
		User user1 = new User();
		user1.setUserId(1);
		user1.setUserName("user11");
		user1.setBirthDay(Date.valueOf("2000-01-01"));
		//assertThat(it.update(user1), is(samePropertyValuesAs(user1));
		//assertThat(it.findById(1L).orElse(null), is(samePropertyValuesAs(user1)));

	}
}
