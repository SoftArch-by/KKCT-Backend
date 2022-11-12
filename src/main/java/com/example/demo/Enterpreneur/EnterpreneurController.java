package com.example.demo.Enterpreneur;

import java.io.Console;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Customer;
import com.example.demo.models.Entrepreneur;
import com.example.demo.models.RequestLog;
import com.example.demo.models.Transaction;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.EntrepreneurRepository;
import com.example.demo.repositories.TransactionRepository;
import com.example.demo.repositories.RequestsLogRepository;

@RestController
public class EnterpreneurController {
    private final TransactionRepository requestRepository;
    private final EntrepreneurRepository entrepreneurRepository;
    private final RequestsLogRepository repositoryRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public static void search(){
        System.out.println("from request");
    }

    
    public EnterpreneurController(TransactionRepository transactionRepository,EntrepreneurRepository entrepreneurRepository,RequestsLogRepository repositoryRepository,CustomerRepository customerRepository) {
        this.requestRepository = transactionRepository;
        this.entrepreneurRepository = entrepreneurRepository;
        this.repositoryRepository = repositoryRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/getRequest/CustomerID")
    public ResponseEntity<List<Transaction>> FindTransectionByCId(@RequestParam String CustomerID){
        return new ResponseEntity<List<Transaction>>(requestRepository.findByCustomerID(CustomerID),HttpStatus.OK);
    }


    @PostMapping("/Request")
    public ResponseEntity<List<Transaction>> Request(@RequestBody RequestLog req){
        Entrepreneur enterp = entrepreneurRepository.findById(req.getEnterpreneurId()).orElseGet(null);
        Customer cust = customerRepository.findById(req.getRequest_customerId()).orElseGet(null);

        System.out.println(enterp);
        System.out.println(cust);
        if (enterp!=null && cust!=null){
            //search trasaction from C_id
            ResponseEntity<List<Transaction>> searchTransaction = new ResponseEntity<List<Transaction>>(requestRepository.findByCustomerID(req.getRequest_customerId()),HttpStatus.OK);
            RequestLog requestLog = new RequestLog(req.getEnterpreneurId(), req.getRequest_customerId());
            repositoryRepository.save(requestLog);

            //calculation credit from transaction

            //return transaction with credit grade
            return searchTransaction;
        }
        else{
               return null;
        }

        // test case
        // {
        //     "enterpreneurId": "6366aace59f77a088ba81546",
        //     "Request_customerId": "6362860320fad745b2054961"
        // }
            
    }

    @PostMapping("/credit")
    public ResponseEntity<String> credit(@RequestBody RequestLog req){
        Optional<Entrepreneur> enterp = entrepreneurRepository.findById(req.getEnterpreneurId());
        Optional<Customer> cust = customerRepository.findById(req.getRequest_customerId());

        // System.out.println(enterp);
        // System.out.println(cust);
        if (!enterp.isEmpty() && !cust.isEmpty()){
            //search trasaction from C_id
            ResponseEntity<List<Transaction>> searchTransaction = new ResponseEntity<List<Transaction>>(requestRepository.findByCustomerID(req.getRequest_customerId()),HttpStatus.OK);
            //calculation credit from transaction
            String credit = CalculationCreditForEnterprenneur.calculationCredit(searchTransaction);

            //return credit with transaction   
            return new ResponseEntity<String>(credit + "," + searchTransaction,HttpStatus.OK);
        }
        else{
               return new ResponseEntity<String>("Not have this enterpreneur_ID or Customer_ID",HttpStatus.OK);
        }
    }
    
}
