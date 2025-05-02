package com.college.yi.bookmanager.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.college.yi.bookmanager.model.Book;

@Service
public class BookService {

    public List<Book> getAllBooks() {
        return Arrays.asList(
        //これはgithubからでよかったのか？		
            createBook(1L, "Java入門", "山田 太郎", "技術評論社", LocalDate.of(2020, 1, 15), 5),
            createBook(2L, "Spring Boot実践", "佐藤 花子", "翔泳社", LocalDate.of(2021, 5, 10), 3),
            createBook(3L, "MyBatis完全ガイド", "中村 一郎", "オライリー", LocalDate.of(2022, 3, 20), 2),
            createBook(4L, "SQLの絵本", "田中 美咲", "インプレス", LocalDate.of(2019, 7, 25), 8),
            createBook(5L, "クラウド時代のアーキテクチャ", "鈴木 健", "技術評論社", LocalDate.of(2023, 9, 30), 4)
        );
    }

    private Book createBook(Long id, String title, String author, String publisher, LocalDate publishedDate, int stock) {
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setPublishedDate(publishedDate);
        book.setStock(stock);
        return book;
    }
}
