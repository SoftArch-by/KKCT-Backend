package com.example.demo.repositories;

import com.example.demo.models.Entrepreneur;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EntrepreneurRepository extends MongoRepository<Entrepreneur,String> {
}