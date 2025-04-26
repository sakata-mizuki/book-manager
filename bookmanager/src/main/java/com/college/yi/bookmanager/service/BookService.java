package com.college.yi.bookmanager.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.college.yi.bookmanager.model.Book;

@Service
public class BookService {
	
	public List<Book> getAllBooks(){
		List<Book> books = new ArrayList<>();
	
	
	Book book1 = new Book();
	 book1.setId(1L);
     book1.setTitle("Spring入門");
     book1.setAuthor("田中 太郎");
     book1.setPublisher("技術評論社");
     book1.setPublishedDate(LocalDate.of(2022, 1, 15));
     book1.setStock(5);
     
     Book book2 = new Book();
     book2.setId(2L);
     book2.setTitle("Java基礎");
     book2.setAuthor("佐藤 花子");
     book2.setPublisher("翔泳社");
     book2.setPublishedDate(LocalDate.of(2021, 3, 10));
     book2.setStock(8);
     
     Book book3 = new Book();
     book3.setId(3L);
     book3.setTitle("Web開発の入門");
     book3.setAuthor("鈴木 次郎");
     book3.setPublisher("マイナビ出版");
     book3.setPublishedDate(LocalDate.of(2020, 7, 20));
     book3.setStock(3);
     
     Book book4 = new Book();
     book4.setId(4L);
     book4.setTitle("データベース入門");
     book4.setAuthor("高橋 明美");
     book4.setPublisher("オライリー・ジャパン");
     book4.setPublishedDate(LocalDate.of(2019, 5, 30));
     book4.setStock(2);
     
     Book book5 = new Book();
     book5.setId(5L);
     book5.setTitle("プログラミング思考");
     book5.setAuthor("山本 一郎");
     book5.setPublisher("集英社");
     book5.setPublishedDate(LocalDate.of(2023, 2, 5));
     book5.setStock(10);
     
 
     
     books.add(book1);
     books.add(book2);
     books.add(book3);
     books.add(book4);
     books.add(book5);

     return books;
	}
     
}
