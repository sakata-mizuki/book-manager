package com.yi_college.bookmanager.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private LocalDate publishDate;
    private int stock;
}
