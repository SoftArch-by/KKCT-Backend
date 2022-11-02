package com.example.demo.AccountManangement;

import com.example.demo.models.Customer;
import com.example.demo.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerAccount {
    private final CustomerRepository customerRepository;
    @Autowired
    public CustomerAccount (CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    // http://localhost:8093/Customer/findByEmail?email=someEmail
    @GetMapping("/Customer/findByEmail")
    public Customer getCustomer(@RequestParam String email){
        //customerRepository.save
        return customerRepository.findCustomerByEmail(email);
    }

    @PostMapping("/Customer/register")
    public Customer save(@RequestBody Customer customer){
        //เช็คว่า email และ citizenID ไม่ซ้ำ
        //ใช้ bcrypt hash password
        //สร้าง customer ใหม่ +save
        return customerRepository.save(customer);
    }

    // http://localhost:8093/Customer/login?email=someEmail&password=somePassword
    @GetMapping("/Customer/login")
    public String login(@RequestParam String email , @RequestParam String password){
        //hash password ด้วย bcypto
        //เช็ค email and password
        // return token
        return "0";
    }

}
