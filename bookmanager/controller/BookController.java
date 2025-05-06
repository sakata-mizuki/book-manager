package com.yi_college.bookmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yi_college.bookmanager.service.BookService;

@Controller
@RequestMapping("books")
public class BookController {

	private final BookService bookService;
	
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@GetMapping
    public String showBookPage() {
        return "index";// サービスから書籍リストを取得
	}
	
}
