package com.gongyuan.bookstore;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAspectJAutoProxy
@EnableTransactionManagement
@EnableCaching
@SpringBootApplication
@Slf4j
public class BookstoreApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(BookstoreApplication.class, args);
        } catch (Exception e) {
            log.error("BookstoreApplication start error", e);
        }
    }

}
