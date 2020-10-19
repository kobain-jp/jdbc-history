package com.dojo.jdbchistoryrest.domain.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dojo.jdbchistoryrest.domain.book.entity.Book;

public interface IBookJpaRepository extends JpaRepository<Book, Long> {

}
