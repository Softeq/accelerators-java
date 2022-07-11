/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.reactive.repository.r2dbc;

import com.softeq.accelerator.flyway.reactive.domain.Assessment;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Assessment DB logic
 *
 * @author stitov
 */
@Repository
public interface AssessmentRepository extends ReactiveCrudRepository<Assessment, UUID> {

    Mono<Assessment> findFirstByUserId(UUID id);

}
