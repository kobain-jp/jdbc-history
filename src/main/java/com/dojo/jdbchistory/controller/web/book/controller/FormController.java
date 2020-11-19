package com.dojo.jdbchistory.controller.web.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dojo.jdbchistory.domain.book.entity.Book;
import com.dojo.jdbchistory.domain.book.repository.IBookRepository;

@Controller
public class FormController {

	private IBookRepository bookRepository;

	@Autowired
	public FormController(@Qualifier("namedPramJdbcTplBookRepository") IBookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@PostMapping("/book-thymeleaf/form/new")
	public String newBook(@ModelAttribute Book book) {
		bookRepository.save(book);
		return "redirect:/book-thymeleaf";
	}

	@PostMapping("/book-thymeleaf/form/delete")
	public String deleteById(@ModelAttribute Book book) {
		bookRepository.deleteById(Long.valueOf(book.getBookId()));
		return "redirect:/book-thymeleaf";
	}
	
	@PostMapping("/book-thymeleaf/form/edit")
	public String edit(@ModelAttribute Book book) {
		bookRepository.save(book);
		return "redirect:/book-thymeleaf";
	}

}
