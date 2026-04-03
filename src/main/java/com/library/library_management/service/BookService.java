package com.library.library_management.service;

import com.library.library_management.model.Book;
import com.library.library_management.model.Borrower;
import com.library.library_management.repository.BookRepository;
import com.library.library_management.repository.BorrowerRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BorrowerRepository borrowerRepository;

    public BookService(BookRepository bookRepository,  BorrowerRepository borrowerRepository) {
        this.bookRepository = bookRepository;
        this.borrowerRepository = borrowerRepository;
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.findByAvailableTrue();
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book borrowBook(Long bookId, Long borrowerId) {
        Book bookToBorrow = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));
        Borrower borrower = borrowerRepository.findById(borrowerId).orElseThrow(() -> new RuntimeException("Borrower not found with id: " + borrowerId));

        if (!bookToBorrow.isAvailable()) {
            throw new RuntimeException("Book is already borrowed");
        }

        bookToBorrow.setAvailable(false);
        bookToBorrow.setBorrower(borrower);

        return bookRepository.save(bookToBorrow);
    }
}