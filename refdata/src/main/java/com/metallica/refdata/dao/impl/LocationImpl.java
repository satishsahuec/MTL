package com.metallica.refdata.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.metallica.refdata.dao.Location;


@Component
public class LocationImpl {

	@Autowired
	private MongoOperations mongoOperations;

	public List<Location> getLocation(Location location) {

		List<Location> locationList = null;
	
		Query query = new Query();
		Criteria criteria = new Criteria();
		criteria.orOperator(Criteria.where("code").is(location.getCode()),
				Criteria.where("name").is(location.getName()));

		query.addCriteria(criteria);

		locationList = mongoOperations.find(query, Location.class);

		if (locationList.isEmpty())
			locationList = getAllLocation();

		return locationList;

	}

	private List<Location> getAllLocation() {

		return mongoOperations.findAll(Location.class);

	}

	public boolean isValidLocation(Location location) {
		Query query = new Query();
		query.addCriteria(Criteria.where("code").is(location.getCode()));
		
		return !mongoOperations.find(query, Location.class).isEmpty();

	}
}
