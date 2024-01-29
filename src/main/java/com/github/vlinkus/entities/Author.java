package com.github.vlinkus.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table( name = "authors")
public class Author {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long authorId;
    private String firstName;
    private String lastName;
    private String introduction;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Book> books;

    public Author(String firstName, String lastName, String introduction, List<Book> books) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.introduction = introduction;
        this.books = books;
    }
    public Author(String firstName, String lastName, String introduction) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.introduction = introduction;
    }
}
