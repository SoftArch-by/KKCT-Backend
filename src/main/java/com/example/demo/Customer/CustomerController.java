package com.example.demo.Customer;

import java.util.List;

import com.example.demo.Enterpreneur.CalculationCreditForEntreprenneur;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Credit;
import com.example.demo.models.Customer;
import com.example.demo.models.RequestCredit;
import com.example.demo.models.Transaction;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.EntrepreneurRepository;
import com.example.demo.repositories.TransactionRepository;
import com.example.demo.repositories.RequestsLogRepository;
@RestController
public class CustomerController {
    private final TransactionRepository requestRepository;
    private final CustomerRepository customerRepository;

    public CustomerController(TransactionRepository transactionRepository,EntrepreneurRepository entrepreneurRepository,RequestsLogRepository repositoryRepository,CustomerRepository customerRepository) {
        this.requestRepository = transactionRepository;
        this.customerRepository = customerRepository;
    }
    @PostMapping("/RequestCredit_fromCustomer")
    public ResponseEntity<RequestCredit> reqFromCustomer(@RequestParam String email){
        Customer customer = customerRepository.findByEmail(email);

        System.out.print("cust"+customer);
        if (!customer.isEmpty()){
            //serach id customer from email
            Customer idCustomer = customerRepository.findByEmail(email);
            //search trasaction from id
            ResponseEntity<List<Transaction>> searchTransaction = new ResponseEntity<List<Transaction>>(requestRepository.findByCustomerID(idCustomer.getId()),HttpStatus.OK);

            ResponseEntity<Credit> credit = new ResponseEntity<Credit>(CalculationCreditForEntreprenneur.calculationCredit(searchTransaction),HttpStatus.OK);

            RequestCredit reCredit = new RequestCredit(credit.getBody(),searchTransaction.getBody());

            //return credit with transaction
            return new ResponseEntity<RequestCredit>(reCredit,HttpStatus.OK);
        }
        else{
               return new ResponseEntity<RequestCredit>(HttpStatus.BAD_REQUEST);
        }
    }
}