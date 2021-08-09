package com.softeq.wiremock.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class CityInputParser {
    private static final String DELIMITER = ",";

    public List<String> parse(String data) {
        return Stream.of(data.split(DELIMITER))
                .map(String::new)
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
