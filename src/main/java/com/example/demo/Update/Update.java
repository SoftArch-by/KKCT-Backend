package com.example.demo.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
    // @PostMapping("/getTransaction")
    // public String getTransaction(Id T_id){
	// 	String t = "";
    //     for (Transaction transaction : transactionRepository.findAll()){
	// 		t+=transaction;
    //         System.out.println(transaction);
	// 	}
    //     return t;
    // }

    @PostMapping("/createTransaction_CustomerCid")
    public ResponseEntity<Transaction> createTransactionCid(@RequestBody createTransactionCid ct){
        Customer customer = customerRepository.findByCitizenID(ct.getCitizen_id());
        String customerID = customer.getId();
        System.out.println(ct.getEmail());
        System.out.println(ct.getOrganizationName());
        Entrepreneur entrepreneur = entrepreneurRepository.findEntrepreneurByEmailAndOrganizationName(ct.getEmail(),ct.getOrganizationName());
        String entrepreneurID = entrepreneur.getId();
        if (customerID == null ||entrepreneurID == null) {return new ResponseEntity<Transaction>(HttpStatus.NOT_FOUND);}
        Transaction t = new Transaction(customerID, entrepreneurID, ct.getMoney(), ct.getDueDate());
        return new ResponseEntity<Transaction>(transactionRepository.save(t),HttpStatus.OK);
    }

    @PostMapping("/createTransaction_CustomerEmail")
    public ResponseEntity<Transaction> createTransactionEmail(@RequestBody createTransactionEmail ct){
        Customer customer = customerRepository.findByEmail(ct.getC_email());
        String customerID = customer.getId();
        System.out.println(ct.getEmail());
        System.out.println(ct.getOrganizationName());
        Entrepreneur entrepreneur = entrepreneurRepository.findEntrepreneurByEmailAndOrganizationName(ct.getEmail(),ct.getOrganizationName());
        String entrepreneurID = entrepreneur.getId();
        if (customerID == null ||entrepreneurID == null) {return new ResponseEntity<Transaction>(HttpStatus.NOT_FOUND);}
        Transaction t = new Transaction(customerID, entrepreneurID, ct.getMoney(), ct.getDueDate());
        return new ResponseEntity<Transaction>(transactionRepository.save(t),HttpStatus.OK);
    }

    // @GetMapping("/getTransaction/{TransactionId}")
    // Transaction findtransection(@PathVariable final String TransactionId){
    //     return transactionRepository.findById(TransactionId).orElseGet(null);
    // }

    @PostMapping("/update")
    public ResponseEntity<Transaction> updateTransaction(@RequestBody updateRequest req){
        Transaction transaction = transactionRepository.findById(req.getID()).get();
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
}
