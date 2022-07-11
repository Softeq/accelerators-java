/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.reactive.service;

import com.softeq.accelerator.flyway.reactive.domain.Score;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Score service
 *
 * @author stitov
 */
public interface ScoreService {

    Flux<Score> findAll();

    Mono<Score> findById(String id);

    Mono<Score> save(Score score);

    Flux<Score> findByUserId(String id);

}
