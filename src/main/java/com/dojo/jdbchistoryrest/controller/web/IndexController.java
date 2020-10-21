package com.dojo.jdbchistoryrest.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dojo.jdbchistoryrest.domain.book.repository.IBookRepository;

@Controller
public class IndexController {

	private IBookRepository bookRepository;

	@Autowired
	public IndexController(@Qualifier("namedPramJdbcTplBookRepository") IBookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@GetMapping("book-vue")
	public String indexVue() {
		return "/book/index-vue";
	}

	@GetMapping("book-thymeleaf")
	public String indexThymeleaf(Model model) {
		model.addAttribute("bookList", bookRepository.findAll());
		return "/book/index-thymeleaf";
	}

}
