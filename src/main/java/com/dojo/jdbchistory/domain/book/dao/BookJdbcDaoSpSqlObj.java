package com.dojo.jdbchistory.domain.book.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.dojo.jdbchistory.domain.book.dao.sql.BookDeleteQuery;
import com.dojo.jdbchistory.domain.book.dao.sql.BookInsertQuery;
import com.dojo.jdbchistory.domain.book.dao.sql.BookSelectQueryCount;
import com.dojo.jdbchistory.domain.book.dao.sql.BookSelectQueryFindAll;
import com.dojo.jdbchistory.domain.book.dao.sql.BookSelectQueryFindById;
import com.dojo.jdbchistory.domain.book.dao.sql.BookSelectQueryFindByTitleLike;
import com.dojo.jdbchistory.domain.book.dao.sql.BookUpdateQuery;
import com.dojo.jdbchistory.domain.book.entity.Book;

public class BookJdbcDaoSpSqlObj extends JdbcDaoSupport implements IBook {

	@Override
	public List<Book> findAll() {
		return new BookSelectQueryFindAll(getDataSource()).execute();
	}

	@Override
	public Optional<Book> findById(long id) {

		List<Book> bookList = new BookSelectQueryFindById(getDataSource()).execute(id);

		if (bookList.isEmpty()) {
			return Optional.empty();
		} else {
			return Optional.of(bookList.iterator().next());
		}
	}

	@Override
	public List<Book> findByTitleLike(String author) {
		return new BookSelectQueryFindByTitleLike(getDataSource()).execute(author);
	}

	@Override
	public int count() {
		return new BookSelectQueryCount(getDataSource()).execute().iterator().next();
	}

	@Override
	public int insert(Book book) {
		return new BookInsertQuery(getDataSource()).update(book);
	}

	@Override
	public int update(Book book) {
		return new BookUpdateQuery(getDataSource()).update(book);
	}

	@Override
	public int deleteById(long id) {
		return new BookDeleteQuery(getDataSource()).update(id);

	}

}
