package com.metallica.refdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoOperations;

import com.metallica.refdata.dao.Commodity;
import com.metallica.refdata.dao.CounterParty;
import com.metallica.refdata.dao.Location;
import com.metallica.refdata.dao.util.Counter;
import com.metallica.refdata.repository.CommodityRepository;
import com.metallica.refdata.repository.CounterPartyRepository;
import com.metallica.refdata.repository.LocationRepository;

@SpringBootApplication
public class RefDataApp implements CommandLineRunner {

	@Autowired
	private LocationRepository locationRepository;
	@Autowired
	private CommodityRepository commodityRepository;
	@Autowired
	private CounterPartyRepository counterPartyRepository;
	
	@Autowired
	private MongoOperations mongoOperation;

	public static void main(String[] args) {
		SpringApplication.run(RefDataApp.class, args);
		System.out.println("Ref Data Service up and running ");

	}
	//As of now hardcode Data is added Later Same As Trade service CURD operation can be done from frontend
	public void run(String... args) throws Exception {

		locationRepository.deleteAll();

		locationRepository.save(new Location("London", "LON"));
		locationRepository.save(new Location("New York", "NY"));
		locationRepository.save(new Location("Mumbai", "KA"));

		commodityRepository.deleteAll();

		commodityRepository.save(new Commodity("Alimunimum", "AL"));
		commodityRepository.save(new Commodity("Copper", "CU"));
		commodityRepository.save(new Commodity("Zinc", "ZN"));

		counterPartyRepository.deleteAll();

		counterPartyRepository.save(new CounterParty("APP", "Apple Inc"));
		counterPartyRepository.save(new CounterParty("ZET", "Zeeta corporation Inc"));
		counterPartyRepository.save(new CounterParty("SAP", "Sapient"));
		
		Counter count = new Counter("user", 0);
		if (!mongoOperation.collectionExists("counter"))
			mongoOperation.save(count, "counter");

		System.out.println("Dummy Data inserted sucessfully");

		for (Location location : locationRepository.findAll()) {
			System.out.println(location);
		}

		for (Commodity commodity : commodityRepository.findAll()) {
			System.out.println(commodity);
		}

		for (CounterParty counterParty : counterPartyRepository.findAll()) {
			System.out.println(counterParty);
		}
	}
}
