package com.college.yi.bookmanager.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BookService {
    public List<Book> getAllBooks() {
        return Arrays.asList(
            new Book(1L, "Java超入門", "山田太郎", "技術出版社", LocalDate.of(2023, 3, 15), 10),
            new Book(2L, "Spring Boot完全ガイド", "佐藤花子", "コード出版", LocalDate.of(2024, 1, 20), 7),
            new Book(3L, "MyBatisハンズオン", "中村健", "開発社", LocalDate.of(2022, 6, 1), 4),
            new Book(4L, "PostgreSQL実践", "渡辺翔", "DB出版", LocalDate.of(2021, 11, 10), 6),
            new Book(5L, "Thymeleafテンプレート", "田中絵里", "Web出版", LocalDate.of(2024, 9, 5), 3)
        );
    }
}