package com.dojo.jdbchistoryrest.domain.book.dao;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.test.jdbc.JdbcTestUtils;

import com.dojo.jdbchistoryrest.domain.book.entity.Book;

@JdbcTest
public class BookJdbcDaoSpJdbcTplTest {

	IBook it;

	@Autowired
	DataSource dataSource;
	@Autowired
	JdbcTemplate jdbcTemplate;

	@BeforeEach
	void setUp() throws Exception {
		it = new BookJdbcDaoSpJdbcTpl();
		((JdbcDaoSupport) it).setDataSource(dataSource);
	}

	@Test
	public void testCount() {
		assertThat(it.count(), is(JdbcTestUtils.countRowsInTable(jdbcTemplate, "Book")));
	}

	@Test
	public void testFindAll() {

		List<Book> bookList = new ArrayList<Book>();
		Book book1 = new Book();
		book1.setBookId(1);
		book1.setTitle("SLAM DUNK 1");
		book1.setIsbn(9784088716114L);
		book1.setAuthor("井上雄彦");
		book1.setReleaseDate(Date.valueOf("1991-02-08"));
		bookList.add(book1);

		Book book2 = new Book();
		book2.setBookId(2);
		book2.setTitle("SLAM DUNK 2");
		book2.setIsbn(9784088716121L);
		book2.setAuthor("井上雄彦");
		book2.setReleaseDate(Date.valueOf("1991-06-10"));
		bookList.add(book2);

		Book book3 = new Book();
		book3.setBookId(3);
		book3.setTitle("リアル 1");
		book3.setIsbn(9784088761435L);
		book3.setAuthor("井上雄彦");
		book3.setReleaseDate(Date.valueOf("2001-3-19"));
		bookList.add(book3);

		assertThat(it.findAll(), is(containsInAnyOrder(samePropertyValuesAs(book1), samePropertyValuesAs(book2),
				samePropertyValuesAs(book3))));
	}

	@Test
	public void testFindById_ReturnNull_IfNotMatch() {
		assertThat(it.findById(-1).orElse(null), is(nullValue()));
	}

	@Test
	public void testFindById_ReturnBook_IfMatched() {
		Book book = new Book();
		book.setBookId(1);
		book.setTitle("SLAM DUNK 1");
		book.setIsbn(9784088716114L);
		book.setAuthor("井上雄彦");
		book.setReleaseDate(Date.valueOf("1991-02-08"));
		assertThat(it.findById(1).orElse(null), is(samePropertyValuesAs(book)));
	}

	@Test
	public void testFindByTitleLike_ReturnBooks_IfMatched() {
		List<Book> bookList = new ArrayList<Book>();
		Book book1 = new Book();
		book1.setBookId(1);
		book1.setTitle("SLAM DUNK 1");
		book1.setIsbn(9784088716114L);
		book1.setAuthor("井上雄彦");
		book1.setReleaseDate(Date.valueOf("1991-02-08"));
		bookList.add(book1);

		Book book2 = new Book();
		book2.setBookId(2);
		book2.setTitle("SLAM DUNK 2");
		book2.setIsbn(9784088716121L);
		book2.setAuthor("井上雄彦");
		book2.setReleaseDate(Date.valueOf("1991-06-10"));
		bookList.add(book2);

		assertThat(it.findByTitleLike("SLAM DUNK"),
				is(containsInAnyOrder(samePropertyValuesAs(book1), samePropertyValuesAs(book2))));
	}

	@Test
	public void testInsert() {
		Book book = new Book();
		book.setBookId(4);
		book.setTitle("SLAM DUNK 3");
		book.setIsbn(9784088716138L);
		book.setAuthor("井上雄彦");
		book.setReleaseDate(Date.valueOf("1991-07-10"));

		int beforeCount = JdbcTestUtils.countRowsInTable(jdbcTemplate, "book");

		assertThat(it.insert(book), is(1));

		int generatedId = jdbcTemplate.queryForObject("Select max(book_id) from book", Integer.class);
		book.setBookId(generatedId);

		assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "book"), is(beforeCount + 1));
		assertThat(it.findById(generatedId).orElse(null), is(samePropertyValuesAs(book)));

	}

	@Test
	public void testUpdate() {
		Book book = new Book();
		book.setBookId(1);
		book.setTitle("スラムダンク 1");
		book.setIsbn(9784088716138L);
		book.setAuthor("いのうえたけひこ");
		book.setReleaseDate(Date.valueOf("1991-07-11"));
		assertThat(it.update(book), is(1));
		assertThat(it.findById(1).orElse(null), is(samePropertyValuesAs(book)));
	}

	@Test
	public void testDelete() {
		assertThat(it.deleteById(2), is(1));
		assertThat(it.findById(2).orElse(null), is(nullValue()));
	}
}
