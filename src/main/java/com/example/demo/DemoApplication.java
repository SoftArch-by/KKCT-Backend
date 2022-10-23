package com.example.demo;

import com.example.demo.Update.Update;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.models.Transaction;
import com.example.demo.repositories.TransactionRepository;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication implements CommandLineRunner {

	private final UserRepository userRepository;
	private final TransactionRepository transactionRepository;
	@Autowired
	public DemoApplication(UserRepository userRepository,TransactionRepository transactionRepository){
		this.userRepository = userRepository;
		this.transactionRepository = transactionRepository;
	}

	
	public static void main(String... args) throws Exception{
		SpringApplication.run(DemoApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		if(userRepository.findAll().isEmpty()){
			userRepository.save(new User("Oat","LNWza007"));
			userRepository.save(new User("NotOat","ButGod"));
		}
		if(transactionRepository.findAll().isEmpty()){
			transactionRepository.save(new Transaction("1", "KKCT",100.0,LocalDate.now(),LocalDate.now()));
		}

		Update.update();
		// userRepository.save(new User("Ton","GuMa"));

		for (User user : userRepository.findAll()){
			System.out.println(user);
		}
		
		//TEST Commit 2
	}
}