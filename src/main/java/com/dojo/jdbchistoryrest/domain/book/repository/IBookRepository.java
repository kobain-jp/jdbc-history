package com.dojo.jdbchistoryrest.domain.book.repository;

import java.util.List;
import java.util.Optional;

import com.dojo.jdbchistoryrest.domain.book.entity.Book;

public interface IBookRepository {

	public List<Book> findAll();

	public Optional<Book> findById(long id);

	public List<Book> findByTitleLike(String author);

	public int count();

	public int insert(Book book);

	public int update(Book book);

	public int deleteById(long id);
		
	public Book save(Book book);

	boolean existsById(long id);

	public int insertGetId(Book book);

}
