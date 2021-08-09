package com.softeq.wiremock.gateway;

import com.softeq.wiremock.dto.RatesResponse;
import com.softeq.wiremock.dto.Request;
import com.softeq.wiremock.dto.WeatherResponse;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Getter
public class ProcessorGateway {

    private final ProcessorRestTemplate restTemplate = new ProcessorRestTemplate();
    private final String baseUrl;

    public ProcessorGateway(@Value("${gateway.host}") String host, @Value("${gateway.port}") int port) {
        this.baseUrl = "http://" + host + ":" + port;
    }

    public WeatherResponse requestWeather(final Request request) {
        String url = baseUrl + "/weather";
        return restTemplate.postForObject(url, request, WeatherResponse.class);
    }

    public RatesResponse requestExchangeRate(final Request request) {
        String url = baseUrl + "/rates";
        return restTemplate.postForObject(url, request, RatesResponse.class);
    }
}
