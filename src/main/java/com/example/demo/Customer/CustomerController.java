package com.example.demo.Customer;

import java.util.List;

import com.example.demo.models.*;
import org.bson.json.JsonObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Transaction;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.EntrepreneurRepository;
import com.example.demo.repositories.TransactionRepository;
@RestController
public class CustomerController {
    private final TransactionRepository requestRepository;
    private final CustomerRepository customerRepository;
    private final EntrepreneurRepository entrepreneurRepository;

    public CustomerController(TransactionRepository transactionRepository,EntrepreneurRepository entrepreneurRepository,CustomerRepository customerRepository) {
        this.requestRepository = transactionRepository;
        this.entrepreneurRepository = entrepreneurRepository;
        this.customerRepository = customerRepository;
    }
    @GetMapping("/getTransaction")
    public ResponseEntity<JsonObject> FindTransactionByEmail(@RequestParam String email){
        String object_id = customerRepository.findByEmail(email).getId();
        List<Transaction> res = requestRepository.findByCustomerID(object_id);
        String res_string="";
        for (Transaction t:res) {
            System.out.println(t);
            Entrepreneur findEnt  = entrepreneurRepository.findById(t.getEntrepreneur_ID()).orElseGet(null);
            TransactionToReturn reformat = new TransactionToReturn(t.getId(),t.getcustomerID(), findEnt.getOrganizationName() ,t.getTransactionInfo(), t.getDueDate());
            res_string += reformat.toString();
        }

        JsonObject o = new JsonObject(res_string);

        return new ResponseEntity<JsonObject>(o, HttpStatus.OK);
    }
}