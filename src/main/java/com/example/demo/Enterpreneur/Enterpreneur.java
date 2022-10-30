package com.example.demo.Enterpreneur;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    

    // @PostMapping("/enterpreuerRequest")
    // public String getTransaction(Iterable<String> C_id){

	// 	String txt = "";
    //     for (Transaction transaction : transactionRepository.findAllById(C_id)){
	// 		txt+=transaction;
    //         System.out.println(transaction);
	// 	}
    //     return txt;
    // }

    @GetMapping("/Request/{CustomerID}")
    public List<Transaction> findtransection(@PathVariable final String CustomerID){
        try{
            return transactionRepository.findByCustomerID(CustomerID);
        }catch(Exception e){
            return null;
        }
        // String t = "";
        // for (Transaction transaction : transactionRepository.findByC_Id(C_Id)){
		// 	t+=transaction;
        //     System.out.println(transaction);
		// }
        // return t;
    }
}
