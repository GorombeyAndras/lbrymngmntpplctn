package com.library.library_management.service;

import com.library.library_management.model.Book;
import com.library.library_management.model.Borrower;
import com.library.library_management.repository.BorrowerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BorrowerService {
    private final BorrowerRepository borrowerRepository;

    public BorrowerService(BorrowerRepository borrowerRepository) {
        this.borrowerRepository = borrowerRepository;
    }

    public Borrower createBorrower(Borrower borrower) {
        return borrowerRepository.save(borrower);
    }

    public Borrower getBorrowerById(Long id) {
        return borrowerRepository.findById(id).orElseThrow(() -> new RuntimeException("Borrower not found with id: " + id));
    }

    public List<Book> getBorrowerBooksById(Long id) {
        return borrowerRepository.findById(id).orElseThrow(() -> new RuntimeException("Borrower not found with id: " + id)).getBooks();
    }
}
