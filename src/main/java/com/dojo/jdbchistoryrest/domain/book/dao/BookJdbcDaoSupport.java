package com.dojo.jdbchistoryrest.domain.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import com.dojo.jdbchistoryrest.domain.book.entity.Book;

@Component
public class BookJdbcDaoSupport extends JdbcDaoSupport {

	public List<Book> findAll() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Book> bookList = new ArrayList<Book>();

		try {
			con = getConnection();
			ps = con.prepareStatement("select * from book");
			rs = ps.executeQuery();

			while (rs.next()) {
				Book book = new Book();
				book.setBookId(rs.getLong("book_id"));
				book.setTitle(rs.getString("title"));
				book.setIsbn(rs.getLong("isbn"));
				book.setAuthor(rs.getString("author"));
				book.setReleaseDate(rs.getDate("release_date"));
				bookList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseConnection(con);
		}
		return bookList;
	}

	public List<Book> findAllByJdbcTemplateRowMapper() {

		return getJdbcTemplate().query("select * from book", new RowMapper<Book>() {

			public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
				Book book = new Book();
				book.setBookId(rs.getLong("book_id"));
				book.setTitle(rs.getString("title"));
				book.setIsbn(rs.getLong("isbn"));
				book.setAuthor(rs.getString("author"));
				book.setReleaseDate(rs.getDate("release_date"));
				return book;

			}
		});

	}

	public List<Book> findAllByJdbcTemplateRowMapperRamda() {

		return getJdbcTemplate().query("select * from book",

				(ResultSet rs, int rowNum) -> {
					Book book = new Book();
					book.setBookId(rs.getLong("book_id"));
					book.setTitle(rs.getString("title"));
					book.setIsbn(rs.getLong("isbn"));
					book.setAuthor(rs.getString("author"));
					book.setReleaseDate(rs.getDate("release_date"));
					return book;

				});

	}

	public List<Book> findAllByJdbcTemplate() {

		return getJdbcTemplate().query("select * from book", new BeanPropertyRowMapper<Book>(Book.class));

	}
}
