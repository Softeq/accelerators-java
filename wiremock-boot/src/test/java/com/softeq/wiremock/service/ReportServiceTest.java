package com.softeq.wiremock.service;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.softeq.wiremock.dto.RatesResponse;
import com.softeq.wiremock.dto.WeatherResponse;
import com.softeq.wiremock.entity.City;
import com.softeq.wiremock.gateway.ProcessorGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.softeq.wiremock.dto.WeatherResponse.WeatherResponseStatus.SUCCESS;
import static org.assertj.core.api.Assertions.assertThat;

public class ReportServiceTest {

    private ReportService service;
    private WireMockServer wireMockServer;

    public static final City MINSK = new City("Minsk", "BYN");
    public static final List<City> MINSK_AS_LIST = Arrays.asList(MINSK);

    public static final City CITY_ONE = new City("CityOne", "CUR");
    public static final City CITY_TWO = new City("CityTwo", "CUR");
    public static final List<City> CITY_LIST = Arrays.asList(CITY_ONE, CITY_TWO);

    public static final LocalDateTime FIXED_DATE_TIME = LocalDateTime.of(2021, 8, 5,
            0, 0, 0);

    public static final String WEATHER_URL = "/weather";
    public static final String RATES_URL = "/rates";

    @BeforeEach
    void setup() {
        wireMockServer = new WireMockServer();
        configureFor("localhost", 8080);
        wireMockServer.start();

        ProcessorGateway gateway = new ProcessorGateway("localhost", wireMockServer.port());
        service = new ReportService(gateway);
    }

    @Test
    void testRequestWeatherStubbingFromResources() {
        //given
        String requestValue = null;
        String responseValue = null;
        try {
            requestValue = new String(Files.readAllBytes(Paths.get("src/test/resources/requestMinsk.json")));
            responseValue = new String(Files.readAllBytes(Paths.get("src/test/resources/responseWeatherMinsk.json")));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        stubFor(post(urlPathEqualTo(WEATHER_URL))
                .withRequestBody(equalToJson(requestValue))
                .willReturn(okJson(responseValue)));

        //when
        List<WeatherResponse> actualResponseList = service.requestWeatherForList(MINSK_AS_LIST);
        WeatherResponse expectedResponse = new WeatherResponse("Minsk", "BY",
                294.01, "overcast clouds", WeatherResponse.WeatherResponseStatus.SUCCESS);
        List<WeatherResponse> expectedResponseList = Arrays.asList(expectedResponse);

        //then
        assertThat(actualResponseList).isEqualTo(expectedResponseList);

        //verify
        verify(postRequestedFor(urlEqualTo(WEATHER_URL)));
    }


    @Test
    void testRequestWeatherUndeterminedRequest() {
        //given
        String responseValue = null;
        try {
            responseValue = new String(Files.readAllBytes(Paths.get("src/test/resources/responseWeatherUndetermined.json")));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        stubFor(post(urlPathEqualTo(WEATHER_URL))
                .withRequestBody(
                        matchingJsonPath("cityName")
                )
                .withRequestBody(
                        matchingJsonPath("currencyAbbreviation")
                )
                .willReturn(okJson(responseValue)));

        //when
        List<WeatherResponse> actualResponseList = service.requestWeatherForList(CITY_LIST);
        WeatherResponse expectedResponse = new WeatherResponse("", "",
                0.0, "", SUCCESS);
        List<WeatherResponse> expectedResponseList = Arrays.asList(expectedResponse, expectedResponse);

        //then
        assertThat(actualResponseList).isEqualTo(expectedResponseList);

        //verify
        verify(2, postRequestedFor(urlEqualTo(WEATHER_URL)));
    }

    @Test
    void testRequestRatesStubbingFromResources() {
        //given
        String requestValue = null;
        String responseValue = null;
        try {
            responseValue = new String(Files.readAllBytes(Paths.get("src/test/resources/responseRatesMinsk.json")));
            requestValue = new String(Files.readAllBytes(Paths.get("src/test/resources/requestMinsk.json")));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        stubFor(post(urlPathEqualTo(RATES_URL))
                .withRequestBody(equalToJson(requestValue))
                .willReturn(okJson(responseValue)));

        //when
        List<RatesResponse> actualResponseList = service.requestRatesForList(MINSK_AS_LIST);
        RatesResponse expectedResponse = new RatesResponse("Minsk", 1, FIXED_DATE_TIME,
                "BYN", 1, "Belarusian rubles", BigDecimal.valueOf(1.0),
                RatesResponse.RatesResponseStatus.SUCCESS);
        List<RatesResponse> expectedResponseList = Arrays.asList(expectedResponse);

        //then
        assertThat(actualResponseList).isEqualTo(expectedResponseList);

        //verify
        verify(postRequestedFor(urlEqualTo(RATES_URL)));
    }


    @Test
    void testRequestRatesUndeterminedRequest() {
        //given
        String responseValue = null;
        try {
            responseValue = new String(Files.readAllBytes(Paths.get("src/test/resources/responseRatesUndetermined.json")));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        stubFor(post(urlPathEqualTo(RATES_URL))
                .withRequestBody(
                        matchingJsonPath("cityName")
                )
                .withRequestBody(
                        matchingJsonPath("currencyAbbreviation")
                )
                .willReturn(okJson(responseValue)));

        //when
        List<RatesResponse> actualResponseList = service.requestRatesForList(CITY_LIST);
        RatesResponse expectedResponse = new RatesResponse("", 0, FIXED_DATE_TIME,
                "", 0, "", BigDecimal.valueOf(0.0),
                RatesResponse.RatesResponseStatus.SUCCESS);
        List<RatesResponse> expectedResponseList = Arrays.asList(expectedResponse, expectedResponse);

        //then
        assertThat(actualResponseList).isEqualTo(expectedResponseList);

        //verify
        verify(2, postRequestedFor(urlEqualTo(RATES_URL)));
    }

    @AfterEach
    void tearDown() {
        wireMockServer.stop();
    }

}
