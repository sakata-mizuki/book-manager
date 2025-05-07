package com.yi_college.bookmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.yi_college.bookmanager.service.BookService;

@Controller
public class BookController {

	private final BookService bookService;
	
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@GetMapping("/books")
    public String showBookPage() {
        return "index";// サービスから書籍リストを取得


	}
	
}
