/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.integration;

import com.softeq.accelerator.flyway.TestWebApplication;
import com.softeq.accelerator.flyway.dto.CreateFeedbackDto;
import com.softeq.accelerator.flyway.dto.UserDto;
import com.softeq.accelerator.flyway.entity.Assessment;
import com.softeq.accelerator.flyway.repository.AssessmentRepo;
import com.softeq.accelerator.flyway.repository.UserRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

/**
 * Tests Feedback related logic.
 * <p/>
 * Created on 4/14/2020.
 * <p/>
 *
 * @author slapitsky
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {TestWebApplication.class})
@Profile(TestWebApplication.INTEGRATION_TEST_PROFILE)
public class FeedbackTest extends AbstractIntegrationTest {

    @Autowired
    private AssessmentRepo assessmentRepo;

    @Autowired
    private UserRepo userRepo;

    @Test
    public void testGetAllFeedbackOk() {
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<List<UserDto>> response = template
            .exchange(base + contextPath + "/api/v1/feedbacks",
                GET, new HttpEntity<>(null, headers), new ParameterizedTypeReference<List<UserDto>>() {
                });

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testCreateFeedbackOk() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        CreateFeedbackDto request = new CreateFeedbackDto();
        LocalDateTime feedbackDate = LocalDateTime.now();
        request.setFeedbackDate(feedbackDate);
        request.setUserId(1);
        request.setScore(BigDecimal.ONE);
        request.setComment("Good");

        //create assessment and pass the id to request
        Assessment a = new Assessment();
        a.setTargetUser(userRepo.getById(1).orElseThrow());
        a.setAssessmentDate(feedbackDate);
        a = assessmentRepo.save(a);
        request.setAssessmentId(a.getId());

        ResponseEntity<String> response = template
            .exchange(base + contextPath + "/api/v1/feedbacks",
                POST, new HttpEntity<>(request, headers), String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
