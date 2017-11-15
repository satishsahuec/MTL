package com.metallica.trade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoOperations;

import com.metallica.trade.dao.util.Counter;

@SpringBootApplication
public class TradeApp implements CommandLineRunner {

	@Autowired
	private  MongoOperations mongoOperation;

	public static void main(String[] args) {

		SpringApplication.run(TradeApp.class, args);

		System.out.println("Trade Service up and running");
	}

	public void run(String... args) throws Exception {


		Counter count = new Counter("user", 0);
		if (!mongoOperation.collectionExists("counter"))
			mongoOperation.save(count, "counter");
	}
}
