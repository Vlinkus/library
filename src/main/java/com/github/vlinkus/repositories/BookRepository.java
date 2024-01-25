package com.github.vlinkus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.github.vlinkus.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
