package com.example.demo.repositories;
import com.example.demo.models.RequestLogCustomerEmail;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestsLogRepository extends MongoRepository<RequestLogCustomerEmail , String>{
    
}
