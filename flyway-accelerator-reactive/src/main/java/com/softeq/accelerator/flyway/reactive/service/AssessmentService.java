/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.reactive.service;

import com.softeq.accelerator.flyway.reactive.domain.Assessment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Assessment service
 *
 * @author stitov
 */
public interface AssessmentService {

    Flux<Assessment> findAll();

    Mono<Assessment> findById(UUID id);

    Mono<Assessment> save(Assessment assessment);

    Mono<Assessment> findByUserId(UUID id);

}
