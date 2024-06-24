package com.petwellnes.petwellnes_backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {
    private static final String REST_COUNTRIES_API_URL = "https://restcountries.com/v3.1/all";

    public List<String> getCountries() {
        RestTemplate restTemplate = new RestTemplate();
        Country[] countries = restTemplate.getForObject(REST_COUNTRIES_API_URL, Country[].class);
        return countries != null ?
                Arrays.stream(countries).map(Country::getName).sorted().collect(Collectors.toList()) :
                Collections.emptyList();
    }

    private static class Country {
        private Name name;

        public String getName() {
            return name.common;
        }

        private static class Name {
            private String common;
        }
    }
}
