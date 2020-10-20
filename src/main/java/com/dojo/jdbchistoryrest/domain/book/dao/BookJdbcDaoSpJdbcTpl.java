package com.dojo.jdbchistoryrest.domain.book.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.dojo.jdbchistoryrest.domain.book.entity.Book;

public class BookJdbcDaoSpJdbcTpl extends JdbcDaoSupport implements IBook {

	@Override
	public List<Book> findAll() {

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

	@Override
	public Optional<Book> findById(long id) {

		List<Book> list = getJdbcTemplate().query("select * from book where book_id = ?",
				new Object[] { Long.valueOf(id) }, (ResultSet rs, int rowNum) -> {
					Book book = new Book();
					book.setBookId(rs.getLong("book_id"));
					book.setTitle(rs.getString("title"));
					book.setIsbn(rs.getLong("isbn"));
					book.setAuthor(rs.getString("author"));
					book.setReleaseDate(rs.getDate("release_date"));
					return book;

				});

		if (list.isEmpty()) {
			return Optional.empty();
		} else {
			return Optional.ofNullable(list.iterator().next());
		}

	}

	@Override
	public List<Book> findByTitleLike(String author) {

		return getJdbcTemplate().query("select * from book where title like '%'||?||'%'", new Object[] { author },
				new BeanPropertyRowMapper<Book>(Book.class));

	}

	@Override
	public int count() {

		return getJdbcTemplate().queryForObject("select count(*) as cnt from book", Integer.class);

	}

	@Override
	@Transactional
	public int create(Book book) {

		return getJdbcTemplate().update("insert into book (isbn,title,author,release_date) values (?,?,?,?)",
				new Object[] { book.getIsbn(), book.getTitle(), book.getAuthor(), book.getReleaseDate() });

	}

	@Override
	@Transactional
	public int update(long id, Book book) {

		return getJdbcTemplate().update("update book set isbn=? ,title=? ,author=? ,release_date=? where book_id = ?",
				new Object[] { book.getIsbn(), book.getTitle(), book.getAuthor(), book.getReleaseDate(),
						book.getBookId() });

	}

	@Override
	@Transactional
	public int delete(long id) {

		return getJdbcTemplate().update("delete from book where book_id = ?", new Object[] { Long.valueOf(id) });

	}

}
