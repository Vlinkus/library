package com.github.vlinkus.services;

import com.github.vlinkus.entities.Author;
import com.github.vlinkus.entities.Book;
import com.github.vlinkus.entities.BookResponseEntity;
import com.github.vlinkus.repositories.AuthorRepository;
import com.github.vlinkus.repositories.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository){
        this.repository = bookRepository;
        this.authorRepository = authorRepository;
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
        if(book != null) {
            Author authorOfTheBook = checkIfAuthorExistsOrSaveAuthor(book.getAuthor());
            Book bookByIsbn = checkIfBookWithIsbnExist(book);
            if(bookByIsbn != null){
                bookByIsbn.setAmount(bookByIsbn.getAmount()+book.getAmount());
                if(bookByIsbn.getAuthor() == null) bookByIsbn.setAuthor(authorOfTheBook);
                savedBook = repository.save(bookByIsbn);
            } else {
                book.setBookId(null);
                book.setAuthor(authorOfTheBook);
                savedBook = repository.save(book);
            }
        }
        return savedBook != null ? new BookResponseEntity("Book SAVED",savedBook, HttpStatus.OK) :
                new BookResponseEntity("Book save CANCELED", HttpStatus.BAD_REQUEST);
    }

    private Author checkIfAuthorExistsOrSaveAuthor(Author author){
        Author savedAuthor = null;
        if(author != null & author.getAuthorId() != null){
            savedAuthor = authorRepository.findById(author.getAuthorId()).orElse(null);
        }
        if(savedAuthor == null & author.getFirstName().length() > 1 & author.getLastName().length() > 1) {
            savedAuthor = authorRepository.save(author);
        }
        return savedAuthor;
    }
    private Book checkIfBookWithIsbnExist(Book book) {
        String isbn = book.getIsbn() != null ? book.getIsbn().replaceAll("[\\s\\-.,]", "") : null;
        return isbn != null ?
                repository.findBookByIsbn(book.getIsbn()).orElse(null) : null;
    }

    public BookResponseEntity updateBook(Long id, Book book){
        Book updatedBook = null;
        boolean bookExists = false;
        if(book != null &  id!= null & book.getBookId()!= null & id == book.getBookId())
            bookExists = repository.existsById(id);
        if(bookExists){
            Author authorOfBook = checkIfAuthorExistsOrSaveAuthor(book.getAuthor());
            book.setAuthor(authorOfBook);
            updatedBook = repository.save(book);
        }
        return updatedBook != null ? new BookResponseEntity("Book UPDATED",updatedBook, HttpStatus.OK) :
                new BookResponseEntity("Book update CANCELED", HttpStatus.BAD_REQUEST);
    }

    public List<Book> findBooksByISBN(String isbn){
        return repository.findBooksByIsbn(isbn);
    }



}
