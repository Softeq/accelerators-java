/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.reactive.handler;

import com.softeq.accelerator.flyway.reactive.domain.Feedback;
import com.softeq.accelerator.flyway.reactive.service.FeedbackService;
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
 * Feedback HTTP requests processing
 *
 * @author stitov
 */
@Component
@RequiredArgsConstructor
public class FeedbackHandler {

    private final FeedbackService service;

    public Mono<ServerResponse> getAll(ServerRequest request) {
        return ok().contentType(MediaType.APPLICATION_JSON).body(service.findAll(), Feedback.class);
    }

    public Mono<ServerResponse> getById(ServerRequest request) {
        UUID id = UUID.fromString(request.pathVariable("id"));
        return service.findById(id)
            .flatMap(feedback -> ok().contentType(MediaType.APPLICATION_JSON).bodyValue(feedback))
            .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(Feedback.class)
            .flatMap(service::save)
            .flatMap(feedback -> ServerResponse
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(feedback));
    }

    public Mono<ServerResponse> getByUserId(ServerRequest request) {
        UUID userId = UUID.fromString(request.pathVariable("id"));
        return ok().contentType(MediaType.APPLICATION_JSON).body(service.findByUserId(userId), Feedback.class);
    }

    public Mono<ServerResponse> getByTargetUserId(ServerRequest request) {
        UUID targetUserId = UUID.fromString(request.pathVariable("id"));
        return ok().contentType(MediaType.APPLICATION_JSON).body(service.findByTargetUserId(targetUserId), Feedback.class);
    }

}
