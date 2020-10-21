package com.dojo.jdbchistoryrest.domain.book.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dojo.jdbchistoryrest.domain.book.entity.Book;

@Repository("namedPramJdbcTplBookRepository")
public class NamedParamJdbcTplBookRepository implements IBookRepository {

	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	public NamedParamJdbcTplBookRepository(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate =jdbcTemplate;
	}
	

	@Override
	public List<Book> findAll() {

		return jdbcTemplate.query("select * from book", new BeanPropertyRowMapper<Book>(Book.class));

	}

	@Override
	public Optional<Book> findById(long id) {

		SqlParameterSource params = new MapSqlParameterSource().addValue("book_id", Long.valueOf(id));

		List<Book> list = jdbcTemplate.query("select * from book where book_id = :book_id", params,
				new BeanPropertyRowMapper<Book>(Book.class));

		if (list.isEmpty()) {
			return Optional.empty();
		} else {
			return Optional.ofNullable(list.iterator().next());
		}

	}

	@Override
	public List<Book> findByTitleLike(String author) {

		SqlParameterSource params = new MapSqlParameterSource().addValue("author", author);

		return jdbcTemplate.query("select * from book where title like '%'||:author||'%'", params,
				new BeanPropertyRowMapper<Book>(Book.class));

	}

	@Override
	public int count() {

		return jdbcTemplate.queryForObject("select count(*) as cnt from book", new MapSqlParameterSource(),
				Integer.class);

	}

	@Override
	@Transactional
	public int create(Book book) {

		SqlParameterSource params = new MapSqlParameterSource().addValue("author", book.getAuthor())
				.addValue("isbn", book.getIsbn()).addValue("title", book.getTitle())
				.addValue("release_date", book.getReleaseDate());

		return jdbcTemplate.update(
				"insert into book (isbn,title,author,release_date) values (:isbn,:title,:author,:release_date)",
				params);

	}

	@Override
	@Transactional
	public int update(long id, Book book) {

		SqlParameterSource params = new MapSqlParameterSource().addValue("author", book.getAuthor())
				.addValue("isbn", book.getIsbn()).addValue("title", book.getTitle())
				.addValue("release_date", book.getReleaseDate()).addValue("book_id", book.getBookId());

		return jdbcTemplate.update(
				"update book set isbn=:isbn ,title=:title ,author=:author ,release_date=:release_date where book_id = :book_id",
				params);
	}

	@Override
	@Transactional
	public int delete(long id) {
		
		SqlParameterSource params = new MapSqlParameterSource().addValue("book_id", Long.valueOf(id));
		return jdbcTemplate.update("delete from book where book_id = :book_id", params);

	}
}
