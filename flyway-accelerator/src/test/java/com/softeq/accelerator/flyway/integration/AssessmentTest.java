/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.integration;

import com.softeq.accelerator.flyway.TestWebApplication;
import com.softeq.accelerator.flyway.dto.AssessmentDto;
import com.softeq.accelerator.flyway.dto.CreateAssessmentDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

/**
 * Tests Assessment related logic
 * <p/>
 * Created on 4/13/2020.
 * <p/>
 *
 * @author slapitsky
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {TestWebApplication.class})
@Profile(TestWebApplication.INTEGRATION_TEST_PROFILE)
public class AssessmentTest extends AbstractIntegrationTest {

    @Test
    public void testGetAllAssessmentsOk() {
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<List<AssessmentDto>> response = template
            .exchange(base + contextPath + "/api/v1/assessments",
                GET, new HttpEntity<>(null, headers), new ParameterizedTypeReference<List<AssessmentDto>>() {
                });

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        List<AssessmentDto> users = response.getBody();
        assertTrue(users.isEmpty());
    }

    @Test
    public void testCreateAssessmentOk() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        CreateAssessmentDto request = new CreateAssessmentDto();
        LocalDateTime assessmentDate = LocalDateTime.now();
        request.setAssessmentDate(assessmentDate);
        request.setUserId(1);
        ResponseEntity<String> response = template
            .exchange(base + contextPath + "/api/v1/assessments",
                POST, new HttpEntity<>(request, headers), String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
