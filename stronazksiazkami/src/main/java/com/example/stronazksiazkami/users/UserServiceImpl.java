package com.example.stronazksiazkami.users;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User addNewUser(User user) {
        Optional<User> userOptional = userRepository.findUsersByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new IllegalArgumentException("email exists");
        }
        String encryptedPassword = simpleEncryptPassword(user.getPassword());
        user.setPassword(encryptedPassword);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("user with id " + userId + " does not exist");
        }
        userRepository.deleteById(userId);
    }

    @Override
    @Transactional
    public User updateUser(Integer userId, User updateUser) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + userId + " does not exist"));

        existingUser.setLogin(updateUser.getLogin());
        existingUser.setPassword(updateUser.getPassword());
        existingUser.setName(updateUser.getName());
        existingUser.setSurname(updateUser.getSurname());
        existingUser.setPhone(updateUser.getPhone());
        existingUser.setEmail(updateUser.getEmail());
        existingUser.setAddress(updateUser.getAddress());existingUser.setAge(updateUser.getAge());

        return userRepository.save(existingUser);
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
