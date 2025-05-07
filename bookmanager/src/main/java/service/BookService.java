package com.example.bookmanager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import entity.BookEntity;
import repository.BookMapper;

@Service
public class BookService {

    private final BookMapper bookMapper;

    public BookService(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public List<Book> getAllBooks() {
        List<BookEntity> entities = bookMapper.findAll();
        return entities.stream().map(this::toModel).collect(Collectors.toList());
    }

    public Book addBook(Book book) {
        BookEntity entity = toEntity(book);
        bookMapper.insert(entity);
        book.setId(entity.getId());
        return book;
    }

    public Book updateBook(int id, Book book) {
        book.setId(id);
        BookEntity entity = toEntity(book);
        bookMapper.update(entity);
        return book;
    }

    public void deleteBook(int id) {
        bookMapper.delete(id);
    }

    private BookEntity toEntity(Book book) {
        BookEntity e = new BookEntity();
        e.setId(book.getId());
        e.setTitle(book.getTitle());
        e.setAuthor(book.getAuthor());
        e.setPublisher(book.getPublisher());
        e.setPublishedDate(book.getPublishedDate());
        e.setStock(book.getStock());
        return e;
    }

    private Book toModel(BookEntity e) {
        Book book = new Book();
        book.setId(e.getId());
        book.setTitle(e.getTitle());
        book.setAuthor(e.getAuthor());
        book.setPublisher(e.getPublisher());
        book.setPublishedDate(e.getPublishedDate());
        book.setStock(e.getStock());
        return book;
    }
}