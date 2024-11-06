package com.example.stronazksiazkami.book;

import com.example.stronazksiazkami.author.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

    @Repository
    public interface BookRepository extends JpaRepository<Book, Integer>
    {
        // @Query("SELECT s FROM Books s WHERE s.title =?1")
        Optional<Book> findBooksByTitle(String title);

        List<Book> findAllByDeletedFalse();
    }

