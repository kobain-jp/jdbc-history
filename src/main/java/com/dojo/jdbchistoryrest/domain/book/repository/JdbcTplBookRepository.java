package com.dojo.jdbchistoryrest.domain.book.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dojo.jdbchistoryrest.domain.book.entity.Book;

@Repository("jdbcTplBookRepository")
public class JdbcTplBookRepository implements IBookRepository {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcTplBookRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Book> findAll() {

		return jdbcTemplate.query("select * from book", new BeanPropertyRowMapper<Book>(Book.class));

	}

	@Override
	public Optional<Book> findById(long id) {

		List<Book> list = jdbcTemplate.query("select * from book where book_id = ?", new Object[] { Long.valueOf(id) },
				new BeanPropertyRowMapper<Book>(Book.class));

		if (list.isEmpty()) {
			return Optional.empty();
		} else {
			return Optional.ofNullable(list.iterator().next());
		}

	}

	@Override
	public List<Book> findByTitleLike(String author) {

		return jdbcTemplate.query("select * from book where title like '%'||?||'%'", new Object[] { author },
				new BeanPropertyRowMapper<Book>(Book.class));

	}

	@Override
	public int count() {

		return jdbcTemplate.queryForObject("select count(*) as cnt from book", Integer.class);

	}

	@Override
	@Transactional
	public int create(Book book) {

		return jdbcTemplate.update("insert into book (isbn,title,author,release_date) values (?,?,?,?)",
				new Object[] { book.getIsbn(), book.getTitle(), book.getAuthor(), book.getReleaseDate() });

	}

	@Override
	@Transactional
	public int update(long id, Book book) {

		return jdbcTemplate.update("update book set isbn=? ,title=? ,author=? ,release_date=? where book_id = ?",
				new Object[] { book.getIsbn(), book.getTitle(), book.getAuthor(), book.getReleaseDate(),
						book.getBookId() });

	}

	@Override
	@Transactional
	public int delete(long id) {

		return jdbcTemplate.update("delete from book where book_id = ?", new Object[] { Long.valueOf(id) });

	}

}
