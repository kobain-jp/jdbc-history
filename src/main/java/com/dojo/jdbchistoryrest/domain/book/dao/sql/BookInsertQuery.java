package com.dojo.jdbchistoryrest.domain.book.dao.sql;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import com.dojo.jdbchistoryrest.domain.book.entity.Book;

public class BookInsertQuery extends SqlUpdate {

	private static final String SQL_INSERT = "insert into book (isbn,title,author,release_date) values (?,?,?,?)";

	public BookInsertQuery(DataSource dataSource) {
		super(dataSource, SQL_INSERT);
		declareParameter(new SqlParameter(Types.NUMERIC));
		declareParameter(new SqlParameter(Types.NVARCHAR));
		declareParameter(new SqlParameter(Types.NVARCHAR));
		declareParameter(new SqlParameter(Types.DATE));
		compile();
	}

	public int update(Book book) {
		Object[] params = new Object[4];
		params[0] = book.getIsbn();
		params[1] = book.getTitle();
		params[2] = book.getAuthor();
		params[3] = book.getReleaseDate();
		
		return super.update(params);
	}

}
