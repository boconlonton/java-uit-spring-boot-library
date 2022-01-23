package com.tutorial.springapi.controller;

import com.tutorial.springapi.models.Book;
import com.tutorial.springapi.models.ResponseObject;
import com.tutorial.springapi.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/books")
public class BookController {
    // DI = Dependency Injection
    @Autowired
    private BookRepository repository;

    @GetMapping("")
    List<Book> getAllBooks() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<Book> foundBook = repository.findById(id);
        if (foundBook.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Query book successfully", foundBook)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false", "Cannot find book with id = " + id, "")
            );
        }

    }

    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertBook(@RequestBody Book newBook) {
        List<Book> foundBooks = repository.findByName(newBook.getName().trim());
        if(foundBooks.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Book name is already existed", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert book successfully", repository.save(newBook))
        );
    }

    // update, upsert = update if found, otherwise insert
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateBook(@RequestBody Book newBook, @PathVariable Long id) {
        Book updatedBook = repository.findById(id)
                .map(book -> {
                    book.setName(newBook.getName());
                    book.setGenre(newBook.getGenre());
                    return repository.save(book);
                }).orElseGet(() -> {
                    newBook.setId(id);
                    return repository.save(newBook);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update book successfully", updatedBook)
        );
    }

    // delete a book
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteBook(@PathVariable Long id) {
        boolean exists = repository.existsById(id);
        if(exists) {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete book successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Cannot find book to delete", "")
        );
    }
}
