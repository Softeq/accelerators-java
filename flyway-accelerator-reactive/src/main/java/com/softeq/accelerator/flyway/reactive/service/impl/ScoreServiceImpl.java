/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.reactive.service.impl;

import com.softeq.accelerator.flyway.reactive.domain.Score;
import com.softeq.accelerator.flyway.reactive.repository.nosql.ScoreRepository;
import com.softeq.accelerator.flyway.reactive.service.ScoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Score service implementation
 *
 * @author stitov
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService {

    private final ScoreRepository repository;

    @Override
    public Flux<Score> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Score> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Score> save(Score score) {
        return repository.save(score);
    }

    @Override
    public Flux<Score> findByUserId(String id) {
        return repository.findAllByUserId(id);
    }
}
