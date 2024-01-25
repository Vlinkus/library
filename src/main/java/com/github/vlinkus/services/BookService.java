package com.github.vlinkus.services;

import com.github.vlinkus.entities.Book;
import com.github.vlinkus.entities.BookResponseEntity;
import com.github.vlinkus.repositories.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository bookRepository){
        this.repository = bookRepository;
    }

    public List<Book> getBooks(){
        return  repository.findAll();
    }

    public Book getBookById(Long id){
        return repository.findById(id).orElse(null);
    }

    public BookResponseEntity deleteBookById(Long id){
        boolean bookExist = false;
        boolean bookExisted = false;
        if(id != null)
            bookExisted = repository.existsById(id);
        if(bookExisted){
            repository.deleteById(id);
            bookExist = !repository.existsById(id);
        }
         return   bookExisted & bookExist ?  new BookResponseEntity("Book DELETED", HttpStatus.OK) :
             new BookResponseEntity("Book delete CANCELED", HttpStatus.BAD_REQUEST);
    }

    public BookResponseEntity saveBook(Book book){
        Book savedBook = null;
        if(book != null & book.getBookId() == null){
            savedBook = repository.save(book);
        }
        return savedBook != null ? new BookResponseEntity("Book SAVED",savedBook, HttpStatus.OK) :
                new BookResponseEntity("Book save CANCELED", HttpStatus.BAD_REQUEST);
    }

    public BookResponseEntity updateBook(Long id, Book book){
        Book updatedBook = null;
        boolean bookExists = false;
        if(book != null &  id!= null & book.getBookId()!= null & id == book.getBookId())
            bookExists = repository.existsById(id);
        if(bookExists){
            updatedBook = repository.save(book);
        }
        return updatedBook != null ? new BookResponseEntity("Book UPDATED",updatedBook, HttpStatus.OK) :
                new BookResponseEntity("Book update CANCELED", HttpStatus.BAD_REQUEST);
    }



}
