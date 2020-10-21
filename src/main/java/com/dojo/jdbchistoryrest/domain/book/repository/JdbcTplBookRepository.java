package com.dojo.jdbchistoryrest.domain.book.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

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
	public int insert(Book book) {

		return jdbcTemplate.update("insert into book (isbn,title,author,release_date) values (?,?,?,?)",
				new Object[] { book.getIsbn(), book.getTitle(), book.getAuthor(), book.getReleaseDate() });

	}

	@Override
	public int update(Book book) {

		return jdbcTemplate.update("update book set isbn=? ,title=? ,author=? ,release_date=? where book_id = ?",
				new Object[] { book.getIsbn(), book.getTitle(), book.getAuthor(), book.getReleaseDate(),
						book.getBookId() });

	}

	@Override
	public int deleteById(long id) {

		return jdbcTemplate.update("delete from book where book_id = ?", new Object[] { Long.valueOf(id) });

	}

	@Override
	public Book save(Book book) {

		long bookId = book.getBookId();

		if (book.getBookId() == 0 || !existsById(book.getBookId())) {
			bookId = insertGetId(book);
		} else {
			update(book);
		}
		return this.findById(bookId).get();
	}

	@Override
	public int insertGetId(Book book) {

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(con -> {
			PreparedStatement ps = con.prepareStatement(
					"insert into book (isbn,title,author,release_date) values (?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, book.getIsbn());
			ps.setString(2, book.getTitle());
			ps.setString(3, book.getAuthor());
			ps.setDate(4, book.getReleaseDate());
			return ps;
		}, keyHolder);

		return (int) keyHolder.getKey();
	}

	@Override
	public boolean existsById(long id) {

		return this.findById((id)).isPresent();

	}

}
