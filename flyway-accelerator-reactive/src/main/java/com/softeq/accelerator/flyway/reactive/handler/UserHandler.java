/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.reactive.handler;

import com.softeq.accelerator.flyway.reactive.domain.User;
import com.softeq.accelerator.flyway.reactive.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * User HTTP requests processing
 *
 * @author stitov
 */
@Component
@RequiredArgsConstructor
public class UserHandler {

    private final UserService service;

    public Mono<ServerResponse> getAll(ServerRequest request) {
        return ok().contentType(MediaType.APPLICATION_JSON).body(service.findAll(), User.class);
    }

    public Mono<ServerResponse> getById(ServerRequest request) {
        UUID id = UUID.fromString(request.pathVariable("id"));
        return service.findById(id)
            .flatMap(user -> ok().contentType(MediaType.APPLICATION_JSON).bodyValue(user))
            .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(User.class)
            .flatMap(service::save)
            .flatMap(user -> ServerResponse
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(user));
    }

}
