package com.library.library_management.controller;


import com.library.library_management.model.Book;
import com.library.library_management.model.Borrower;
import com.library.library_management.service.BorrowerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrowers")
public class BorrowerController {
    private final BorrowerService borrowerService;

    public  BorrowerController(BorrowerService borrowerService) {
        this.borrowerService = borrowerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Borrower> getBorrowerById(@PathVariable Long id) {
        return ResponseEntity.ok(borrowerService.getBorrowerById(id));
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<List<Book>> getBorrowerBooksById(@PathVariable Long id) {
        return ResponseEntity.ok(borrowerService.getBorrowerBooksById(id));
    }

    @PostMapping
    public ResponseEntity<Borrower> createBorrower(@RequestBody Borrower borrower){
        Borrower savedBorrower = borrowerService.createBorrower(borrower);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBorrower);
    }

}
