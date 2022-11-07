package com.example.demo.repositories;
import com.example.demo.models.RequestLog;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestsLogRepository extends MongoRepository<RequestLog , String>{
    
}
