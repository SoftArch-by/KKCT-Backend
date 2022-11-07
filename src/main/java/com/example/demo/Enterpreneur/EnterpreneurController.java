package com.example.demo.Enterpreneur;

import java.io.Console;
import java.util.List;

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

    // @GetMapping("/getRequest/Ent")
    // public ResponseEntity<List<Entrepreneur>> test(@RequestBody RequestLog req){
    //     return new ResponseEntity<List<Entrepreneur>>(entrepreneurRepository.findRequestEmailAndName(req.getEmailEntrepreuer(), req.getOrganizationName()),HttpStatus.OK);
    // }

    @PostMapping("/Request")
    public ResponseEntity<List<Transaction>> Request(@RequestBody RequestLog req){
        Entrepreneur enterp = entrepreneurRepository.findEntrepreneurByEmailAndOrganizationName(req.getEmailEntrepreuer(), req.getOrganizationName());
        Customer cust = customerRepository.findCustomerBycitizenID(req.getRequest_customerId());

        System.out.println(enterp);
        if (enterp!=null && cust!=null){
            ResponseEntity<List<Transaction>> findCid = new ResponseEntity<List<Transaction>>(requestRepository.findByCustomerID(req.getRequest_customerId()),HttpStatus.OK);
            RequestLog requestLog = new RequestLog(req.getEmailEntrepreuer(), req.getOrganizationName(), req.getRequest_customerId());
            repositoryRepository.save(requestLog);

            return findCid;
        }
        else{
            return null;
        }
            
    }
    
}
