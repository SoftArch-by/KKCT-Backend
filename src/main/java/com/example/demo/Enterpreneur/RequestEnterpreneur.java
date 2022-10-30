package com.example.demo.Enterpreneur;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Transaction;
import com.example.demo.repositories.TransactionRepository;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@RestController
public class RequestEnterpreneur {
    private final TransactionRepository transactionRepository;

    public static void search(){
        System.out.println("from request");
    }

    
    public RequestEnterpreneur(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    

    @PostMapping("/enterpreuerRequest")
    public String getTransaction(Iterable<String> C_id){

		String txt = "";
        for (Transaction transaction : transactionRepository.findAllById(C_id)){
			txt+=transaction;
            System.out.println(transaction);
		}
        return txt;
    }
}
