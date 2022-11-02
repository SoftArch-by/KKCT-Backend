package com.example.demo.AccountManangement;

import com.example.demo.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerAccount {
    private final CustomerRepository customerRepository;
    @Autowired
    public CustomerAccount (CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }


}
