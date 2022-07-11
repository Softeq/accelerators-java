/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.reactive.router;

import com.softeq.accelerator.flyway.reactive.handler.UserHandler;
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
public class UserRouter {

    @Bean(name = "UserRouter")
    public RouterFunction<ServerResponse> route(UserHandler handler) {
        return RouterFunctions.route()
            .GET("/user", accept(APPLICATION_JSON), handler::getAll)
            .GET("/user/{id}", accept(APPLICATION_JSON), handler::getById)
            .POST("/user", accept(APPLICATION_JSON), handler::create)
            .build();
    }

}
