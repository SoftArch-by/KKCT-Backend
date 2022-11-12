package com.example.demo.Enterpreneur;

import com.example.demo.models.Transaction;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;

public class CalculationCreditForEnterprenneur {

    public static String calculationCredit(ResponseEntity<List<Transaction>> transaction){
        String customertransaction = null;
        customertransaction = transaction.getBody().toString();

        List<Transaction> transactionBody = null;
        transactionBody = transaction.getBody();
        if (!transactionBody.isEmpty()){
            int numberOfTransaction = transactionBody.size();
            numberOfTransaction = transactionBody.size();
            int clearTransactionn = 0;
            float amountMoney = 0;
            List<Float> length = new ArrayList<>();
            float transactionPeriod = 0;
            
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
                
                //check enterprenuer Type Matching
                //

            }
            //if clear all trasaction => clearTransaction = 1
            clearTransactionn = (clearTransactionn / numberOfTransaction);
            //if paid all transaction => amountMoney = 1
            amountMoney = (amountMoney / numberOfTransaction) ;
            //average of trasaction period
            transactionPeriod = (transactionPeriod / numberOfTransaction) ;
            //if type macth all transactionn => typeMatch = 1
            // 

            //still not matching
            float credit = (clearTransactionn * 40) + (amountMoney * 30) + ((transactionPeriod/365) * 15);

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

            return "{" + 
            "CreditScore='" + credit + "'" +
            ",Recommend='" + recommend + "'" +
            "}";

        }
        else{
            return "not have transaction in this customer";
        }

    }
}
