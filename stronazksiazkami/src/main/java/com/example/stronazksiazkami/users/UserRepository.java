
package com.example.stronazksiazkami.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUsersByEmail(String email);


    //List<User> findAllBy();

    User findUsersByName(String name);
}
