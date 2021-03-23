/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.feign.controller;

import com.softeq.feign.model.JiraResponse;
import com.softeq.feign.model.WeatherResponse;
import com.softeq.feign.service.FullService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AppController {
    @Autowired
    private FullService client;

    @GetMapping("/browse")
    ResponseEntity<JiraResponse> getAllOpenIssues(@RequestParam(name = "jqlRequest", required = false) String jqlRequest) {
        return client.getAllOpenIssues(jqlRequest);
    }

    @GetMapping("/points/{point}/forecast")
    ResponseEntity<WeatherResponse> getWeatherData(@PathVariable(name = "point") String point) {
        return client.getWeatherData(point);
    }

    @GetMapping("/projects/{project}")
    ResponseEntity<JiraResponse> getProjectIssues(@RequestParam(name = "jqlRequest", required = false) String filter,
                                                  @PathVariable(name = "project") String project) {
        return client.getProjectIssues(filter, project);
    }

    @GetMapping("/secure")
    ResponseEntity<JiraResponse> getActiveSprints(@RequestParam(name = "rapidView", required = false) String rapidView) {
        return client.getActiveSprints(rapidView);
    }
}
