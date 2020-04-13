/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.service;

import com.softeq.accelerator.flyway.dto.AssessmentDto;

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
}
