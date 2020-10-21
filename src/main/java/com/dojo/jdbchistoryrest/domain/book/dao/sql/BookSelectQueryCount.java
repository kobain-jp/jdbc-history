package com.dojo.jdbchistoryrest.domain.book.dao.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.object.MappingSqlQuery;

public class BookSelectQueryCount extends MappingSqlQuery<Integer> {

	private static final String SQL_COUNT = "select count(*) as cnt from book";

	public BookSelectQueryCount(DataSource dataSource) {
		super(dataSource, SQL_COUNT);
		compile();
	}

	@Override
	protected Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
		return Integer.valueOf(rs.getInt("cnt"));
	}

}
