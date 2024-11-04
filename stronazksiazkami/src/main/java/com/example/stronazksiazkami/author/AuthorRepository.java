package com.example.stronazksiazkami.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

    @Repository
    public interface AuthorRepository extends JpaRepository<Author, Integer>
    {
        //@Query("SELECT s FROM Author s WHERE s.surname =?1")
        Optional<Author> findAuthorsBySurname(String surname);

        //List<Authors> findAllByIsDeletedFalse();
    }

