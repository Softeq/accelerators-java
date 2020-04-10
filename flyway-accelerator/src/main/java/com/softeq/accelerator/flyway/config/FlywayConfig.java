/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */
package com.softeq.accelerator.flyway.config;

import com.softeq.accelerator.flyway.prod.db.migration.callback.LogFlywayCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Registering Flyway callback code
 * <p/>
 * Created on 4/9/2020.
 * <p/>
 *
 * @author slapitsky
 */
@Configuration
public class FlywayConfig {

    @Bean
    public LogFlywayCallback flywayCallback() {
        return new LogFlywayCallback();
    }
}
