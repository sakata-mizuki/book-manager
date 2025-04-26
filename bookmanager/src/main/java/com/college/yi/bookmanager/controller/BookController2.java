package com.college.yi.bookmanager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.college.yi.bookmanager.model.Book;
import com.college.yi.bookmanager.service.BookService;

@RestController
public class BookController2 {
	private BookService bookService;
	
	public BookController2(BookService bookService) {
        this.bookService = bookService;
	}

	@GetMapping("/api/books")
	  public List<Book> getBooks() {
	      return bookService.getAllBooks();
	  }
}
