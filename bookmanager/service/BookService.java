package com.college.yi.bookmanager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.college.yi.bookmanager.entity.BookEntity;
import com.college.yi.bookmanager.exception.BookNotFoundException;
import com.college.yi.bookmanager.model.Book;
import com.college.yi.bookmanager.repository.BookRepository;


@Service
public class BookService {
	
	private final BookRepository booksRepository ;
	
    public BookService(BookRepository booksRepository) {
	     this.booksRepository = booksRepository;
	 }
	
    public List<Book> getAllBooks() {
        List<BookEntity> entities = booksRepository.findAll();
        
        if (entities.isEmpty()) {
            throw new BookNotFoundException("書籍が見つかりませんでした");
        }

        // Entity → Model への変換
        return entities.stream()
                .map(e -> new Book(
                        e.getId(),
                        e.getTitle(),
                        e.getAuthor(),
                        e.getPublisher(),
                        e.getPublishedDate(),
                        e.getStock()
                ))
                .collect(Collectors.toList());
    }

    public Book addBook(Book book) {
        try {
            // Book → Entity に変換
            BookEntity bookEntity = new BookEntity();
            bookEntity.setTitle(book.getTitle());
            bookEntity.setAuthor(book.getAuthor());
            bookEntity.setPublisher(book.getPublisher());
            bookEntity.setPublishedDate(book.getPublishedDate());
            bookEntity.setStock(book.getStock());

            // MyBatisのinsert（saveは使わない）tig
            booksRepository.insert(bookEntity);

            // 自動生成されたIDがbookEntityにセットされている（Mapper側で設定した場合）
            return new Book(
                bookEntity.getId(),  // insert後にIDが入ってる前提
                bookEntity.getTitle(),
                bookEntity.getAuthor(),
                bookEntity.getPublisher(),
                bookEntity.getPublishedDate(),
                bookEntity.getStock()
            );
        } catch (Exception e) {
            throw new RuntimeException("データベース保存時にエラーが発生しました: " + e.getMessage(), e);
        }
    }
        // 書籍更新
        public Book updateBook(Long id, Book book) {
            // 書籍をリポジトリから取得
            BookEntity existingBookEntity = booksRepository.findById(id);
            if (existingBookEntity == null) {
                throw new BookNotFoundException("書籍が見つかりませんでした");
            }	
            		

            // 既存書籍の情報を更新
            existingBookEntity.setTitle(book.getTitle());
            existingBookEntity.setAuthor(book.getAuthor());
            existingBookEntity.setPublisher(book.getPublisher());
            existingBookEntity.setPublishedDate(book.getPublishedDate());
            existingBookEntity.setStock(book.getStock());

            // 更新後の書籍情報を保存
           booksRepository.update(existingBookEntity);

            // BookEntity -> Book に変換して返す（DTOとエンティティの変換）
            return new Book(
            		existingBookEntity.getId(),
            		existingBookEntity.getTitle(),
            		existingBookEntity.getAuthor(),
            		existingBookEntity.getPublisher(),
            		existingBookEntity.getPublishedDate(),
            		existingBookEntity.getStock()
            );
        }
        
        public void deleteBook(Long id) {
            // 削除対象の書籍が存在するかチェック
            BookEntity book = booksRepository.findById(id);
            	if (book == null) {
            	      throw new BookNotFoundException("書籍が見つかりませんでした");
            	  }

            // 存在すれば削除
            booksRepository.delete(id);
        }

    
}
