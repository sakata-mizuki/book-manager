package com.college.yi.bookmanager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.college.yi.bookmanager.repository") // ★これ追加！
public class BookmanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookmanagerApplication.class, args);
    }

}
///
