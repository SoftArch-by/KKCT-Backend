package com.example.demo.Enterpreneur;


import com.example.demo.models.Credit;
import com.example.demo.models.Entrepreneur;
import com.example.demo.models.Transaction;
import com.example.demo.repositories.EntrepreneurRepository;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator.TypeMatcher;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CalculationCreditForEntreprenneur {

    public static Credit calculationCredit(ResponseEntity<List<Transaction>> transaction){

        List<Transaction> transactionBody = null;
        transactionBody = transaction.getBody();
        if (!transactionBody.isEmpty()){
            int numberOfTransaction = transactionBody.size();
            numberOfTransaction = transactionBody.size();
            int clearTransactionn = 0;
            float amountMoney = 0;
            List<Float> length = new ArrayList<>();
            float transactionPeriod = 0;
            int typeMatching = 0;
            
            for (Transaction t:transactionBody){
                //clearTransaction
                if (t.getUnpaid() == 0){
                    clearTransactionn+=1;
                }
                //amount paid/all
                amountMoney += ((t.getTransactionInfo() - t.getUnpaid())/t.getTransactionInfo());

                //length
                long diff_millisec =  Math.abs(t.getDueDate().getTime() - t.getTransactionDate().getTime());
                float days = (int) (diff_millisec / (1000*60*60*24));
                length.add(days);
                transactionPeriod+=days;
                
                // //check entreprenuer Type Matching
                // System.out.println(t.getEntrepreneur_ID());

                // String temp =t.getEntrepreneur_ID();
                // Entrepreneur t_Ent_ID = EntrepreneurRepository.findById(temp);
                // System.out.println(t_Ent_ID);
                // if(typeEntReq == (t_Ent_ID.getType())){
                //     typeMatching += 1;
                // }

            }
            //if clear all trasaction => clearTransaction = 1
            clearTransactionn = (clearTransactionn / numberOfTransaction);
            //if paid all transaction => amountMoney = 1
            amountMoney = (amountMoney / numberOfTransaction) ;
            //average of trasaction period
            transactionPeriod = (transactionPeriod / numberOfTransaction) ;
            //if type macth all transactionn => typeMatch = 1
            typeMatching = (typeMatching / numberOfTransaction) ;

            float credit = (clearTransactionn * 45) + (amountMoney * 35) + ((transactionPeriod/365) * 20 );

            System.out.println(clearTransactionn);
            System.out.println(amountMoney);
            System.out.println((transactionPeriod/365) * 15);
            System.out.println(credit);

            String recommend ;
            if (credit >= 75){
                recommend = "Good Credit";
            }
            else if(credit >= 50){
                recommend = "Moderate Credit";
            }
            else if(credit >= 25){
                recommend = "Poor Credit";
            }
            else{
                recommend = "Bad Credit";
            }
            

            // return "{" + 
            // "CreditScore='" + credit + "'" +
            // ",Recommend='" + recommend + "'" +
            // "}";
            return new Credit(credit,recommend);

        }
        else{
            return null;
        }

    }
}
