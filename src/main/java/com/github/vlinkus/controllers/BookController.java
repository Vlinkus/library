package com.github.vlinkus.controllers;

import com.github.vlinkus.entities.Author;
import com.github.vlinkus.entities.Book;
import com.github.vlinkus.entities.BookResponseEntity;
import com.github.vlinkus.repositories.AuthorRepository;
import com.github.vlinkus.repositories.BookRepository;
import com.github.vlinkus.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    BookService service;

    @GetMapping()
    public List<Book> getAllBooks(){
        return service.getBooks();
    }

    @GetMapping("/id/{id}")
    public Book getBookById(@PathVariable Long id){
        return service.getBookById(id);
    }

    @GetMapping("/isbn/{isbn}")
    public List<Book> getBookByIsbn(@PathVariable String isbn){
        return service.findBooksByISBN(isbn);
    }

    @DeleteMapping("/{id}")
    public BookResponseEntity deleteBook(@PathVariable Long id){
        return service.deleteBookById(id);
    }

    @PostMapping()
    public BookResponseEntity addBook(@RequestBody Book book){
        return service.saveBook(book);
    }

    @PutMapping("/{id}")
    public BookResponseEntity updateBook(@PathVariable Long id, @RequestBody Book book){
        System.out.println(book.toString());
        return service.updateBook(id, book);
    }

}
