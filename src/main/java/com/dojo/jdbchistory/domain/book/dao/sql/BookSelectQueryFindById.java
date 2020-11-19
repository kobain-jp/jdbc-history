package com.dojo.jdbchistory.domain.book.dao.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import com.dojo.jdbchistory.domain.book.entity.Book;

public class BookSelectQueryFindById extends MappingSqlQuery<Book> {

	private static final String SQL_FIND_BY_ID = "select * from book where book_id = ?";
	
	public BookSelectQueryFindById(DataSource dataSource) {
		super(dataSource, SQL_FIND_BY_ID);
		declareParameter(new SqlParameter(Types.NUMERIC));
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
