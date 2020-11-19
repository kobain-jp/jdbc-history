package com.dojo.jdbchistory.domain.book.dao.sql;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class BookDeleteQuery extends SqlUpdate {

	private static final String SQL_DELETE_BY_ID = "delete from book where book_id = ?";

	public BookDeleteQuery(DataSource dataSource) {
		super(dataSource, SQL_DELETE_BY_ID);
		declareParameter(new SqlParameter(Types.NUMERIC));
		compile();
	}

}
