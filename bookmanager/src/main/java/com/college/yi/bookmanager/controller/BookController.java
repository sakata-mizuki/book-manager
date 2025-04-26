package com.college.yi.bookmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.college.yi.bookmanager.service.BookService;

@Controller
public class BookController {

  @SuppressWarnings("unused")
private BookService bookService;

@GetMapping("/books")
public String contextLoads() {
    return "index";

	}
}
