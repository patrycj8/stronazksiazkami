package com.example.stronazksiazkami.users;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User addNewUsers(User user) {
        Optional<User> userOptional = userRepository.findUsersByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new IllegalArgumentException("email exists");
        }
        String encryptedPassword = simpleEncryptPassword(user.getPassword());
        user.setPassword(encryptedPassword);
        //User savedUser = userRepository.save(user);
        return userRepository.save(user);
    }

    public void deleteUsers(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("user with id " + userId + " does not exist");
        }
        userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUsers(Integer userId, String name, String email) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("user with id " + userId + " does not exist"));
        if (name != null && !name.isEmpty() && !Objects.equals(user.getName(), name)) {
            user.setName(name);
        }
        if (email != null && !email.isEmpty() && !Objects.equals(user.getEmail(), email)) {
            if (userRepository.findUsersByEmail(email).isPresent()) {
                throw new IllegalArgumentException("email exists");
            }
            user.setEmail(email);
        }
    }
    private String simpleEncryptPassword(String password) {
        StringBuilder encrypted = new StringBuilder();
        int shift = 5;

        for (char e : password.toCharArray()) {
            if (Character.isLetter(e)) {
                char base = Character.isLowerCase(e) ? 'a' : 'A';
                e = (char) ((e - base + shift) % 26 + base);
            }
            encrypted.append(e);
        }
        return encrypted.toString();
    }
}
