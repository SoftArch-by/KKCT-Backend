package com.example.demo.services;

import com.example.demo.models.Role;
import com.example.demo.models.User;

import java.util.List;

public interface UserService {
    User addUser(User user);

    Role saveRole(Role role);

    void addRoleToUser(String username, String role);

    User getUser(String username);

    List<User>getUsers();
}
