package com.example.stronazksiazkami.book.repository;

import com.example.stronazksiazkami.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

    @Repository
    public interface BookRepository extends JpaRepository<Book, Integer>
    {
        // @Query("SELECT s FROM Books s WHERE s.title =?1")
        Optional<Book> findBooksByTitle(String title);
    }

