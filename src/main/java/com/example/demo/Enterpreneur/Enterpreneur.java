package com.example.demo.Enterpreneur;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Transaction;
import com.example.demo.repositories.TransactionRepository;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@RestController
public class Enterpreneur {
    private final TransactionRepository transactionRepository;

    public static void search(){
        System.out.println("from serach");
    }

    
    public Enterpreneur(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void findByCustomerID(String CustomerID){
        
    }

    // @GetMapping("/Request/CustomerID")
    // Transaction findtransection(@RequestParam final String CustomerID){
    //     return transactionRepository.findByCustomerID(CustomerID).orElseGet(CustomerID);
    // }
}
