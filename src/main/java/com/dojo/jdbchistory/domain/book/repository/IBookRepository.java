package com.dojo.jdbchistory.domain.book.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.dojo.jdbchistory.domain.book.entity.Book;

@Repository
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
