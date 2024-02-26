package com.ensa.tests.soapServices;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CountryRepository {

    private static final Map<String, Country> countries = new HashMap<>();

    @PostConstruct
    public void initData() {
        // initialize countries map
        countries.put("USA", new Country("United States", 331002651, "Washington D.C.", CurrencyEnum.USD));
        countries.put("UK", new Country("United Kingdom", 67886011, "London", CurrencyEnum.GBP));
        countries.put("India", new Country("India", 1380004385, "New Delhi", CurrencyEnum.INR));
    }

    public Country findCountry(String name) {
        return countries.get(name);
    }
}
