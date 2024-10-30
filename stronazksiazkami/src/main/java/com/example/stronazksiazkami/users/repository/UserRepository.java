package com.example.stronazksiazkami.users.repository;

import com.example.stronazksiazkami.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
    @Query("SELECT s FROM User s WHERE s.email =?1")
    Optional<User> findUsersByEmail(String email);
}
