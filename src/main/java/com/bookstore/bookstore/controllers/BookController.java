package com.bookstore.bookstore.controllers;

import com.bookstore.bookstore.Dtos.BookRecordDto;
import com.bookstore.bookstore.models.BookModel;
import com.bookstore.bookstore.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/book_Store/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookModel>> getAllBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
    }

    @PostMapping
    public ResponseEntity<BookModel> saveBook(@RequestBody BookRecordDto bookRecordDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(bookRecordDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook (@PathVariable UUID id){
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.OK).body("Book deleted successfully");
    }
}
