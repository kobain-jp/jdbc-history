package com.dojo.jdbchistory.domain.book.dao;

import java.util.List;
import java.util.Optional;

import com.dojo.jdbchistory.domain.book.entity.Book;

public interface IBook {

	public List<Book> findAll();

	public Optional<Book> findById(long id);

	public List<Book> findByTitleLike(String author);

	public int count();

	public int insert(Book book);

	public int update(Book book);

	public int deleteById(long id);

}
