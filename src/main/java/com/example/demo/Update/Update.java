package com.example.demo.Update;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PatchMapping;

import com.example.demo.models.Transaction;
import com.example.demo.repositories.TransactionRepository;
import com.example.demo.models.UpdateLog;
import com.example.demo.repositories.UpdateLogRepository;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@RestController
public class Update {
    private final TransactionRepository transactionRepository;
    private final UpdateLogRepository updateLogRepository;

    @Autowired
	public Update(TransactionRepository transactionRepository,UpdateLogRepository updateLogRepository){
		this.transactionRepository = transactionRepository;
        this.updateLogRepository = updateLogRepository;
	}
    @Autowired
    public static void update(){
        System.out.println("from update");
    }

    @GetMapping("/helloworld")
    public String hello(){
        return "Hello World";
    }

    @PostMapping("/getTransaction")
    public String getTransaction(Id T_id){
		String t = "";
        for (Transaction transaction : transactionRepository.findAll()){
			t+=transaction;
            System.out.println(transaction);
		}
        return t;
    }
    
    @PostMapping("/dummyTransaction")
    Transaction dummyTransaction(@RequestBody Transaction transaction){
        return transactionRepository.save(transaction);
    }
    
    @GetMapping("/getTransaction/{TransactionId}")
    Transaction findtransection(@PathVariable final String TransactionId){
        return transactionRepository.findById(TransactionId).orElseGet(null);
    }

    
    @PutMapping("/update")
    Transaction updateTransaction(@RequestBody updateRequest req){
        Transaction transaction = transactionRepository.findById(req.getID()).get();
        transaction.setUnpaid(transaction.getUnpaid() - req.getPaid());

        transactionRepository.save(transaction);
        return transaction;
    }

    @PostMapping("/testUpdateLog")
    UpdateLog test(@RequestBody UpdateLog updateLog){
        return updateLogRepository.save(updateLog);
    }

}
