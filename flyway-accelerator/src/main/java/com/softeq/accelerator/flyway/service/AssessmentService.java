/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.service;

import com.softeq.accelerator.flyway.dto.AssessmentDto;
import com.softeq.accelerator.flyway.dto.CreateAssessmentDto;

import java.util.List;

/**
 * Assessment related business logic.
 * <p/>
 * Created on 4/13/2020.
 * <p/>
 *
 * @author slapitsky
 */
public interface AssessmentService {

    /**
     * Get all assessmenrs
     *
     * @return assessments list
     */
    List<AssessmentDto> getAll();

    /**
     * CReates a new assessment
     *
     * @param request
     * @return
     */
    AssessmentDto createAssessment(CreateAssessmentDto request);

    /**
     * Get assessment by id
     *
     * @param id assessment id
     * @return assessment if found or throws ResourceNotFoundException
     */
    AssessmentDto getById(Integer id);
}
