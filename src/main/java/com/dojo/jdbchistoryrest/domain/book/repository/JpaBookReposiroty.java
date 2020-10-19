package com.dojo.jdbchistoryrest.domain.book.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dojo.jdbchistoryrest.domain.book.entity.Book;

@Repository("jpaBookReposiroty")
public class JpaBookReposiroty implements IBookRepository {

	IBookJpaRepository repository;
	
	@Autowired
	public JpaBookReposiroty(IBookJpaRepository repository) {
		this.repository=repository;
	}

	@Override
	public List<Book> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Book> findById(long id) {
		return repository.findById(id);
	}

	@Override
	public List<Book> findByTitleLike(String author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int create(Book book) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(long id, Book book) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
