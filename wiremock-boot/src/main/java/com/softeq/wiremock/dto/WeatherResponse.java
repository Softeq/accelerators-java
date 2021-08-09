package com.softeq.wiremock.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class WeatherResponse {
    private final String cityName;
    private final String countryCode;
    private final Double temperature;
    private final String weatherDescription;
    private final WeatherResponseStatus weatherResponseStatus;

    @JsonCreator
    public WeatherResponse(@JsonProperty("cityName") String cityName,
                           @JsonProperty("countryCode") String countryCode,
                           @JsonProperty("temperature") Double temperature,
                           @JsonProperty("weatherDescription") String weatherDescription,
                           @JsonProperty("responseStatus") WeatherResponseStatus weatherResponseStatus) {
        this.cityName = cityName;
        this.countryCode = countryCode;
        this.temperature = temperature;
        this.weatherDescription = weatherDescription;
        this.weatherResponseStatus = weatherResponseStatus;
    }

    public enum WeatherResponseStatus {
        SUCCESS, REJECTED
    }
}
