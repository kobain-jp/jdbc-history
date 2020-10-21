package com.dojo.jdbchistoryrest.domain.book.dao.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.object.MappingSqlQuery;

import com.dojo.jdbchistoryrest.domain.book.entity.Book;

public class BookSelectQueryFindAll extends MappingSqlQuery<Book> {
	
	private static final String SQL_FIND_ALL = "select * from book";
	
	public BookSelectQueryFindAll(DataSource dataSource) {
		super(dataSource, SQL_FIND_ALL);
		compile();
	}

	@Override
	protected Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Book book = new Book();
		book.setBookId(rs.getLong("book_id"));
		book.setTitle(rs.getString("title"));
		book.setIsbn(rs.getLong("isbn"));
		book.setAuthor(rs.getString("author"));
		book.setReleaseDate(rs.getDate("release_date"));
		return book;
	}

}
