package com.example.demo.repositories;

import com.example.demo.models.Entrepreneur;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EntrepreneurRepository extends MongoRepository<Entrepreneur,String> {
    Entrepreneur findEntrepreneurByEmailAndOrganizationName(String email,String organizationName);
    Entrepreneur findEntrepreneurByEmail(String email);
    Entrepreneur findEntrepreneurByOrganizationName(String organizationname);
    Entrepreneur findID(String _id);
}
