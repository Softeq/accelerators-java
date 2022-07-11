/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.reactive.repository.nosql;

import com.softeq.accelerator.flyway.reactive.domain.Score;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * Score DB logic
 *
 * @author stitov
 */
@Repository
public interface ScoreRepository extends ReactiveMongoRepository<Score, String> {

    Flux<Score> findAllByUserId(String id);

}
