package com.softeq.wiremock.service;

import com.softeq.wiremock.dto.RatesResponse;
import com.softeq.wiremock.dto.Request;
import com.softeq.wiremock.dto.WeatherResponse;
import com.softeq.wiremock.entity.City;
import com.softeq.wiremock.gateway.ProcessorGateway;
import com.softeq.wiremock.mapper.RequestMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReportService {

    private static final LocalDateTime FIXED_DATE_TIME = LocalDateTime.of(2021, 8, 5,
            0, 0, 0);

    private final ProcessorGateway gateway;

    public List<WeatherResponse> requestWeatherForList(final List<City> cities) {
        List<WeatherResponse> responses = cities.stream()
                .map(RequestMapper.INSTANCE::cityToRequest)
                .map(this::requestWeatherForOneCity)
                .collect(Collectors.toList());
        return responses;
    }

    private WeatherResponse requestWeatherForOneCity(final Request request) {
        final WeatherResponse response = gateway.requestWeather(request);
        if (response != null) {
            return new WeatherResponse(response.getCityName(), response.getCountryCode(), response.getTemperature(),
                    response.getWeatherDescription(), WeatherResponse.WeatherResponseStatus.SUCCESS);
        } else {
            return new WeatherResponse("", "", 0.0,
                    "", WeatherResponse.WeatherResponseStatus.REJECTED);
        }
    }

    public List<RatesResponse> requestRatesForList(final List<City> cities) {
        List<RatesResponse> responses = cities.stream()
                .map(RequestMapper.INSTANCE::cityToRequest)
                .map(this::requestRatesForOneCity)
                .collect(Collectors.toList());
        return responses;
    }

    private RatesResponse requestRatesForOneCity(final Request request) {
        final RatesResponse response = gateway.requestExchangeRate(request);
        if (response != null) {
            return new RatesResponse(response.getCityName(), response.getCurrencyId(), response.getDate(),
                    response.getCurrencyAbbreviation(), response.getCurrencyScale(), response.getCurrencyName(),
                    response.getCurrencyOfficialRate(), RatesResponse.RatesResponseStatus.SUCCESS);
        } else {
            return new RatesResponse("", 0, FIXED_DATE_TIME, "", 0,
                    "", BigDecimal.ZERO, RatesResponse.RatesResponseStatus.REJECTED);
        }
    }
}

