package com.softeq.accelerator.flyway.integration;

import com.softeq.accelerator.flyway.TestWebApplication;
import com.softeq.accelerator.flyway.actuator.AppActuatorEndpoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.springframework.http.HttpMethod.GET;

/**
 * Tests custom actuator endpoint 'app-info'
 * <p/>
 * Created on 4/17/2020.
 * <p/>
 *
 * @author amilosh
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {TestWebApplication.class})
@Profile(TestWebApplication.INTEGRATION_TEST_PROFILE)
@PropertySource("classpath:application-gradle.properties")
public class AppActuatorEndpointTest extends AbstractIntegrationTest {

    public static final String APP_VERSION = AppActuatorEndpoint.APP_VERSION;

    @Value("${app.version}")
    private String appVersion;

    @Test
    public void testGetAllUsersOk() {
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<Map<String, Object>> response = template
                .exchange(base + contextPath + "/api/v1/actuator/app-info",
                        GET, new HttpEntity<>(null, headers), new ParameterizedTypeReference<Map<String, Object>>() {
                        });

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        Map<String, Object> appInfo = response.getBody();
        String foundAppVersion = (String) appInfo.get(APP_VERSION);
        assertNotNull(appInfo.get(APP_VERSION));
        assertFalse(foundAppVersion.trim().isEmpty());
        assertEquals(appVersion, foundAppVersion);
    }
}
