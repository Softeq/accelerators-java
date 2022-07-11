/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.reactive.router;

import com.softeq.accelerator.flyway.reactive.handler.AssessmentHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

/**
 * User router function
 *
 * @author stitov
 */
@Configuration
public class AssessmentRouter {

    @Bean(name = "AssessmentRouter")
    public RouterFunction<ServerResponse> route(AssessmentHandler handler) {
        return RouterFunctions.route()
            .GET("/assessment", accept(APPLICATION_JSON), handler::getAll)
            .GET("/assessment/{id}", accept(APPLICATION_JSON), handler::getById)
            .build();
    }

}
