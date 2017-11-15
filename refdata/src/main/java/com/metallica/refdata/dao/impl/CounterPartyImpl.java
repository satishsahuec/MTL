package com.metallica.refdata.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.metallica.refdata.dao.CounterParty;

@Component
public class CounterPartyImpl {

	@Autowired
	private MongoOperations mongoOperations;

	public List<CounterParty> getCounterParty(CounterParty counterParty) {
		List<CounterParty> counterPartyList = null;
		
		Query query = new Query();
		Criteria criteria = new Criteria();
		criteria.orOperator(Criteria.where("code").is(counterParty.getCode()),
				Criteria.where("name").is(counterParty.getName()));

		query.addCriteria(criteria);

		counterPartyList = mongoOperations.find(query, CounterParty.class);

		if (counterPartyList.isEmpty())
			counterPartyList = getAllCounterParty();

		return counterPartyList;

	}

	private List<CounterParty> getAllCounterParty() {

		return mongoOperations.findAll(CounterParty.class);

	}

	public boolean isValidCounterParty(CounterParty counterParty) {
		Query query = new Query();
		query.addCriteria(Criteria.where("code").is(counterParty.getCode()));

		return !mongoOperations.find(query, CounterParty.class).isEmpty();
	}

}
