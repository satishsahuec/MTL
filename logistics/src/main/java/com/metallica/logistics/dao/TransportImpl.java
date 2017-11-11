package com.metallica.logistics.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.metallica.logistics.repository.TransportRepository;

@Component
public class TransportImpl {

	@Autowired
	TransportRepository transportRepo;
	@Autowired
	private MongoOperations mongoOperations;

	public void addTransport(Transport transport) {

		transportRepo.save(transport);

	}

	public String deleteTransport(String id) {

		if (transportRepo.exists(id)) {
			transportRepo.delete(id);
			return "Transport Id = " + id + " Deleted";
		} else {
			return "Transport Id = " + id + " Not Exist";
		}
	}

	public List<Transport> getTransports(Transport transport) {

		Query query = new Query();

		query.addCriteria(Criteria.where("origin").is(transport.getOrigin())
				.orOperator(Criteria.where("destination").is(transport.getDestination())
						.orOperator(Criteria.where("loadingDate").is(transport.getLoadingDate())
								.orOperator(Criteria.where("unloadingDate").is(transport.getUnloadingDate()).orOperator(
										Criteria.where("transportType").is(transport.getTransportType()))))));

		return mongoOperations.find(query, Transport.class);

	}

	public Transport updatetrade(Transport transport) {

		return transportRepo.save(transport);

	}

}
