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
    //Add - dziala
    @PostMapping
    public ResponseEntity<User> registerNewUser(@RequestBody User user, @RequestParam String loggedInUserEmail)
    {
        User savedUser = usersService.addNewUser(user, loggedInUserEmail);
        return ResponseEntity.ok(savedUser);
    }

    //isAdmin - zmienia wartosc
    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Integer userId, @RequestParam String loggedInUserEmail) {
        usersService.deleteUser(userId, loggedInUserEmail);
    }

    //dziala zmienia wartosc na true
    @PutMapping(path = "/restore/{userId}")
    public ResponseEntity<Void> restoreUser(@PathVariable("userId") Integer userId, @RequestParam String loggedInUserEmail) {
        usersService.restoreUser(userId, loggedInUserEmail);
        return ResponseEntity.ok().build();
    }

    //Update - dziala
    @PutMapping(path = "/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") Integer userId,
                                           @RequestBody User updateUser,
                                           @RequestParam String loggedInUserEmail) {
        User updatedUser = usersService.updateUser(userId, updateUser, loggedInUserEmail);
        return ResponseEntity.ok(updatedUser);
    }
}
