/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.service;

import com.softeq.accelerator.flyway.dto.FeedbackDto;

import java.util.List;

/**
 * Feedback related business logic
 * <p/>
 * Created on 4/14/2020.
 * <p/>
 *
 * @author slapitsky
 */
public interface FeedbackService {

    /**
     * Get all feedbacks
     *
     * @return feedback list
     */
    List<FeedbackDto> getAll();
}
