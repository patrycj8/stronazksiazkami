package com.example.stronazksiazkami.publisher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
    Optional<Publisher> findPublishersByName(String name);

    Optional<Publisher> findPublishersByPhone(String phone);
}
