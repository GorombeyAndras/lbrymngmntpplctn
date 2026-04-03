package com.library.library_management.repository;

import com.library.library_management.model.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BorrowerRepository extends JpaRepository<Borrower, Long> {

}
