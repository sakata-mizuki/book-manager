package serivce;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import com.college.yi.bookmanager.entity.BookEntity;
import com.college.yi.bookmanager.model.Book;
import com.college.yi.bookmanager.repository.BookRepository;
import com.college.yi.bookmanager.service.BookService;


@ExtendWith(MockitoExtension.class) 
class BookServiceTest {
    
    @InjectMocks
    private BookService bookService;
    
    @Mock
    private BookRepository bookRepository;

    @Test
    @DisplayName("negative:書籍情報が見つからなかった際にResponseStatusExceptionを返すこと")
    void BookServicetest() {
        when(bookRepository.findAll()).thenReturn(Collections.emptyList());
        
        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class, () -> bookService.getAllBooks() );
        
        assertEquals("404 NOT_FOUND \"書籍が見つかりません\"", exception.getMessage());
    }
    
    @Test
    @DisplayName("positive:書籍登録がされている事")
    void CreateBooktest() {
        Book setBook = new Book();
        setBook.setId(100L);  
        setBook.setTitle("タイトル");
        setBook.setAuthor("著者");
        setBook.setPublisher("出版社");
        setBook.setPublishedDate(LocalDate.of(2025, 4, 27));
        setBook.setStock(5);
        
        doAnswer(invocation -> {
            BookEntity entity = invocation.getArgument(0);
            entity.setId(100L);  
            return null;      
        }).when(bookRepository).insert(any(BookEntity.class));
        
        Book result = bookService.createBook(setBook);

        ArgumentCaptor<BookEntity> captor = ArgumentCaptor.forClass(BookEntity.class);
        verify(bookRepository, times(1)).insert(captor.capture());

        BookEntity capturedEntity = captor.getValue();
        assertEquals("タイトル", capturedEntity.getTitle());
        assertEquals("著者", capturedEntity.getAuthor());
        assertEquals("出版社", capturedEntity.getPublisher());
        assertEquals(LocalDate.of(2025, 4, 27), capturedEntity.getPublishedDate());
        assertEquals(5, capturedEntity.getStock());

        assertEquals(100L, result.getId());
        assertEquals("タイトル", result.getTitle());
        assertEquals("著者", result.getAuthor());
    }
    @Test
    @DisplayName("positive:書籍更新がされていること")
    void updateBookTest() {
        Book upDateModel = new Book();
        upDateModel.setId(1L);
        upDateModel.setTitle("更新されたタイトル");
        upDateModel.setAuthor("更新された著者");
        upDateModel.setPublisher("更新された出版社");
        upDateModel.setPublishedDate(LocalDate.of(2025, 5, 1));
        upDateModel.setStock(15);

        // Act（メソッド実行）
        Book result = bookService.updateBook(1L, upDateModel);

        ArgumentCaptor<BookEntity> captor = ArgumentCaptor.forClass(BookEntity.class);
        verify(bookRepository, times(1)).update(captor.capture());

        // 捕まえたエンティティをチェック
        BookEntity capturedEntity = captor.getValue();
        assertEquals(1L, capturedEntity.getId());
        assertEquals("更新されたタイトル", capturedEntity.getTitle());
        assertEquals("更新された著者", capturedEntity.getAuthor());
        assertEquals("更新された出版社", capturedEntity.getPublisher());
        assertEquals(LocalDate.of(2025, 5, 1), capturedEntity.getPublishedDate());
        assertEquals(15, capturedEntity.getStock());

        assertEquals(upDateModel, result); 
    }
    @Test
    @DisplayName("positive:消去機能が機能している事")
    void deleateTest() {
        Long bookId = 1L;
        doNothing().when(bookRepository).delete(bookId);

        bookService.deleteBook(bookId);

        verify(bookRepository, times(1)).delete(bookId);
    }
    }

    
    

   
        
     
        

