/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.feign.client;

import com.softeq.feign.model.WeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "forecast", url = "https://api.weather.gov")
public interface WeatherClient {
    @GetMapping("/points/{point}/forecast")
    ResponseEntity<WeatherResponse> getForecast(@PathVariable(name = "point") String point);
}
