/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.reactive.service.impl;

import com.softeq.accelerator.flyway.reactive.domain.Assessment;
import com.softeq.accelerator.flyway.reactive.repository.r2dbc.AssessmentRepository;
import com.softeq.accelerator.flyway.reactive.service.AssessmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Assessment service implementation
 *
 * @author stitov
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AssessmentServiceImpl implements AssessmentService {

    private final AssessmentRepository repository;

    @Override
    public Flux<Assessment> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Assessment> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Assessment> save(Assessment assessment) {
        return repository.save(assessment);
    }

    @Override
    public Mono<Assessment> findByUserId(UUID id) {
        return repository.findFirstByUserId(id);
    }

}
