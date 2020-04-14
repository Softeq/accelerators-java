/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.integration;

import com.softeq.accelerator.flyway.TestWebApplication;
import com.softeq.accelerator.flyway.dto.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.HttpMethod.GET;

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

    @Test
    public void testGetAllFeedbackOk() {
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<List<UserDto>> response = template
            .exchange(base + contextPath + "/api/v1/feedbacks",
                GET, new HttpEntity<>(null, headers), new ParameterizedTypeReference<List<UserDto>>() {
                });

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        List<UserDto> users = response.getBody();
        assertTrue(users.isEmpty());
    }
}
