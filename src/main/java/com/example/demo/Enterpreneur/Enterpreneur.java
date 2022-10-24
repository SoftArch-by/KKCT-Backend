package com.example.demo.Enterpreneur;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Transaction;
import com.example.demo.repositories.TransactionRepository;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@RestController
public class Enterpreneur {
    private final TransactionRepository transactionRepository;

    
    public Enterpreneur(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    

    @PostMapping("/getTransaction")
    public String getTransaction(Iterable<String> C_id){

		String txt = "";
        for (Transaction transaction : transactionRepository.findAllById(C_id)){
			txt+=transaction;
            System.out.println(transaction);
		}
        return txt;
    }
}
