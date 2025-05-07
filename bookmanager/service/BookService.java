package com.yi_college.bookmanager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.yi_college.bookmanager.entity.BookEntity;
import com.yi_college.bookmanager.model.Book;
import com.yi_college.bookmanager.repository.BookMapper;

@Service
public class BookService {

    private final BookMapper bookMapper;

    public BookService(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public List<Book> getAllBooks() {
        List<BookEntity> entities = bookMapper.findAll();
        return entities.stream()
                .map(entity -> new Book(
                        entity.getId(),
                        entity.getTitle(),
                        entity.getAuthor(),
                        entity.getPublisher(),
                        entity.getPublishedDate(),
                        entity.getStock()
                ))
                .collect(Collectors.toList());
    }

    public Book createBook(Book book) {
        // BookEntity に変換してDBに保存
        BookEntity entity = new BookEntity();
        entity.setTitle(book.getTitle());
        entity.setAuthor(book.getAuthor());
        entity.setPublisher(book.getPublisher());
        entity.setPublishedDate(book.getPublishDate());
        entity.setStock(book.getStock());
        
        bookMapper.insert(entity);  // MyBatisのinsertメソッドを使用してデータベースに保存
        
        // 保存した後、bookオブジェクトにIDを設定して返す
        book.setId(entity.getId());
        return book;
    }

    public Book updateBook(int id, Book book) {
        // BookEntity に変換
        BookEntity entity = new BookEntity();
        entity.setId(id);  // 更新するIDを設定
        entity.setTitle(book.getTitle());
        entity.setAuthor(book.getAuthor());
        entity.setPublisher(book.getPublisher());
        entity.setPublishedDate(book.getPublishDate());  // 修正されたgetPublishedDate()を使用
        entity.setStock(book.getStock());

        bookMapper.update(entity);  // BookEntityを更新
        
        return book;
    }

    public void deleteBook(int id) {
        bookMapper.delete(id);
    }
}
