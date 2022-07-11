/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.reactive.service;

import com.softeq.accelerator.flyway.reactive.domain.Feedback;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Feedback service
 *
 * @author stitov
 */
public interface FeedbackService {

    Flux<Feedback> findAll();

    Mono<Feedback> findById(UUID id);

    Mono<Feedback> save(Feedback feedback);

    Flux<Feedback> findByUserId(UUID id);

    Flux<Feedback> findByTargetUserId(UUID id);

}
