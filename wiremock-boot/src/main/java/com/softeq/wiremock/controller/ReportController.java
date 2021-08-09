package com.softeq.wiremock.controller;

import com.softeq.wiremock.dto.RatesResponse;
import com.softeq.wiremock.dto.WeatherResponse;
import com.softeq.wiremock.entity.City;
import com.softeq.wiremock.service.CityService;
import com.softeq.wiremock.service.ExcelExporter;
import com.softeq.wiremock.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@EnableWebMvc
@AllArgsConstructor
public class ReportController {

    private ReportService reportService;
    private CityService cityService;
    private ExcelExporter exporter;

    @GetMapping
    public ModelAndView home() {
        ModelAndView homeMV = new ModelAndView();
        List<String> cityNames = cityService.getAllCityNames();
        homeMV.setViewName("home");
        homeMV.addObject("cityNames", cityNames);
        return homeMV;
    }

    @PostMapping("/getReport")
    public void getReport(@RequestParam String input, HttpServletResponse response) {
        List<City> cities = cityService.getCitiesAsList(input);
        List<WeatherResponse> weatherResponses = reportService.requestWeatherForList(cities);
        List<RatesResponse> ratesResponses = reportService.requestRatesForList(cities);

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Report.csv";
        response.setHeader(headerKey, headerValue);

        exporter.export(response, weatherResponses, ratesResponses);
    }
}
