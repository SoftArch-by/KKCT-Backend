package com.example.demo.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document
@Data
public class RefreshToken {
    @Id
    private String id;
    @DocumentReference(lazy = true)
    private User owner;
}
