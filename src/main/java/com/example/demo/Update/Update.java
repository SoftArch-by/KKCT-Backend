package com.example.demo.Update;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PatchMapping;

import com.example.demo.models.Customer;
import com.example.demo.models.Entrepreneur;
import com.example.demo.models.Transaction;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.EntrepreneurRepository;
import com.example.demo.repositories.TransactionRepository;
import com.example.demo.models.UpdateLog;
import com.example.demo.repositories.UpdateLogRepository;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@RestController
public class Update {
    private final TransactionRepository transactionRepository;
    private final UpdateLogRepository updateLogRepository;
    private final CustomerRepository customerRepository;
    private final EntrepreneurRepository entrepreneurRepository;

    @Autowired
	public Update(TransactionRepository transactionRepository,UpdateLogRepository updateLogRepository,CustomerRepository customerRepository,EntrepreneurRepository entrepreneurRepository){
		this.transactionRepository = transactionRepository;
        this.updateLogRepository = updateLogRepository;
        this.customerRepository = customerRepository;
        this.entrepreneurRepository = entrepreneurRepository;
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
    @PostMapping("/dummyEntrepreneur")
    Entrepreneur dummyEntrepreneur(@RequestBody Entrepreneur e){
        return entrepreneurRepository.save(e);
    }
    @PostMapping("/createTransaction")
    Transaction createTransaction(@RequestBody createTransaction ct){
        String customerID = customerRepository.findByCitizenID(ct.getCitizen_id()).getId();
        String entrepreneurID = entrepreneurRepository.findEntrepreneurByEmailAndOrganizationName(ct.getEmail(),ct.getOrganizationName()).getId();
        Transaction t = new Transaction(customerID, entrepreneurID, ct.getMoney(), ct.getDueDate());
        return transactionRepository.save(t);
    }
    /*ลองหา data จาก citizeniD */
    @GetMapping("/CustomerID/findBycitizenID")
    public Customer getCustomerID(@RequestParam String citizenID){
        return customerRepository.findByCitizenID(citizenID);
    }
    @GetMapping("/getTransaction/{TransactionId}")
    Transaction findtransection(@PathVariable final String TransactionId){
        return transactionRepository.findById(TransactionId).orElseGet(null);
    }
    
    @PostMapping("/update")
    Transaction updateTransaction(@RequestBody updateRequest req){
        Transaction transaction = transactionRepository.findById(req.getID()).get();
        transaction.setUnpaid(transaction.getUnpaid() - req.getPaid()); 
        UpdateLog updateLog = new UpdateLog(transaction.getId(), req.getPaid());
        transactionRepository.save(transaction);
        updateLogRepository.save(updateLog);
        return transaction;
    }

    @PostMapping("/testUpdateLog")
    UpdateLog test(@RequestBody UpdateLog updateLog){
        return updateLogRepository.save(updateLog);
    }

}
