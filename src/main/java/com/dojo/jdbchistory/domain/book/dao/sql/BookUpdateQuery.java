package com.dojo.jdbchistory.domain.book.dao.sql;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import com.dojo.jdbchistory.domain.book.entity.Book;

public class BookUpdateQuery extends SqlUpdate {

	private static final String SQL_UPDATE = "update book set isbn=? ,title=? ,author=? ,release_date=? where book_id = ?";

	public BookUpdateQuery(DataSource dataSource) {
		super(dataSource, SQL_UPDATE);
		declareParameter(new SqlParameter(Types.NUMERIC));
		declareParameter(new SqlParameter(Types.NVARCHAR));
		declareParameter(new SqlParameter(Types.NVARCHAR));
		declareParameter(new SqlParameter(Types.DATE));
		declareParameter(new SqlParameter(Types.NUMERIC));
		compile();
	}

	public int update(Book book) {
		Object[] params = new Object[5];
		params[0] = book.getIsbn();
		params[1] = book.getTitle();
		params[2] = book.getAuthor();
		params[3] = book.getReleaseDate();
		params[4] = book.getBookId();
		return super.update(params);
	}

}
