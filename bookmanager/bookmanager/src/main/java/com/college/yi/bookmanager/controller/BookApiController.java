package com.college.yi.bookmanager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.college.yi.bookmanager.model.Book;      // ← 次エラーはいた時の見直しポジ
import com.college.yi.bookmanager.service.BookService;


@RestController
@RequestMapping("/api/books")
public class BookApiController {

    private final BookService bookService;

    public BookApiController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)//これ少し奥深そう？おもろそう。
    public List<Book> getAllBooks() {
        // BookService が返す List<model.Book>と型が一致
        return bookService.getAllBooks();
    }
}
