package com.dojo.jdbchistoryrest.domain.user.dao;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import com.dojo.jdbchistoryrest.domain.book.entity.Book;

@Component
public class UserJdbcDaoSupport extends JdbcDaoSupport {
	
	public List<Book> findAll() {
		return null;
	}
}
