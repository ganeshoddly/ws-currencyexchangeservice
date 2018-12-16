package com.example.currencyexchangeservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.currencyexchangeservice.model.ExchangeValue;
import com.example.currencyexchangeservice.repo.ExchangeValueRepo;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private Environment myEnv;
	
	@Autowired
	ExchangeValueRepo evRepo;
	
	@RequestMapping(value = "/createData", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody ExchangeValue evData)
	{
		evRepo.save(evData);
	}

	/* hard-coding without DB
	 * 
	 * @GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, 
												@PathVariable String to)
	{
		ExchangeValue ev = new ExchangeValue(1000L, from, to, BigDecimal.valueOf(65));
		ev.setPort(Integer.parseInt(myEnv.getProperty("local.server.port")));
		return ev;
	}
	*/
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, 
												@PathVariable String to)
	{
		ExchangeValue ev = evRepo.findByFromAndTo(from, to);
		ev.setPort(Integer.parseInt(myEnv.getProperty("local.server.port")));
		return ev;
	}

}
