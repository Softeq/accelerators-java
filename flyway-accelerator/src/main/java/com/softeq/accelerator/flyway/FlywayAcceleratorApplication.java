package com.softeq.accelerator.flyway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.softeq.accelerator.flyway"})
public class FlywayAcceleratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlywayAcceleratorApplication.class, args);
    }

}
