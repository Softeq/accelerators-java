/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.reactive.handler;

import com.softeq.accelerator.flyway.reactive.domain.Assessment;
import com.softeq.accelerator.flyway.reactive.service.AssessmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * Assessment HTTP requests processing
 *
 * @author stitov
 */
@Component
@RequiredArgsConstructor
public class AssessmentHandler {

    private final AssessmentService service;

    public Mono<ServerResponse> getAll(ServerRequest request) {
        return ok().contentType(MediaType.APPLICATION_JSON).body(service.findAll(), Assessment.class);
    }

    public Mono<ServerResponse> getById(ServerRequest request) {
        UUID id = UUID.fromString(request.pathVariable("id"));
        return service.findById(id)
            .flatMap(assessment -> ok().contentType(MediaType.APPLICATION_JSON).bodyValue(assessment))
            .switchIfEmpty(ServerResponse.notFound().build());
    }

}
