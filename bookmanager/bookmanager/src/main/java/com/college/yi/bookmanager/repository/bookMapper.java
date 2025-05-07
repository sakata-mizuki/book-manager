package com.college.yi.bookmanager.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.college.yi.bookmanager.model.Book;

@Mapper
public interface bookMapper {
    List<Book> findAll();
    void insert(Book book);
    void update(Book book);
    void delete(int id);
}
