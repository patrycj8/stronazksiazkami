package com.example.stronazksiazkami.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Optional<Book> findBooksByIsbn(String isbn);

    List<Book> findAllByDeletedFalse();
}

