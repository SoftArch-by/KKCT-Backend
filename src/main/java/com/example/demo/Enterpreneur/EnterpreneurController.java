package com.example.demo.Enterpreneur;

import java.io.Console;
import java.util.List;
import java.util.Optional;

import com.example.demo.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("/Register")
    public ResponseEntity<String> EntreprenuerSignup(@RequestBody EntrepreneurSignupForm form ){
        System.out.println(form.getType());
        Entrepreneur e = new Entrepreneur(null,form.getEmail(), form.getOrganizationName(),form.getType());
        entrepreneurRepository.save(e);
        return  ResponseEntity.ok("Sign up success");
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
            ResponseEntity<List<Transaction>> findCid = new ResponseEntity<List<Transaction>>(requestRepository.findByCustomerID(req.getRequest_customerId()),HttpStatus.OK);
            RequestLog requestLog = new RequestLog(req.getEnterpreneurId(), req.getRequest_customerId());
            repositoryRepository.save(requestLog);

            return findCid;
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
    
}
