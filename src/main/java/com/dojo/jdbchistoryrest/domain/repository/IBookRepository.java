package com.dojo.jdbchistoryrest.domain.repository;

import java.util.List;
import java.util.Optional;

import com.dojo.jdbchistoryrest.domain.entity.Book;

public interface IBookRepository {

	public List<Book> findAll();

	public Optional<Book> findById(long id);

	public List<Book> findByTitleLike(String author);

	public int count();

	public int create(Book book);

	public int update(long id, Book book);

	public int delete(long id);

}
