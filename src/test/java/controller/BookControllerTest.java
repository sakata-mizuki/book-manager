package controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.college.yi.bookmanager.controller.BookApiController;
import com.college.yi.bookmanager.controller.BookController;
import com.college.yi.bookmanager.model.Book;
import com.college.yi.bookmanager.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = BookController.class)
@ContextConfiguration(classes = BookApiController.class)
@AutoConfigureMockMvc 

public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @InjectMocks
    BookApiController bookApiController;

    @MockitoBean
    private BookService bookService; 

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @DisplayName("ADMINがpositive:本の一覧を取得すること")
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void getAllBooksListTest() throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("タイトル");
        book.setAuthor("著者");
        book.setPublisher("出版社");
        book.setPublishedDate(LocalDate.of(2025, 5, 1));
        book.setStock(15);
        when(bookService.getAllBooks()).thenReturn(Arrays.asList(book));

        mockMvc.perform(get("/api/books"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].title").value("タイトル"))
            .andExpect(jsonPath("$[0].author").value("著者"));
    }

    @Test
    @DisplayName("ADMINがPositive新しく本が登録できている事")
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void CreatedBookTest() throws Exception {        
        Book savedBook = new Book();
        Book book = new Book();
        
        book.setId(null);
        book.setTitle("更新されたタイトル");
        book.setAuthor("更新された著者");
        book.setPublisher("更新された出版社");
        book.setPublishedDate(LocalDate.of(2025, 5, 1));
        book.setStock(15);
        savedBook.setId(1L);
        savedBook.setTitle("タイトル");
        savedBook.setAuthor("著者");
        savedBook.setPublisher("出版社");
        savedBook.setPublishedDate(LocalDate.of(2025, 5, 1));
        savedBook.setStock(15);
        when(bookService.createBook(any(Book.class))).thenReturn(savedBook);

        mockMvc.perform(post("/api/books")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.title").value("タイトル"));
    }

    @Test
    @DisplayName("ADMINがpositive:本が更新できている事")
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void UpdateBookTest() throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("タイトル");
        book.setAuthor("著者");
        book.setPublisher("出版社");
        book.setPublishedDate(LocalDate.of(2025, 5, 1));
        book.setStock(15);

        when(bookService.updateBook(eq(1L), any(Book.class))).thenReturn(book);

        mockMvc.perform(put("/api/books/1")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title").value("タイトル"))
            .andExpect(jsonPath("$.author").value("著者"));
    }


    @Test
    @DisplayName("ADMINがPositive:消去機能が働いている事")
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void DeleateBookTest() throws Exception {
        doNothing().when(bookService).deleteBook(1L);
        mockMvc.perform(delete("/api/books/1")
                .with(csrf()))
            .andExpect(status().isNoContent());
           
    }
    
    @Test
    @DisplayName("Negative:USERとADMINが閲覧できること")
    void testGetBooks_AsUser_ShouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/books").with(user("user").roles("USER")))
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Negative:USERが登録機能を使うとエラーが返されること")
    void testCreateBook_AsUser_ShouldReturnForbidden() throws Exception {
        Book book = new Book();
        book.setId(null);
        book.setTitle("タイトル");
        book.setAuthor("著者");
        book.setPublisher("出版社");
        book.setPublishedDate(LocalDate.of(2025, 5, 1));
        book.setStock(15);

        mockMvc.perform(post("/api/books")
                .with(user("user").roles("USER"))
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
            .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Negative:USERが更新機能を使うとエラーが返されること")
    void testUpdateBook_AsUser_ShouldReturnForbidden() throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("タイトル");
        book.setAuthor("著者");
        book.setPublisher("出版社");
        book.setPublishedDate(LocalDate.of(2025, 5, 1));
        book.setStock(15);
        
        mockMvc.perform(put("/api/books/1")
                .with(user("user").roles("USER"))
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
            .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Negative:USERが消去機能を使うとエラーが返されること")
    void testDeleteBook_AsUser_ShouldReturnForbidden() throws Exception {
        mockMvc.perform(delete("/api/books/1")
                .with(user("user").roles("USER")))
            .andExpect(status().isForbidden());
    }
    
    @Test
    @DisplayName("Negative:未認証ユーザーが一覧取得できないこと")
    void testGetBooks_Unauthenticated_ShouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/api/books"))
            .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Negative:未認証ユーザーが登録できないこと")
    void testCreateBook_Unauthenticated_ShouldReturnUnauthorized() throws Exception {
        Book book = new Book();
        book.setTitle("未認証テスト");
        book.setAuthor("だれか");
        book.setPublisher("なし");
        book.setPublishedDate(LocalDate.of(2025, 5, 1));
        book.setStock(10);

        mockMvc.perform(post("/api/books")
                .with(csrf()) // CSRFは必要（未認証でも送られる想定で）
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
            .andExpect(status().isUnauthorized()); // ← ここが重要
    }
    @Test
    @DisplayName("Negative:未認証ユーザーが更新できないこと")
    void testUpdateBook_Unauthenticated_ShouldReturnUnauthorized() throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("未認証更新");

        mockMvc.perform(put("/api/books/1")
                .with(csrf())
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
        .andExpect(status().isUnauthorized()); 
    }

    @Test
    @DisplayName("Negative:未認証ユーザーが削除できないこと")
    void testDeleteBook_Unauthenticated_ShouldReturnUnauthorized() throws Exception {
        mockMvc.perform(delete("/api/books/1")
                .with(csrf())) 
            .andExpect(status().isUnauthorized());



    }
} 
