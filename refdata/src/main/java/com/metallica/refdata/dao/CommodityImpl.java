package com.metallica.refdata.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class CommodityImpl {

	@Autowired
	private MongoOperations mongoOperations;

	public List<Commodity> getCommodity(Commodity commodity) {
		List<Commodity> commodityList = null;
		// commodity.setCode("ZN");
		// commodity.setName("");

		System.out.println("Commodity " + commodity);

		Query query = new Query();
		Criteria criteria = new Criteria();
		criteria.orOperator(Criteria.where("code").is(commodity.getCode()),
				Criteria.where("name").is(commodity.getName()));

		query.addCriteria(criteria);

		commodityList = mongoOperations.find(query, Commodity.class);

		if (commodityList.isEmpty())
			commodityList = getAllCommodity();

		return commodityList;

	}

	private List<Commodity> getAllCommodity() {

		return mongoOperations.findAll(Commodity.class);

	}

	public boolean isValidCommodity(Commodity commodity) {
		Query query = new Query();
		query.addCriteria(Criteria.where("code").is(commodity.getCode()));

		return !mongoOperations.find(query, Commodity.class).isEmpty();
	}

}
