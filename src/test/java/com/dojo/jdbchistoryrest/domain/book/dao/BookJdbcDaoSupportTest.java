package com.dojo.jdbchistoryrest.domain.book.dao;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.dojo.jdbchistoryrest.domain.book.entity.Book;

@JdbcTest
class BookJdbcDaoSupportTest {

	@Autowired
	DataSource dataSource;

	BookJdbcDaoSupport it;

	@BeforeEach
	void setUp() throws Exception {
		it = new BookJdbcDaoSupport();
		it.setDataSource(dataSource);
	}

	@Test
	void testFindAll() {
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

		it = new BookJdbcDaoSupport();
		it.setDataSource(dataSource);

		assertThat(it.findAll(), is(containsInAnyOrder(samePropertyValuesAs(book1), samePropertyValuesAs(book2),
				samePropertyValuesAs(book3))));

		assertThat(it.findAllByJdbcTemplate(), is(containsInAnyOrder(samePropertyValuesAs(book1),
				samePropertyValuesAs(book2), samePropertyValuesAs(book3))));

		assertThat(it.findAllByJdbcTemplateRowMapper(), is(containsInAnyOrder(samePropertyValuesAs(book1),
				samePropertyValuesAs(book2), samePropertyValuesAs(book3))));

		assertThat(it.findAllByJdbcTemplateRowMapperRamda(), is(containsInAnyOrder(samePropertyValuesAs(book1),
				samePropertyValuesAs(book2), samePropertyValuesAs(book3))));
	}

}
