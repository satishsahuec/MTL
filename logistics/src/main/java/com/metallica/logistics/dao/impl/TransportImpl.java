package com.metallica.logistics.dao.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.metallica.logistics.dao.Transport;
import com.metallica.logistics.dao.util.SearchCriteriaTransport;
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

	// TODO NOT a good way to search this way some other solution later
	public String getTransports(SearchCriteriaTransport searchCriteria) {

		Query query = new Query();
		Criteria criteria = new Criteria();

		if (searchCriteria.getOrigin() != null) {
			criteria = criteria.and("origin").is(searchCriteria.getOrigin());
		}
		if (searchCriteria.getDestination() != null) {
			criteria = criteria.and("destination").is(searchCriteria.getDestination());
		}
		if (searchCriteria.getTransportType() != null) {
			criteria = criteria.and("transportType").is(searchCriteria.getTransportType());
		}

		if (searchCriteria.getFromLoadingDate() != null && searchCriteria.getToLoadingDate() != null) {

			criteria = criteria.andOperator(Criteria.where("loadingDate").gte(searchCriteria.getFromLoadingDate()),
					Criteria.where("loadingDate").lte(searchCriteria.getToLoadingDate()));

		} else if (searchCriteria.getFromLoadingDate() != null && searchCriteria.getToLoadingDate() == null) {
			criteria = criteria.and("loadingDate").gte(searchCriteria.getFromLoadingDate());

		}

		else if (searchCriteria.getFromLoadingDate() == null && searchCriteria.getToLoadingDate() != null) {

			criteria = criteria.and("loadingDate").gte(searchCriteria.getToLoadingDate());

		}

		if (searchCriteria.getFromUnloadingDate() != null && searchCriteria.getToUnloadingDate() != null) {

			criteria = criteria.andOperator(Criteria.where("unloadingDate").gte(searchCriteria.getFromUnloadingDate()),
					Criteria.where("unloadingDate").lte(searchCriteria.getToUnloadingDate()));

		} else if (searchCriteria.getFromUnloadingDate() != null && searchCriteria.getToUnloadingDate() == null) {
			criteria = criteria.and("unloadingDate").gte(searchCriteria.getFromUnloadingDate());

		}

		else if (searchCriteria.getFromUnloadingDate() == null && searchCriteria.getToUnloadingDate() != null) {

			criteria = criteria.and("unloadingDate").gte(searchCriteria.getToUnloadingDate());

		}
		query.addCriteria(criteria);

		return new Gson().toJson(mongoOperations.find(query, Transport.class));

	}

	public Transport updatetrade(Transport transport) {

		return transportRepo.save(transport);

	}

}
