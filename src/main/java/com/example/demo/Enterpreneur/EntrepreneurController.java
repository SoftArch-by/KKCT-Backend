package com.example.demo.Enterpreneur;

import java.io.Console;
import java.util.List;
import java.util.Optional;

import com.example.demo.models.*;
import org.bson.json.JsonObject;
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
import com.example.demo.models.RequestLogCustomerEmail;
import com.example.demo.models.Transaction;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.EntrepreneurRepository;
import com.example.demo.repositories.TransactionRepository;
import com.example.demo.repositories.RequestsLogRepository;

@RestController
public class EntrepreneurController {
    private final TransactionRepository requestRepository;
    private final EntrepreneurRepository entrepreneurRepository;
    private final RequestsLogRepository repositoryRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public static void search(){
        System.out.println("from request");
    }

    public EntrepreneurController(TransactionRepository transactionRepository,EntrepreneurRepository entrepreneurRepository,RequestsLogRepository repositoryRepository,CustomerRepository customerRepository) {
        this.requestRepository = transactionRepository;
        this.entrepreneurRepository = entrepreneurRepository;
        this.repositoryRepository = repositoryRepository;
        this.customerRepository = customerRepository;
    }


    @PostMapping("/Register")
    public ResponseEntity<String> EntreprenuerSignup(@RequestBody EntrepreneurSignupForm form ){
        System.out.println(form.getType());
        Entrepreneur e = new Entrepreneur(null,form.getEmail(), form.getOrganizationName(),form.getType());
        if(entrepreneurRepository.findEntrepreneurByEmail(e.getEmail()).isEmpty()){
            entrepreneurRepository.save(e);
            return  ResponseEntity.ok("Sign up success");
        }
        return  ResponseEntity.ok("already have account");
    }

    // @GetMapping("/getRequest/CustomerID")
    // public ResponseEntity<List<Transaction>> FindTransectionByCId(@RequestParam String CustomerID){
    //     return new ResponseEntity<List<Transaction>>(requestRepository.findByCustomerID(CustomerID),HttpStatus.OK);
    // }

    // @GetMapping("/getTransaction")
    // public ResponseEntity<JsonObject> FindTransactionByEmail(@RequestParam String email){
    //     String object_id = customerRepository.findByEmail(email).getId();
    //     List<Transaction> res = requestRepository.findByCustomerID(object_id);
    //     String res_string="";
    //     for (Transaction t:res) {
    //         System.out.println(t);
    //         Entrepreneur findEnt  = entrepreneurRepository.findById(t.getEntrepreneur_ID()).orElseGet(null);
    //         TransactionToReturn reformat = new TransactionToReturn(t.getId(),t.getcustomerID(), findEnt.getOrganizationName() ,t.getTransactionInfo(), t.getDueDate());
    //         res_string += reformat.toString();
    //     }

    //     JsonObject o = new JsonObject(res_string);

    //     return new ResponseEntity<JsonObject>(o, HttpStatus.OK);
    // }
    
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


    @PostMapping("/RequestCredit_CustomerEmail")
    public ResponseEntity<RequestCredit> credit(@RequestBody RequestLogCustomerEmail req){
        Entrepreneur entrepreneur = entrepreneurRepository.findEntrepreneurByEmailAndOrganizationName(req.getEntrepreneur_Email(),req.getEntrepreneur_Name());
        Customer customer = customerRepository.findByEmail(req.getRequest_Customer_Email());

        System.out.print("ent"+entrepreneur);
        System.out.print("cust"+customer);
        if (!entrepreneur.isEmpty() && !customer.isEmpty()){
            //serach id customer from email
            Customer idCustomer = customerRepository.findByEmail(req.getRequest_Customer_Email());
            //search trasaction from id
            ResponseEntity<List<Transaction>> searchTransaction = new ResponseEntity<List<Transaction>>(requestRepository.findByCustomerID(idCustomer.getId()),HttpStatus.OK);
            //calculation credit from transaction
            // List<Transaction> transactionBody = null;
            // List<Entrepreneur> entrepreneursList = null;
            // transactionBody = searchTransaction.getBody();
            // for(Transaction t:transactionBody){
            //     Entrepreneur t_Ent_ID = EntrepreneurRepository.findById(t.getEntrepreneur_ID()).orElseGet(null);
            // }
            ResponseEntity<Credit> credit = new ResponseEntity<Credit>(CalculationCreditForEntreprenneur.calculationCredit(searchTransaction),HttpStatus.OK);

            RequestCredit reCredit = new RequestCredit(credit.getBody(),searchTransaction.getBody());

            RequestLogCustomerEmail requestLog = new RequestLogCustomerEmail(req.getEntrepreneur_Email(),req.getEntrepreneur_Name(), req.getRequest_Customer_Email());
            repositoryRepository.save(requestLog);

            //return credit with transaction
            return new ResponseEntity<RequestCredit>(reCredit,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<RequestCredit>(HttpStatus.BAD_REQUEST);
        }
    }
    

    @PostMapping("/RequestCredit_CustomerCid")
    public ResponseEntity<RequestCredit> credit(@RequestBody RequestLogCustomerCid req){
        Entrepreneur entrepreneur = entrepreneurRepository.findEntrepreneurByEmailAndOrganizationName(req.getEntrepreneur_Email(),req.getEntrepreneur_Name());
        Customer customer = customerRepository.findByCitizenID(req.getRequest_Customer_Cid());

        System.out.print("ent"+entrepreneur);
        System.out.print("cust"+customer);
        if (!entrepreneur.isEmpty() && !customer.isEmpty()){
            //serach id customer from email
            Customer idCustomer = customerRepository.findByCitizenID(req.getRequest_Customer_Cid());
            //search trasaction from id
            ResponseEntity<List<Transaction>> searchTransaction = new ResponseEntity<List<Transaction>>(requestRepository.findByCustomerID(idCustomer.getId()),HttpStatus.OK);
            ResponseEntity<Credit> credit = new ResponseEntity<Credit>(CalculationCreditForEntreprenneur.calculationCredit(searchTransaction),HttpStatus.OK);

            RequestCredit reCredit = new RequestCredit(credit.getBody(),searchTransaction.getBody());

            RequestLogCustomerEmail requestLog = new RequestLogCustomerEmail(req.getEntrepreneur_Email(),req.getEntrepreneur_Name(), req.getRequest_Customer_Cid());
            repositoryRepository.save(requestLog);

            //return credit with transaction
            return new ResponseEntity<RequestCredit>(reCredit,HttpStatus.OK);
        }
        else{
               return new ResponseEntity<RequestCredit>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getCustomer")
    public ResponseEntity<Customer> getCustomer(@RequestParam String email){
        return new ResponseEntity<Customer>(customerRepository.findByEmail(email),HttpStatus.OK);
    }
}
