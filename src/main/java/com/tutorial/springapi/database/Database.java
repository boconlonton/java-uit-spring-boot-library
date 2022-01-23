package com.tutorial.springapi.database;

import com.tutorial.springapi.models.Book;
import com.tutorial.springapi.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {
    // logger
    private static final Logger logger = LoggerFactory.getLogger(Database.class);

    @Bean
    CommandLineRunner initDatabase(BookRepository bookRepository) {

        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
//                Book bookA = new Book("LOTR", "Fantasy");
//                Book bookB = new Book("GOT", "Fantasy");
//                logger.info("insert data: " + bookRepository.save(bookA));
//                logger.info("insert data: " + bookRepository.save(bookB));
            }
        };
    }

}
