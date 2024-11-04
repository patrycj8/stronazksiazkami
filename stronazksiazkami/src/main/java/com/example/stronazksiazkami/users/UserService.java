package com.example.stronazksiazkami.users;

import java.util.List;

public interface UserService
{
    List<User> getUsers();

    User addNewUsers(User user);
    void deleteUsers(Integer userId);
    void updateUsers(Integer userId, String name, String email);
}
