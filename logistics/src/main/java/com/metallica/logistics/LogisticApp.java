package com.metallica.logistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoOperations;

import com.metallica.logistics.dao.util.Counter;

@SpringBootApplication
public class LogisticApp implements CommandLineRunner {

	@Autowired
	private MongoOperations mongoOperation;

	public static void main(String[] args) {
		SpringApplication.run(LogisticApp.class, args);
		System.out.println("Logistic Service up and running ");

	}

	public void run(String... args) throws Exception {
		//Auto incremented Sequence for _id in mongo DB
		Counter count = new Counter("user", 0);
		if (!mongoOperation.collectionExists("counter"))
			mongoOperation.save(count, "counter");
	}
}
