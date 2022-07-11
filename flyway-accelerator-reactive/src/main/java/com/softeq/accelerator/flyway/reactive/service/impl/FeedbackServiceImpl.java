/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.reactive.service.impl;

import com.softeq.accelerator.flyway.reactive.domain.Assessment;
import com.softeq.accelerator.flyway.reactive.domain.Feedback;
import com.softeq.accelerator.flyway.reactive.domain.Score;
import com.softeq.accelerator.flyway.reactive.repository.r2dbc.FeedbackRepository;
import com.softeq.accelerator.flyway.reactive.service.AssessmentService;
import com.softeq.accelerator.flyway.reactive.service.FeedbackService;
import com.softeq.accelerator.flyway.reactive.service.ScoreService;
import com.softeq.accelerator.flyway.reactive.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Feedback service implementation
 *
 * @author stitov
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository repository;
    private final UserService userService;
    private final AssessmentService assessmentService;
    private final ScoreService scoreService;

    @Override
    public Flux<Feedback> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Feedback> findById(UUID id) {
        return userService.findById(id)
            .zipWith(repository.findById(id), (user, feedback) -> {
                feedback.setUser(user);
                return feedback;
            });
    }

    @Transactional
    @Override
    public Mono<Feedback> save(Feedback feedback) {
        int score = feedback.getScore();
        UUID targetUserId = feedback.getTargetUserId();
        return assessmentService.findByUserId(targetUserId)
            .switchIfEmpty(Mono.just(Assessment.builder().build()))
            .flatMap(assessment -> {
                assessment.setUserId(targetUserId);
                assessment.setUpdateDate(feedback.getFeedbackDate());
                if (assessment.getId() == null) {
                    assessment.setAverage(score);
                    assessment.setFeedbacksCount(1);
                } else {
                    int count = assessment.getFeedbacksCount();
                    float average = assessment.getAverage();
                    assessment.setAverage((count * average + score) / (count + 1));
                    assessment.setFeedbacksCount(count + 1);
                }
                return assessmentService.save(assessment);
            })
            .then(scoreService.save(Score
                .builder()
                .userId(feedback.getTargetUserId().toString())
                .score(score)
                .build()))
            .then(repository.save(feedback));
    }

    @Override
    public Flux<Feedback> findByUserId(UUID id) {
        return repository.findAllByUserId(id);
    }

    @Override
    public Flux<Feedback> findByTargetUserId(UUID id) {
        return repository.findAllByTargetUserId(id);
    }


}
