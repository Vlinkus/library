package com.github.vlinkus.entities;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class AuthorResponseEntity extends ResponseEntity<Author> {

    private String message;
    public AuthorResponseEntity(String message, Author body, HttpStatusCode status) {
        super(body, status);
        this.message = message;
    }

    public AuthorResponseEntity(String message, HttpStatusCode status) {
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
