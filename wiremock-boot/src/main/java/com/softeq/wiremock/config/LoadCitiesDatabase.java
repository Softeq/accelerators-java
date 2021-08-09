package com.softeq.wiremock.config;

import com.softeq.wiremock.entity.City;
import com.softeq.wiremock.repository.CityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadCitiesDatabase {
    @Bean
    CommandLineRunner initDatabase(CityRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new City("Minsk", "BYN")));
            log.info("Preloading " + repository.save(new City("Munich", "EUR")));
            log.info("Preloading " + repository.save(new City("Houston", "USD")));
            log.info("Preloading " + repository.save(new City("Vilnius", "EUR")));
            log.info("Preloading " + repository.save(new City("Kiev", "UAH")));
            log.info("Preloading " + repository.save(new City("Moscow", "RUB")));
        };
    }
}
