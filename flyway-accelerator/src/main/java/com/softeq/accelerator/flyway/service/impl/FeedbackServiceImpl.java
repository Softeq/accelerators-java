/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.service.impl;

import com.softeq.accelerator.flyway.dto.FeedbackDto;
import com.softeq.accelerator.flyway.mapper.FeedbackMapper;
import com.softeq.accelerator.flyway.repository.FeedbackRepo;
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
    private FeedbackMapper feedbackMapper;

    public FeedbackServiceImpl(FeedbackRepo feedbackRepoInject, FeedbackMapper feedbackMapperInject) {
        this.feedbackRepo = feedbackRepoInject;
        this.feedbackMapper = feedbackMapperInject;
    }

    @Override
    public List<FeedbackDto> getAll() {
        log.info("Get all feedbacks");
        return StreamSupport.stream(feedbackRepo.findAll().spliterator(), false)
            .map(f -> feedbackMapper.toDto(f))
            .collect(Collectors.toList());
    }
}
