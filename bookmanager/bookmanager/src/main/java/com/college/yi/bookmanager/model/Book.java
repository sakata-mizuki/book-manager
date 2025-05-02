package com.college.yi.bookmanager.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Book {

    private Long id;
    private String title;
    private String author;
    private String publisher;
    private LocalDate publishedDate;
    private int stock;
}