package com.dojo.jdbchistoryrest.domain.user.dao;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.dojo.jdbchistoryrest.domain.book.entity.Book;

public class UserJdbcDaoSupport extends JdbcDaoSupport {
	
	public List<Book> findAll() {
		return null;
	}
	
	public List<Book> findAllByJdbcTemplateRowMapper() {

		return null;

	}

	public List<Book> findAllByJdbcTemplateRowMapperRamda() {

		return null;

	}

	public List<Book> findAllByJdbcTemplate() {

		return null;

	}
}
