/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.reactive.service;

import com.softeq.accelerator.flyway.reactive.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * User service
 *
 * @author stitov
 */
public interface UserService {

    Flux<User> findAll();

    Mono<User> findById(UUID id);

    Mono<User> save(User user);

}
