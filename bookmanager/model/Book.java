package com.college.yi.bookmanager.model;

import java.time.LocalDate;

import lombok.Data;

@Data

public class Book {
	private Long id; 
	private String title;
	private String author;//著者
	private String publisher;//出版社
	private LocalDate publishedDate;//出版日
	private int stock;//在庫数


public Book(Long id, String title, String author, String publisher, LocalDate publishedDate, int stock) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.publisher = publisher;
    this.publishedDate = publishedDate;
    this.stock = stock;
}
}
	
//	public Long getId() {
//		return id;
//	}
//	
//	public void setId(Long id) {
//		this.id = id;
//	}
//	
//	public String getTitle() {
//		return title;
//	}
//	
//	public void setTitle(String title) {
//		this.title = title;
//	}
//	
//	public String getAuthor() {
//		return author;
//	}
//	
//	public void setAuthor(String author) {
//		this.author = author;
//		
//	}
//	
//	public String getPublisher(){
//		return publisher;
//		
//	}
//	
//	public void setPublisher(String publisher) {
//		this.publisher = publisher;
//		
//	}
//	
//	public LocalDate getPublishDate() {
//		return publishDate;
//	}
//	
//	public void setPublishDate( LocalDate publishDate) {
//		this.publishDate = publishDate;
//	}
//	
//	public int getStock() {
//		return stock;
//	}
//	
//	public void setStock(int stock) {
//		this.stock = stock;
//	}
//
//
//	
//	
//	
//}
