package com.dojo.jdbchistoryrest.domain.book.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.dojo.jdbchistoryrest.domain.book.entity.Book;

public class BookDao {

	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String userName;
	@Value("${spring.datasource.password}")
	private String password;

	public List<Book> findAll() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Book> bookList = new ArrayList<Book>();

		try {
			Class.forName(driverClassName);
			con = DriverManager.getConnection(url, userName, password);
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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se2) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException se2) {
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return bookList;
	}

}
