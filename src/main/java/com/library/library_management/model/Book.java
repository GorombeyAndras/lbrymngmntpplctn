package com.library.library_management.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor //The JPA can instantiate an object from the db when reading it with a no argument constructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    private boolean available = true;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "borrower_id")
    private Borrower borrower;
}
