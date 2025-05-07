package com.example.bookmanager.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import entity.BookEntity;

@Mapper
public interface BookMapper {
    List<BookEntity> findAll();
    void insert(BookEntity book);
    void update(BookEntity book);
    void delete(int id);
}