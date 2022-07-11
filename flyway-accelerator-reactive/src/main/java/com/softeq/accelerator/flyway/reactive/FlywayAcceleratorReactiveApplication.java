/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories(basePackages = "com.softeq.accelerator.flyway.reactive.repository.r2dbc")
@EnableReactiveMongoRepositories(basePackages = "com.softeq.accelerator.flyway.reactive.repository.nosql")
public class FlywayAcceleratorReactiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlywayAcceleratorReactiveApplication.class, args);
    }

}
