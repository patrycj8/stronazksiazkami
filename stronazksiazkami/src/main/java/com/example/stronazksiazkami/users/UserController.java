package com.example.stronazksiazkami.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    private final UserServiceImpl usersService;

    @Autowired
    public UserController(UserServiceImpl usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public List<User> getUsers() {
        return usersService.getUsers();
    }

    @PostMapping
    public ResponseEntity<User> registerNewUser(@RequestBody User user, @RequestParam String loggedInUserEmail) {
        User savedUser = usersService.addNewUser(user, loggedInUserEmail);
        return ResponseEntity.ok(savedUser);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Integer userId, @RequestParam String loggedInUserEmail) {
        usersService.deleteUser(userId, loggedInUserEmail);
    }

    @PutMapping(path = "/restore/{userId}")
    public ResponseEntity<Void> restoreUser(@PathVariable("userId") Integer userId, @RequestParam String loggedInUserEmail) {
        usersService.restoreUser(userId, loggedInUserEmail);
        return ResponseEntity.ok().build();
    }


    @PutMapping(path = "/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") Integer userId,
                                           @RequestBody User updateUser,
                                           @RequestParam String loggedInUserEmail) {
        User updatedUser = usersService.updateUser(userId, updateUser, loggedInUserEmail);
        return ResponseEntity.ok(updatedUser);
    }
}
