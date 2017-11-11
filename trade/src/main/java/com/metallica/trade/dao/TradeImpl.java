package com.metallica.trade.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.metallica.trade.repository.TradeRepository;

@Component
public class TradeImpl {

	@Autowired
	private TradeRepository repository;

	@Autowired
	private MongoOperations mongoOperations;

	public void addtrade(Trade trade) {
		repository.save(trade);
	}

	public String getTrades(SearchCriteria searchCriteria) {

		Query query = new Query();
		Criteria criteria = new Criteria();

		List<String> listOfside = new ArrayList<String>();
		listOfside.add(searchCriteria.getBuySide());
		listOfside.add(searchCriteria.getSellSide());

		if (searchCriteria.getCommodity() != null) {
			criteria = criteria.and("commodity").is(searchCriteria.getCommodity());
		}
		if (searchCriteria.getCounterParty() != null) {
			criteria = criteria.and("counterParty").is(searchCriteria.getCounterParty());
		}
		if (searchCriteria.getLocation() != null) {
			criteria = criteria.and("location").is(searchCriteria.getLocation());
		}
		if (searchCriteria.getBuySide() != null || searchCriteria.getSellSide() != null) {
			criteria = criteria.and("side")
					.in(Arrays.asList(searchCriteria.getBuySide(), searchCriteria.getSellSide()));
		}

	
		if (searchCriteria.getFromtradeDate() != null || searchCriteria.getTotradeDate() != null) {
			// criteria =
			// Criteria.where("tradeDate").gt(searchCriteria.getFromtradeDate()).lt(searchCriteria.getTotradeDate());
			

			criteria = criteria.orOperator(Criteria.where("tradeDate").gt(searchCriteria.getFromtradeDate()).lt(searchCriteria.getTotradeDate()));
			

		}

		query.addCriteria(criteria);

		List<Trade> tr = mongoOperations.find(query, Trade.class);

		System.out.println("List " + tr);
		String json = new Gson().toJson(tr);

		return json;

	}

	public String deleteTrade(String id) {

		if (repository.exists(id)) {
			repository.delete(id);
			return "Trade Id = " + id + " Deleted";
		} else {
			return "Trade Id = " + id + " Not Exist";
		}

	}

	public Trade updatetrade(Trade trade) {

		/*
		 * Query query = new
		 * Query(Criteria.where("_id").is("59f051f7cfe950222c6efc00")); Update
		 * update = new Update(); update.set("price", 100);
		 * mongoOperations.updateFirst(query, update, Trade.class);
		 */

		// Trade tradeObj =repository.findOne(trade.getTradeId());

		Trade t = repository.save(trade);
		return t;

	}

	public Trade getTrade(String id) {
		return repository.findOne(id);
	}

}
