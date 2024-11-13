package com.example.stronazksiazkami.users;

import com.example.stronazksiazkami.model.AuthenticatedUser;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        Boolean isAdmin = userOptional.map(User::getIsAdmin).orElse(false);
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
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer userId, String loggedInUserEmail) {
        if (!isAdmin(loggedInUserEmail)) {
            throw new SecurityException("Only admin users can delete users");
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("user not found"));
        user.setDeleted(true);
        userRepository.save(user);
    }

    public void restoreUser(Integer userId, String loggedInUserEmail) {
        if (!isAdmin(loggedInUserEmail)) {
            throw new SecurityException("Only admin users can restore users");
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("user not found"));
        user.setDeleted(false);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(Integer userId, User updateUser, String loggedInUserEmail) {
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userRepository.findUsersByName(username);
        AuthenticatedUser user = new AuthenticatedUser(u);
        return new AuthenticatedUser(u);
    }
}