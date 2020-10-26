package com.dojo.jdbchistoryrest.controller.web.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dojo.jdbchistoryrest.domain.book.entity.Book;
import com.dojo.jdbchistoryrest.domain.book.repository.IBookRepository;

@Controller
public class IndexController {

	private IBookRepository bookRepository;

	@Autowired
	public IndexController(@Qualifier("namedPramJdbcTplBookRepository") IBookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@GetMapping("book-thymeleaf")
	public String indexThymeleaf(Model model) {
		model.addAttribute("bookList", bookRepository.findAll());
		return "/book/index-thymeleaf";
	}

	@PostMapping("book-thymeleaf/search-by-title")
	public String searchByTitle(@RequestParam("searchValue") String searchValue, Model model) {
		model.addAttribute("bookList", bookRepository.findByTitleLike(searchValue));
		model.addAttribute("searchValue", searchValue);
		return "/book/index-thymeleaf";
	}

	@GetMapping("book-thymeleaf/new")
	public String newBook(Model model) {

		model.addAttribute("book", new Book());

		model.addAttribute("action", "new");
		model.addAttribute("title", "Register Book");
		model.addAttribute("captionHeaderMessage", "fill out book info to create");
		model.addAttribute("classHeaderMessage", "alert alert-info");
		model.addAttribute("captionSubmitBtn", "create");
		model.addAttribute("classSubmitBtn", "btn btn-primary");
		return "/book/form-thymeleaf";
	}

	@GetMapping("book-thymeleaf/edit/{id}")
	public String editBook(@PathVariable String id, Model model) {
		model.addAttribute("book", bookRepository.findById(Long.valueOf(id)).orElse(new Book()));

		model.addAttribute("action", "edit");
		model.addAttribute("title", "Edit Book");
		model.addAttribute("captionHeaderMessage", "Do you want to edit this book?");
		model.addAttribute("classHeaderMessage", "alert alert-info");
		model.addAttribute("captionSubmitBtn", "update");
		model.addAttribute("classSubmitBtn", "btn btn-primary");

		return "/book/form-thymeleaf";
		
	}

	@GetMapping("book-thymeleaf/delete/{id}")
	public String deleteBook(@PathVariable String id, Model model) {

		model.addAttribute("book", bookRepository.findById(Long.valueOf(id)).orElse(new Book()));

		model.addAttribute("action", "delete");
		model.addAttribute("title", "Delete Book");
		model.addAttribute("captionHeaderMessage", "Do you want to delete this book?");
		model.addAttribute("classHeaderMessage", "alert alert-danger");
		model.addAttribute("captionSubmitBtn", "delete");
		model.addAttribute("classSubmitBtn", "btn btn-danger");
		model.addAttribute("readonly", true);

		return "/book/form-thymeleaf";
	}

	@GetMapping("book-vue")
	public String indexVue() {
		return "/book/index-vue";
	}

	@GetMapping("book-react")
	public String indexReact() {
		return "/book/index-react";
	}

	@GetMapping("book-vanillajs")
	public String indexVanillaJs() {
		return "/book/index-vanillajs";
	}

}
