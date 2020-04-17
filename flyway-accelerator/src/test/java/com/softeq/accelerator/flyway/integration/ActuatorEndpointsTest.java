/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.integration;

import com.softeq.accelerator.flyway.TestWebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.HttpMethod.GET;

/**
 * Tests some actuator endpoints
 * <p/>
 * Created on 4/17/2020.
 * <p/>
 *
 * @author amilosh
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {TestWebApplication.class})
@Profile(TestWebApplication.INTEGRATION_TEST_PROFILE)
public class ActuatorEndpointsTest extends AbstractIntegrationTest {

    public static final String ACTUATOR = "/actuator";
    public static final String ENDPOINT_BEANS = "/beans";
    public static final String ENDPOINT_ENV = "/env";
    public static final String ENDPOINT_FLYWAY = "/flyway";
    public static final String ENDPOINT_HEALTH = "/health";
    public static final String ENDPOINT_TRACE = "/httptrace";
    public static final String ENDPOINT_INFO = "/info";
    public static final String ENDPOINT_METRICS = "/metrics";
    public static final String ENDPOINT_DUMP = "/threaddump";

    @Test
    public void testEndpointBeansOk() {
        ResponseEntity<String> response = template
                .exchange(base + contextPath + "/api/v1" + ACTUATOR + ENDPOINT_BEANS,
                        GET, new HttpEntity<>(null, headers()), String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        String beansBody = response.getBody();
        assertTrue(beansBody.contains("appActuatorEndpoint"));
        assertTrue(beansBody.contains("flywayAcceleratorApplication"));
    }

    @Test
    public void testEndpointEnvOk() {
        ResponseEntity<String> response = template
                .exchange(base + contextPath + "/api/v1" + ACTUATOR + ENDPOINT_ENV,
                        GET, new HttpEntity<>(null, headers()), String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        String envBody = response.getBody();
        assertTrue(envBody.contains("\"OS\""));
        assertTrue(envBody.contains("\"activeProfiles\""));
    }

    @Test
    public void testEndpointFlywayOk() {
        ResponseEntity<String> response = template
                .exchange(base + contextPath + "/api/v1" + ACTUATOR + ENDPOINT_FLYWAY,
                        GET, new HttpEntity<>(null, headers()), String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        String flywayBody = response.getBody();
        assertTrue(flywayBody.contains("\"flywayBeans\""));
        assertTrue(flywayBody.contains("\"flyway\""));
    }

    @Test
    public void testEndpointHealthOk() {
        ResponseEntity<String> response = template
                .exchange(base + contextPath + "/api/v1" + ACTUATOR + ENDPOINT_HEALTH,
                        GET, new HttpEntity<>(null, headers()), String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        String healthBody = response.getBody();
        assertTrue(healthBody.contains("\"status\":\"UP\""));
    }

    @Test
    public void testEndpointTraceOk() {
        ResponseEntity<String> response = template
                .exchange(base + contextPath + "/api/v1" + ACTUATOR + ENDPOINT_TRACE,
                        GET, new HttpEntity<>(null, headers()), String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        String traceBody = response.getBody();
        assertTrue(traceBody.contains("\"traces\""));
    }

    @Test
    public void testEndpointInfoOk() {
        ResponseEntity<String> response = template
                .exchange(base + contextPath + "/api/v1" + ACTUATOR + ENDPOINT_INFO,
                        GET, new HttpEntity<>(null, headers()), String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        String infoBody = response.getBody();
        assertTrue(infoBody.contains("\"app\""));
        assertTrue(infoBody.contains("\"version\""));
    }

    @Test
    public void testEndpointMetricsOk() {
        ResponseEntity<String> response = template
                .exchange(base + contextPath + "/api/v1" + ACTUATOR + ENDPOINT_METRICS,
                        GET, new HttpEntity<>(null, headers()), String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        String metricsBody = response.getBody();
        assertTrue(metricsBody.contains("\"names\":"));
    }

    @Test
    public void testEndpointDumpOk() {
        ResponseEntity<String> response = template
                .exchange(base + contextPath + "/api/v1" + ACTUATOR + ENDPOINT_DUMP,
                        GET, new HttpEntity<>(null, headers()), String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        String threaddumpBody = response.getBody();
        assertTrue(threaddumpBody.contains("\"Reference Handler\""));
        assertTrue(threaddumpBody.contains("\"Finalizer\""));
    }

    private HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
