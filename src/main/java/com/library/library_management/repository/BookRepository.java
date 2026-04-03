package com.library.library_management.repository;

import com.library.library_management.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book>  findByAvailableTrue();
}
