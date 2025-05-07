package com.yi_college.bookmanager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yi_college.bookmanager.model.Book;
import com.yi_college.bookmanager.service.BookService;

@RestController
public class ApiController {
	private final BookService bookService = new BookService();

	@GetMapping("/api/books")
	public List<Book>getBooks(){
		return bookService.getAllBooks();
	}
}
