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

import com.example.demo.models.Credit;
import com.example.demo.models.Customer;
import com.example.demo.models.Entrepreneur;
import com.example.demo.models.RequestCredit;
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


    // @PostMapping("/Request")
    // public ResponseEntity<List<Transaction>> Request(@RequestBody RequestLog req){
    //     Entrepreneur enterp = entrepreneurRepository.findById(req.getEnterpreneurId()).orElseGet(null);
    //     Customer cust = customerRepository.findById(req.getRequest_Customer_Cid()).orElseGet(null);

    //     System.out.println(enterp);
    //     System.out.println(cust);
    //     if (enterp!=null && cust!=null){
    //         //search trasaction from C_id
    //         ResponseEntity<List<Transaction>> searchTransaction = new ResponseEntity<List<Transaction>>(requestRepository.findByCustomerID(req.getRequest_Customer_Cid()),HttpStatus.OK);
    //         RequestLog requestLog = new RequestLog(req.getEnterpreneurId(), req.getRequest_Customer_Cid());
    //         repositoryRepository.save(requestLog);

    //         //calculation credit from transaction

    //         //return transaction with credit grade
    //         return searchTransaction;
    //     }
    //     else{
    //            return null;
    //     }

    //     // test case
    //     // {
    //     //     "enterpreneurId": "6366aace59f77a088ba81546",
    //     //     "Request_Customer_Cid": "6362860320fad745b2054961"
    //     // }
            
    // }

    @PostMapping("/RequestCredit")
    public ResponseEntity<RequestCredit> credit(@RequestBody RequestLog req){
        Entrepreneur enterpreneur = entrepreneurRepository.findEntrepreneurByEmailAndOrganizationName(req.getEnterpreneur_Email(),req.getEnterpreneur_Name());
        Customer customer = customerRepository.findByCitizenID(req.getRequest_Customer_Cid());

        System.out.print("ent"+enterpreneur);
        System.out.print("cust"+customer);
        if (!enterpreneur.isEmpty() && !customer.isEmpty()){
            //serach id customer from citizen id
            Customer idCustomer = customerRepository.findByCitizenID(req.getRequest_Customer_Cid());
            //search trasaction from id
            ResponseEntity<List<Transaction>> searchTransaction = new ResponseEntity<List<Transaction>>(requestRepository.findByCustomerID(idCustomer.getId()),HttpStatus.OK);
            //calculation credit from transaction
            ResponseEntity<Credit> credit = new ResponseEntity<Credit>(CalculationCreditForEnterprenneur.calculationCredit(searchTransaction),HttpStatus.OK);

            RequestCredit reCredit = new RequestCredit(credit.getBody(),searchTransaction.getBody());

            RequestLog requestLog = new RequestLog(req.getEnterpreneur_Email(),req.getEnterpreneur_Name(), req.getRequest_Customer_Cid());
            repositoryRepository.save(requestLog);

            //return credit with transaction
            return new ResponseEntity<RequestCredit>(reCredit,HttpStatus.OK);
        }
        else{
               return new ResponseEntity<RequestCredit>(HttpStatus.BAD_REQUEST);
        }
    }
    
}
