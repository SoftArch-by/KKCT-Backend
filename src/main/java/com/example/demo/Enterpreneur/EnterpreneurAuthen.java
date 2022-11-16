package com.example.demo.Enterpreneur;

import com.example.demo.models.Entrepreneur;
import com.example.demo.repositories.EntrepreneurRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class EnterpreneurAuthen {
    private static EntrepreneurRepository entrepreneur;


    public static void register(Entrepreneur e){
        System.out.println(e+"this is");
        //Object check = entrepreneur.findEntrepreneurByEmail(e.getEmail());
        entrepreneur.save(e);

    }



}
