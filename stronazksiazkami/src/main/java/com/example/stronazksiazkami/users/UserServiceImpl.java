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

    public boolean isAdmin(String userEmail) {
        Optional<User> userOptional = userRepository.findUsersByEmail(userEmail);
        boolean isAdmin = userOptional.map(User::isAdmin).orElse(false);
        System.out.println("Checking if user is admin: " + userEmail + " - isAdmin: " + isAdmin);
        return isAdmin;
    }

    @Override
    public User addNewUser(User user, String loggedInUserEmail) {
        if (!isAdmin(loggedInUserEmail)) {
            throw new SecurityException("Only admin users can add new users");
        }
        Optional<User> userOptional = userRepository.findUsersByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new IllegalArgumentException("email exists");
        }
        String encryptedPassword = simpleEncryptPassword(user.getPassword());
        user.setPassword(encryptedPassword);

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer userId,String loggedInUserEmail) {
        if(!isAdmin(loggedInUserEmail)) {
            throw new SecurityException("Only admin users can delete users");
        }
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("user with id " + userId + " does not exist");
        }
        userRepository.deleteById(userId);
    }

    @Override
    @Transactional
    public User updateUser(Integer userId, User updateUser, String loggedInUserEmail)
    {
        if (!isAdmin(loggedInUserEmail)) {
            throw new SecurityException("Only admin users can update user");
        }
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + userId + " does not exist"));
        if (updateUser.getLogin() != null) {
            existingUser.setLogin(updateUser.getLogin());
        }
        if (updateUser.getPassword() != null) {
            existingUser.setPassword(updateUser.getPassword());
        }
        if (updateUser.getName() != null) {
            existingUser.setName(updateUser.getName());
        }
        if (updateUser.getSurname() != null) {
            existingUser.setSurname(updateUser.getSurname());
        }
        if (updateUser.getPhone() != null) {
            existingUser.setPhone(updateUser.getPhone());
        }
        if (updateUser.getEmail() != null) {
            existingUser.setEmail(updateUser.getEmail());
        }
        if (updateUser.getAddress() != null) {
            existingUser.setAddress(updateUser.getAddress());
        }
        if (updateUser.getAge() != null) {
            existingUser.setAge(updateUser.getAge());
        }
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
