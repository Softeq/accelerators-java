/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.reactive.router;

import com.softeq.accelerator.flyway.reactive.handler.FeedbackHandler;
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
public class FeedbackRouter {

    @Bean(name = "FeedbackRouter")
    public RouterFunction<ServerResponse> route(FeedbackHandler handler) {
        return RouterFunctions.route()
            .GET("/feedback", accept(APPLICATION_JSON), handler::getAll)
            .GET("/feedback/{id}", accept(APPLICATION_JSON), handler::getById)
            .POST("/feedback", accept(APPLICATION_JSON), handler::create)
            .GET("/feedback/user/{id}", accept(APPLICATION_JSON), handler::getByUserId)
            .GET("/feedback/targetUser/{id}", accept(APPLICATION_JSON), handler::getByTargetUserId)
            .build();
    }

}
