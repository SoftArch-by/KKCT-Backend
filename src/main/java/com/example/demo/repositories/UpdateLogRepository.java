package com.example.demo.repositories;
import com.example.demo.models.UpdateLog;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpdateLogRepository extends MongoRepository<UpdateLog, String> {

}
