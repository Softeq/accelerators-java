/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.service.impl;

import com.softeq.accelerator.flyway.dto.CreateFeedbackDto;
import com.softeq.accelerator.flyway.dto.FeedbackDto;
import com.softeq.accelerator.flyway.entity.Assessment;
import com.softeq.accelerator.flyway.entity.Feedback;
import com.softeq.accelerator.flyway.entity.User;
import com.softeq.accelerator.flyway.exception.ResourceNotFoundException;
import com.softeq.accelerator.flyway.mapper.FeedbackMapper;
import com.softeq.accelerator.flyway.repository.AssessmentRepo;
import com.softeq.accelerator.flyway.repository.FeedbackRepo;
import com.softeq.accelerator.flyway.repository.UserRepo;
import com.softeq.accelerator.flyway.service.FeedbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class FeedbackServiceImpl implements FeedbackService {

    private FeedbackRepo feedbackRepo;
    private UserRepo userRepo;
    private AssessmentRepo assessmentRepo;
    private FeedbackMapper feedbackMapper;

    public FeedbackServiceImpl(FeedbackRepo feedbackRepoInject, AssessmentRepo assessmentRepoInject,
                               UserRepo userRepoInject,
                               FeedbackMapper feedbackMapperInject) {
        this.feedbackRepo = feedbackRepoInject;
        this.assessmentRepo = assessmentRepoInject;
        this.userRepo = userRepoInject;
        this.feedbackMapper = feedbackMapperInject;
    }

    @Override
    public List<FeedbackDto> getAll() {
        log.info("Get all feedbacks");
        return StreamSupport.stream(feedbackRepo.findAll().spliterator(), false)
            .map(f -> feedbackMapper.toDto(f))
            .collect(Collectors.toList());
    }

    @Override
    public FeedbackDto createFeedback(CreateFeedbackDto request) {
        log.info("Create Feedback started");
        User user = userRepo.findById(request.getUserId())
            .orElseThrow(() -> new ResourceNotFoundException("Cannot find user with id = " + request.getUserId(), null));
        Assessment assessment = assessmentRepo.findById(request.getAssessmentId())
            .orElseThrow(() -> new ResourceNotFoundException("Cannot find assessment with id = " + request.getAssessmentId(), null));
        Feedback feedback = feedbackMapper.toEntity(request);
        feedback.setUser(user);
        feedback.setAssessment(assessment);
        FeedbackDto created = feedbackMapper.toDto(feedbackRepo.save(feedback));
        log.info("Create Feedback finished");
        return created;
    }
}
