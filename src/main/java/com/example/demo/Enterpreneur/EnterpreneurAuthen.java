package com.example.demo.Enterpreneur;

import com.example.demo.models.Entrepreneur;
import com.example.demo.repositories.EntrepreneurRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class EnterpreneurAuthen {
    @Autowired
    private EntrepreneurRepository entrepreneur;

    EnterpreneurAuthen (){
    }

    public void register(Entrepreneur e){
        Object check = entrepreneur.findEntrepreneurByEmail(e.getEmail());
        if (check!= null){
            entrepreneur.save(e);
        }
    }



}
