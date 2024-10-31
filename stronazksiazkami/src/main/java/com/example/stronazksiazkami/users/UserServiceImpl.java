package com.example.stronazksiazkami.users;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl {

    private final UserRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository usersResposiory, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersResposiory;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getUsers() {
        return usersRepository.findAll();
    }

    public User addNewUsers(User users) {
        Optional<User> usersOptional = usersRepository.findUsersByEmail(users.getEmail());
        if (usersOptional.isPresent()) {
            throw new IllegalArgumentException("email exists");
        }
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        return usersRepository.save(users);
    }

    public void deleteUsers(Integer usersId) {
        boolean exists = usersRepository.existsById(usersId);
        if (!exists) {
            throw new IllegalArgumentException("user with id " + usersId + " does not exist");
        }
        usersRepository.deleteById(usersId);
    }

    public boolean isAdmin(String email) {
        Optional<User> usersOptional = usersRepository.findUsersByEmail(email);
        return usersOptional.isPresent() && usersOptional.get().getIsAdmin();
    }

    @Transactional
    public void updateUsers(Integer usersId, String name, String email) {
        User users = usersRepository.findById(usersId).orElseThrow(() -> new IllegalArgumentException("users with id " + usersId + " does not exist"));
        if (name != null && name.length() > 0 && !Objects.equals(users.getName(), name)) {
            users.setName(name);
        }
        if (email != null && email.length() > 0 && !Objects.equals(users.getEmail(), email)) {
            Optional<User> usersOptional = usersRepository.findUsersByEmail(email);
            if (usersOptional.isPresent()) {
                throw new IllegalArgumentException("email exists");
            }
            users.setEmail(email);
        }
    }
}
