package com.example.stronazksiazkami.publisher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer>
{
   // @Query("SELECT s FROM Publisher s WHERE s.name = ?1")
    Optional<Publisher> findPublishersByName(String name);

    //@Query("SELECT s FROM Publisher s WHERE s.phone =?1")
    Optional<Publisher> findPublishersByPhone(String phone);
}
