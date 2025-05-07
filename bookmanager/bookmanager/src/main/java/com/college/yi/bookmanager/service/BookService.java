package com.college.yi.bookmanager.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.college.yi.bookmanager.model.Book;
import com.college.yi.bookmanager.repository.bookMapper;

@Service
public class BookService {

    private final bookMapper bookMapper;

    public BookService(bookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public List<Book> getAllBooks() {
        List<Book> books = bookMapper.findAll();
        if (books.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "書籍が見つかりませんでした。");
        }
        return books;
    }

    public Book createBook(Book book) {
        bookMapper.insert(book);
        return book;
    }

    public Book updateBook(Long id, Book book) {
        book.setId(id);
        bookMapper.update(book);
        return book;
    }

    public void deleteBook(int id) { // ← intにする
        bookMapper.delete(id);
    }

}
