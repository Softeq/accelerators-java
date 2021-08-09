package com.softeq.wiremock.service;

import com.softeq.wiremock.entity.City;
import com.softeq.wiremock.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CityService {
    private CityRepository repository;
    private CityInputParser parser;

    public List<City> getCitiesAsList(String input) {
        List<String> cityNames = parser.parse(input);
        return cityNames.stream()
                .map(repository::findByName)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public List<String> getAllCityNames() {
        List<City> cities = repository.findAll();
        List<String> cityNames = cities.stream().map(City::getName).collect(Collectors.toList());
        return cityNames;
    }
}
