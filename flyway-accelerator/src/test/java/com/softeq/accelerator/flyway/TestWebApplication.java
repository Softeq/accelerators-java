/*
 *
 *  * Developed by Softeq Development Corporation
 *  * http://www.softeq.com
 *
 */

package com.softeq.accelerator.flyway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Test web app
 * <p/>
 * Created on 4/8/2020.
 * <p/>
 *
 * @author slapitsky
 */
@SpringBootApplication(scanBasePackages = "com.softeq.accelerator.flyway")
public class TestWebApplication {
    public static final String INTEGRATION_TEST_PROFILE = "integration_test";

    public static void main(String[] args) {
        SpringApplication.run(TestWebApplication.class, args);
    }
}
