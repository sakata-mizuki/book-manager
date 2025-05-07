
package com.yi_college.bookmanager.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yi_college.bookmanager.model.Book;

@Service
public class BookService {
	
	public List<Book> getAllBooks(){
		List<Book> books = new ArrayList<>();	
		
			books.add(new Book(1, "Javatest", "山田太郎", "A", LocalDate.of(2020, 4, 1), 5));
			books.add(new Book(2, "Javatest2", "山田太郎", "出版社B", LocalDate.of(2020, 4, 1), 2));
			books.add(new Book(3, "Javatest3", "山田太郎", "C", LocalDate.of(2020, 6, 1), 5));
			books.add(new Book(4, "Javatest4", "山田太郎", "A", LocalDate.of(2020, 4, 1), 5));
			books.add(new Book(5, "Javatest", "山田太郎", "A", LocalDate.of(2020, 4, 1), 5));
			return books;
	}
}
