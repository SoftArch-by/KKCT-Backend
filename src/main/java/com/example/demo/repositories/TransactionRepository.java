package com.example.demo.repositories;
import com.example.demo.models.Transaction;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {

    List <Transaction> findByCustomerID(String CustomerID);



}
