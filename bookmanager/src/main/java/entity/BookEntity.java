package com.example.bookmanager.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BookEntity {
    private Integer id;
    private String title;
    private String author;
    private String publisher;
    private LocalDate publishedDate;
    private Integer stock;
}