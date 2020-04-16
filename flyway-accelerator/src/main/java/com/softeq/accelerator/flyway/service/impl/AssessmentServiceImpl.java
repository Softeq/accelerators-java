/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.service.impl;

import com.softeq.accelerator.flyway.dto.AssessmentDto;
import com.softeq.accelerator.flyway.dto.CreateAssessmentDto;
import com.softeq.accelerator.flyway.dto.FeedbackDto;
import com.softeq.accelerator.flyway.entity.Assessment;
import com.softeq.accelerator.flyway.entity.User;
import com.softeq.accelerator.flyway.exception.ResourceNotFoundException;
import com.softeq.accelerator.flyway.mapper.AssessmentMapper;
import com.softeq.accelerator.flyway.repository.AssessmentRepo;
import com.softeq.accelerator.flyway.repository.UserRepo;
import com.softeq.accelerator.flyway.service.AssessmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class AssessmentServiceImpl implements AssessmentService {

    private AssessmentRepo assessmentRepo;
    private UserRepo userRepo;
    private AssessmentMapper assessmentMapper;

    public AssessmentServiceImpl(AssessmentRepo assessmentRepoRef,
                                 UserRepo userRepoRef,
                                 AssessmentMapper assessmentMapperRef) {
        this.assessmentRepo = assessmentRepoRef;
        this.userRepo = userRepoRef;
        this.assessmentMapper = assessmentMapperRef;
    }

    @Override
    public List<AssessmentDto> getAll() {
        log.info("Get all assessments");
        return StreamSupport.stream(assessmentRepo.findAll().spliterator(), false)
            .map(a -> assessmentMapper.toDto(a))
            .collect(Collectors.toList());
    }

    @Override
    public AssessmentDto createAssessment(CreateAssessmentDto request) {
        log.info("Create Assessment started");
        User user = userRepo.findById(request.getUserId())
            .orElseThrow(() -> new ResourceNotFoundException("Cannot find user with id = " + request.getUserId(), null));
        Assessment assessment = assessmentMapper.toEntity(request);
        assessment.setTargetUser(user);
        AssessmentDto created = assessmentMapper.toDto(assessmentRepo.save(assessment));
        log.info("Create Assessment finished");
        return created;
    }

    @Override
    public AssessmentDto getById(Integer id) {
        log.info("Find assessment by id");
        AssessmentDto result = assessmentRepo.findById(id)
            .map(assessment -> assessmentMapper.toDto(assessment))
            .orElseThrow(() -> new ResourceNotFoundException("Assessment not found", null));

        calculateScore(result);
        return result;
    }

    private void calculateScore(AssessmentDto assessmentDto) {
        if (assessmentDto.getFeedbacks() == null || assessmentDto.getFeedbacks().isEmpty()) {
            return;
        }
        BigDecimal sum = assessmentDto.getFeedbacks().stream().map(FeedbackDto::getScore)
            .map(Objects::requireNonNull)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        assessmentDto.setScore(sum.divide(new BigDecimal(assessmentDto.getFeedbacks().size()), RoundingMode.FLOOR));
    }
}
