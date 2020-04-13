/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.service.impl;

import com.softeq.accelerator.flyway.dto.AssessmentDto;
import com.softeq.accelerator.flyway.mapper.AssessmentMapper;
import com.softeq.accelerator.flyway.repository.AssessmentRepo;
import com.softeq.accelerator.flyway.service.AssessmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class AssessmentServiceImpl implements AssessmentService {

    private AssessmentRepo assessmentRepo;
    private AssessmentMapper assessmentMapper;

    public AssessmentServiceImpl(AssessmentRepo assessmentRepoRef, AssessmentMapper assessmentMapperRef) {
        this.assessmentRepo = assessmentRepoRef;
        this.assessmentMapper = assessmentMapperRef;
    }

    @Override
    public List<AssessmentDto> getAll() {
        log.info("Get all assessments");
        return StreamSupport.stream(assessmentRepo.findAll().spliterator(), false)
            .map(a -> assessmentMapper.toDto(a))
            .collect(Collectors.toList());
    }
}
