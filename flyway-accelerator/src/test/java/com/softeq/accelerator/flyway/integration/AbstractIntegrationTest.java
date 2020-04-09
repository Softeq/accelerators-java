package com.softeq.accelerator.flyway.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

/**
 * Base class for all integration tests
 * <p/>
 * Created on 4/8/2020.
 * <p/>
 *
 * @author slapitsky
 */
public abstract class AbstractIntegrationTest {

    @LocalServerPort
    protected int port;

    @Value("${server.contextPath:}")
    protected String contextPath;

    protected String base;

    @Autowired
    protected TestRestTemplate template;
}
