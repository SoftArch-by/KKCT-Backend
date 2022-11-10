package com.example.demo.services;

import com.example.demo.models.Customer;
import com.example.demo.models.Role;

import java.util.List;

public interface UserService {
    Customer saveUser(Customer customer);

    Role saveRole(Role role);

    void addRoleToUser(String email, String role);

    Customer getUser(String email);

    List<Customer>getUsers();
}
