/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.feign.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softeq.feign.client.JiraClient;
import com.softeq.feign.client.WeatherClient;
import com.softeq.feign.model.JiraResponse;
import com.softeq.feign.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class FullService {
    private final WeatherClient weatherClient;
    private final JiraClient jiraClient;
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public FullService(WeatherClient weatherClient, JiraClient jiraClient) {
        this.weatherClient = weatherClient;
        this.jiraClient = jiraClient;
    }

    public ResponseEntity<JiraResponse> getAllOpenIssues(String request) {
        ResponseEntity<JiraResponse> jiraResponseEntity = jiraClient.getInfoFromJqlRequest(request);
        ResponseEntity<JiraResponse> json =
            ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(jiraResponseEntity.getBody());
        ResponseEntity<JiraResponse> pdf =
            ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(jiraResponseEntity.getBody());
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("./data/report.json", true));
            BufferedWriter writer1 = new BufferedWriter(new FileWriter("./data/pdf_report.pdf", true))) {
            String jiraJsonRes = mapper.writeValueAsString(jiraResponseEntity.getBody());
            writer.append(jiraJsonRes);
            String jiraPdfRes = mapper.writeValueAsString(pdf.getBody());
            writer1.append(jiraPdfRes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public ResponseEntity<JiraResponse> getProjectIssues(String filter, String project) {
        ResponseEntity<JiraResponse> jiraResponseEntity = jiraClient.getIssues(filter, project);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(jiraResponseEntity.getBody());
    }

    public ResponseEntity<JiraResponse> getActiveSprints(String rapidView) {
        ResponseEntity<JiraResponse> jiraResponseEntity = jiraClient.getActiveSprints(rapidView);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(jiraResponseEntity.getBody());
    }

    public ResponseEntity<WeatherResponse> getWeatherData(String point) {
        ResponseEntity<WeatherResponse> weatherResponseEntity = weatherClient.getForecast(point);
        ResponseEntity<WeatherResponse> json =
            ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(weatherResponseEntity.getBody());
        ResponseEntity<WeatherResponse> pdf =
            ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(weatherResponseEntity.getBody());
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("./data/report.json", true));
            BufferedWriter writer1 = new BufferedWriter(new FileWriter("./data/pdf_report.pdf", true))) {
            String weatherJsonRes = mapper.writeValueAsString(json.getBody());
            writer.append(weatherJsonRes);
            String weatherPdfRes = mapper.writeValueAsString(pdf.getBody());
            writer1.append(weatherPdfRes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
