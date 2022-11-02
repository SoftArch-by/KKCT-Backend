package com.example.demo.Enterpreneur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Transaction;
import com.example.demo.repositories.TransactionRepository;

@RestController
public class EnterpreneurController {

    @Autowired
    private final TransactionRepository requestRepository;

    public static void search(){
        System.out.println("from request");
    }

    
    public EnterpreneurController(TransactionRepository transactionRepository) {
        this.requestRepository = transactionRepository;
    }

    

    @GetMapping("/Request/CustomerID")
    public ResponseEntity<List<Transaction>> findtransection(@RequestParam String CustomerID){
        // return transactionRepository.findByCustomerID(CustomerID).orElseGet(CustomerID);
        return new ResponseEntity<List<Transaction>>(requestRepository.findByCustomerID(CustomerID),HttpStatus.OK);

    }
}
