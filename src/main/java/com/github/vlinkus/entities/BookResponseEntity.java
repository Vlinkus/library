package com.github.vlinkus.entities;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class BookResponseEntity extends ResponseEntity<Book> {

    private String message;
    public BookResponseEntity(String message, Book body, HttpStatusCode status) {
        super(body, status);
        this.message = message;
    }

    public BookResponseEntity(String message, HttpStatusCode status) {
        super(status);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
