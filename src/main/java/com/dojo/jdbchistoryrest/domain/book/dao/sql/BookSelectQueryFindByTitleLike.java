package com.dojo.jdbchistoryrest.domain.book.dao.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import com.dojo.jdbchistoryrest.domain.book.entity.Book;

public class BookSelectQueryFindByTitleLike extends MappingSqlQuery<Book> {

	private static final String SQL_FIND_BY_TITLE_LIKE = "select * from book where title like '%'||?||'%'";

	public BookSelectQueryFindByTitleLike(DataSource dataSource) {
		super(dataSource, SQL_FIND_BY_TITLE_LIKE);
		declareParameter(new SqlParameter(Types.NVARCHAR));
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
