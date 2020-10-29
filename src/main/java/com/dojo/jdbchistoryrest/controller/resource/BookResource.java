package com.dojo.jdbchistoryrest.controller.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dojo.jdbchistoryrest.domain.book.entity.Book;
import com.dojo.jdbchistoryrest.domain.book.repository.IBookRepository;

@RestController
@RequestMapping("/api")
public class BookResource {

	private IBookRepository bookRepository;

	@Autowired
	public BookResource(@Qualifier("namedPramJdbcTplBookRepository") IBookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@GetMapping("/book")
	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	@GetMapping("/book/{id}")
	public Book findById(@PathVariable String id) {
		return bookRepository.findById(Long.valueOf(id)).orElse(null);
	}

	@PostMapping("/book")
	public Book create(@RequestBody Book book) {
		return bookRepository.save(book);
	}

	@PutMapping("/book/{id}")
	public Book update(@PathVariable String id, @RequestBody Book book) {
		book.setBookId(Long.valueOf(id));
		return bookRepository.save(book);
	}

	@DeleteMapping("/book/{id}")
	public void delete(@PathVariable String id) {
		bookRepository.deleteById(Long.valueOf(id));
	}

	@GetMapping("/book-title")
	public List<Book> searchByTitle(@RequestParam String title, Model model) {

		return bookRepository.findByTitleLike(title);

	}

}
