package com.dojo.jdbchistoryrest.domain.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import com.dojo.jdbchistoryrest.domain.book.entity.Book;

@Component
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
