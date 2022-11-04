package com.example.demo;

import com.example.demo.Enterpreneur.EnterpreneurController;
import com.example.demo.Update.Update;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.models.Transaction;
import com.example.demo.repositories.TransactionRepository;
import com.example.demo.models.UpdateLog;
import com.example.demo.repositories.UpdateLogRepository;
@SpringBootApplication
@EnableMongoRepositories
@RestController
public class DemoApplication implements CommandLineRunner {

	private final UserRepository userRepository;
	@Autowired
	public DemoApplication(UserRepository userRepository){
		this.userRepository = userRepository;
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

		Update.update();
		EnterpreneurController.search();
		// userRepository.save(new User("Ton","GuMa"));

		for (User user : userRepository.findAll()){
			System.out.println(user);
		}
		
		//TEST Commit 2
	}
}