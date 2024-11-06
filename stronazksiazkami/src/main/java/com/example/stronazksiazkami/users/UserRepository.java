package com.example.stronazksiazkami.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //@Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findUsersByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.deleted = false")
    List<User> findAllActiveUsers();
}
