package com.dojo.jdbchistory.domain.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.dojo.jdbchistory.domain.book.entity.Book;


public class BookJdbcDaoSpDs extends JdbcDaoSupport implements IBook {

	@Override
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

	@Override
	public Optional<Book> findById(long id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			ps = con.prepareStatement("select * from book where book_id = ?");
			ps.setLong(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				Book book = new Book();
				book.setBookId(rs.getLong("book_id"));
				book.setTitle(rs.getString("title"));
				book.setIsbn(rs.getLong("isbn"));
				book.setAuthor(rs.getString("author"));
				book.setReleaseDate(rs.getDate("release_date"));
				return Optional.of(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseConnection(con);
		}
		return Optional.empty();
	}

	@Override
	public List<Book> findByTitleLike(String author) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Book> bookList = new ArrayList<Book>();

		try {
			con = getConnection();
			ps = con.prepareStatement("select * from book where title like '%'||?||'%'");
			ps.setString(1, author);
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

	@Override
	public int count() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			con = getConnection();
			ps = con.prepareStatement("select count(*) as cnt from book");
			rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseConnection(con);
		}
		return 0;
	}

	@Override
	public int insert(Book book) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = getConnection();
			con.setAutoCommit(false);

			ps = con.prepareStatement("insert into book (isbn,title,author,release_date) values (?,?,?,?)");
			ps.setLong(1, book.getIsbn());
			ps.setString(2, book.getTitle());
			ps.setString(3, book.getAuthor());
			ps.setDate(4, book.getReleaseDate());
			int createdCount = ps.executeUpdate();

			con.commit();

			return createdCount;

		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			releaseConnection(con);
		}
		return 0;
	}

	@Override
	public int update(Book book) {
		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = getConnection();
			con.setAutoCommit(false);

			ps = con.prepareStatement("update book set isbn=? ,title=? ,author=? ,release_date=? where book_id = ?");
			ps.setLong(1, book.getIsbn());
			ps.setString(2, book.getTitle());
			ps.setString(3, book.getAuthor());
			ps.setDate(4, book.getReleaseDate());
			ps.setLong(5, book.getBookId());
			int updatedCount = ps.executeUpdate();

			con.commit();

			return updatedCount;

		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			releaseConnection(con);
		}
		return 0;
	}

	@Override
	public int deleteById(long id) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = getConnection();
			con.setAutoCommit(false);

			ps = con.prepareStatement("delete from book where book_id = ?");
			ps.setLong(1, id);

			int deletedCount = ps.executeUpdate();
			con.commit();

			return deletedCount;

		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			releaseConnection(con);
		}
		return 0;

	}

}
