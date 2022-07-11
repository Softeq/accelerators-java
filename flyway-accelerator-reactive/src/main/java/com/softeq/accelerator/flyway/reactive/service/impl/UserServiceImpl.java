/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.reactive.service.impl;

import com.softeq.accelerator.flyway.reactive.domain.Assessment;
import com.softeq.accelerator.flyway.reactive.domain.User;
import com.softeq.accelerator.flyway.reactive.repository.r2dbc.UserRepository;
import com.softeq.accelerator.flyway.reactive.service.AssessmentService;
import com.softeq.accelerator.flyway.reactive.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * User service implementation
 *
 * @author stitov
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final AssessmentService assessmentService;

    @Override
    public Flux<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<User> findById(UUID id) {
        return assessmentService.findByUserId(id)
            .switchIfEmpty(Mono.just(Assessment.builder().build()))
            .zipWith(repository.findById(id), (assessment, user) -> {
                user.setAssessment(assessment);
                return user;
            });
    }

    @Override
    public Mono<User> save(User user) {
        return repository.save(user);
    }

}
