package com.example.demo.Update;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Transaction> createTransaction(@RequestBody createTransaction ct){
        Customer customer = customerRepository.findByCitizenID(ct.getCitizen_id()).orElseGet(null);
        String customerID = customer.getId();
        Entrepreneur entrepreneur = entrepreneurRepository.findEntrepreneurByEmailAndOrganizationName(ct.getEmail(),ct.getOrganizationName()).orElseGet(null);
        String entrepreneurID = entrepreneur.getId();
        if (customerID == null ||entrepreneurID == null) {return new ResponseEntity<Transaction>(HttpStatus.NOT_FOUND);}
        Transaction t = new Transaction(customerID, entrepreneurID, ct.getMoney(), ct.getDueDate());
        return new ResponseEntity<Transaction>(transactionRepository.save(t),HttpStatus.OK);
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
    //http://localhost:8093/update
    @PostMapping("/update")
    public ResponseEntity<Transaction> updateTransaction(@RequestBody updateRequest req){
        Transaction transaction = transactionRepository.findById(req.getID()).get().orElseGet(null);
        if (transaction == null){return new ResponseEntity<Transaction>(HttpStatus.NOT_FOUND);}
        if (transaction.getUnpaid() - req.getPaid() < 0){return new ResponseEntity<Transaction>(HttpStatus.CONFLICT);}
        else{
            transaction.setUnpaid(transaction.getUnpaid() - req.getPaid()); 
            UpdateLog updateLog = new UpdateLog(transaction.getId(), req.getPaid());
            transactionRepository.save(transaction);
            updateLogRepository.save(updateLog);
        }
        return new ResponseEntity<Transaction>(transaction,HttpStatus.OK);
    }

    @PostMapping("/testUpdateLog")
    UpdateLog test(@RequestBody UpdateLog updateLog){
        return updateLogRepository.save(updateLog);
    }

}
