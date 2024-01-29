package com.github.vlinkus.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table( name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String isbn;
    private String bookName;

    private String bookCategory;
    private String publicationDate;
    private Integer amount;
    @ManyToOne
    @JoinColumn(name = "authorId")
    private Author author;

    public Book(String isbn, String bookName, Author author, String bookCategory, String publicationDate, Integer amount) {
        this.isbn = isbn;
        this.bookName = bookName;
        this.bookCategory = bookCategory;
        this.publicationDate = publicationDate;
        this.amount = amount;
        this.author = author;
    }
}
