package com.github.vlinkus.services;

import com.github.vlinkus.entities.Author;
import com.github.vlinkus.entities.AuthorResponseEntity;
import com.github.vlinkus.repositories.AuthorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class AuthorService {

    private final AuthorRepository repository;

    public AuthorService(AuthorRepository authorRepository){
        this.repository = authorRepository;
    }

    public List<Author> getAuthors(){
        return  repository.findAll();
    }

    public Author getAuthorById(Long id){
        return repository.findById(id).orElse(null);
    }

    public AuthorResponseEntity deleteAuthorById(Long id){
        boolean authorExist = false;
        boolean authorExisted = false;
        if(id!= null )
            authorExisted = repository.existsById(id);
        if(authorExisted){
            repository.deleteById(id);
            authorExist = !repository.existsById(id);
        }
        return   authorExisted & authorExist ?  new AuthorResponseEntity("Author DELETED", HttpStatus.OK) :
                new AuthorResponseEntity("Author delete CANCELED", HttpStatus.BAD_REQUEST);
    }

    public AuthorResponseEntity saveAuthor(Author author){
        Author savedAuthor = null;
        if(author != null & author.getAuthorId() == null){
            savedAuthor = repository.save(author);
        }
        return savedAuthor != null ? new AuthorResponseEntity("Author SAVED",savedAuthor, HttpStatus.OK) :
                new AuthorResponseEntity("Author save CANCELED", HttpStatus.BAD_REQUEST);
    }

    public AuthorResponseEntity updateAuthor(Long id, Author author){
        Author updatedAuthor = null;
        boolean authorExists = false;
        if(author != null &  id!= null & author.getAuthorId()!= null & id == author.getAuthorId())
            authorExists = repository.existsById(id);
        if(authorExists){
            updatedAuthor = repository.save(author);
        }
        return updatedAuthor != null ? new AuthorResponseEntity("Author UPDATED",updatedAuthor, HttpStatus.OK) :
                new AuthorResponseEntity("Author update CANCELED", HttpStatus.BAD_REQUEST);
    }
}
