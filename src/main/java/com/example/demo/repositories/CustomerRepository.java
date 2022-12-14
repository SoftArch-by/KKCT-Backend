package com.example.demo.repositories;
import com.example.demo.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    Customer findByEmail(String Email);
    Customer findByCitizenID(String Cid);
}
