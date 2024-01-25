package com.github.vlinkus.controllers;

import com.github.vlinkus.entities.Author;
import com.github.vlinkus.entities.AuthorResponseEntity;
import com.github.vlinkus.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/api/v1/authors")
@RestController
public class AuthorController {

    @Autowired
    AuthorService service;

    @GetMapping()
    public List<Author> getAllAuthors(){
        return service.getAuthors();
    }

    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable Long id){
        return service.getAuthorById(id);
    }

    @DeleteMapping("/{id}")
    public AuthorResponseEntity deleteAuthor(@PathVariable Long id){
        return service.deleteAuthorById(id);
    }

    @PostMapping()
    public AuthorResponseEntity addAuthor(@RequestBody Author author){
        return service.saveAuthor(author);
    }

    @PutMapping("/{id}")
    public AuthorResponseEntity updateAuthor(@PathVariable Long pathId, @RequestBody Author author){
        return service.updateAuthor(pathId, author);
    }
}
