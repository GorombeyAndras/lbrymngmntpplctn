package com.library.library_management.controller;

import com.library.library_management.model.Book;
import com.library.library_management.service.BookService;
import com.library.library_management.service.BorrowerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final BorrowerService borrowerService;

    public BookController(BookService bookService,  BorrowerService borrowerService) {
        this.bookService = bookService;
        this.borrowerService = borrowerService;
    }

    @GetMapping
    public List<Book> getAvailableBooks() {
        return bookService.getAvailableBooks();
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book saved = bookService.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{bookId}/borrow")
    public ResponseEntity<Book> borrowBook(@PathVariable Long bookId, @RequestParam Long borrowerId) {
        Book borrowedBook = bookService.borrowBook(bookId, borrowerId);
        return ResponseEntity.ok(borrowedBook);
    }

}