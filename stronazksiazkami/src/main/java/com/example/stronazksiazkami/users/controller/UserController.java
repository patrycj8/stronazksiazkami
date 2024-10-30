package com.example.stronazksiazkami.users.controller;

import com.example.stronazksiazkami.users.model.User;
import com.example.stronazksiazkami.users.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")

public class UserController
{
    private final UserServiceImpl usersService;

    @Autowired
    public UserController(UserServiceImpl usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public List<User> getUsers()
    {
        return usersService.getUsers();
    }

    @PostMapping
    public ResponseEntity<User> registerNewUsers(@RequestBody User users)
    {
        User savedUser = usersService.addNewUsers(users);
        return ResponseEntity.ok(savedUser);
    }

    @DeleteMapping(path = "{usersId}")
    public void deleteUsers(@PathVariable("usersId") Integer usersId)
    {
        usersService.deleteUsers(usersId);
    }

    @PutMapping(path = "/{usersId}")
    public void updateAuthors(@PathVariable("usersId") Integer usersId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email) {
        usersService.updateUsers(usersId, name, email);
    }
}
