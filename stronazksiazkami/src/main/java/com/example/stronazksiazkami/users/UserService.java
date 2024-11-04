package com.example.stronazksiazkami.users;

import java.util.List;

public interface UserService
{
    List<User> getUsers();

    User addNewUser(User user);
    void deleteUser(Integer userId);
    User updateUser(Integer userId, User updateUser);
}
