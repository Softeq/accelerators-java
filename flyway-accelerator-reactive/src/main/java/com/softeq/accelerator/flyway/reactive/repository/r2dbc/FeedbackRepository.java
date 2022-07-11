/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.reactive.repository.r2dbc;

import com.softeq.accelerator.flyway.reactive.domain.Feedback;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

/**
 * Feedback DB logic
 *
 * @author stitov
 */
@Repository
public interface FeedbackRepository extends ReactiveCrudRepository<Feedback, UUID> {

    Flux<Feedback> findAllByUserId(UUID id);

    Flux<Feedback> findAllByTargetUserId(UUID id);

}
