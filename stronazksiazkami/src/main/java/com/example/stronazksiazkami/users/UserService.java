package com.example.stronazksiazkami.users;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    User addNewUsers(User users);
    void deleteUsers(Integer usersId);
    void updateUsers(Integer usersId, String name, String email);
}
