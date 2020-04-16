/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Assessment representation for API
 * <p/>
 * Created on 4/8/2020.
 * <p/>
 *
 * @author slapitsky
 */
@Data
public class AssessmentDto {

    private Integer id;

    private UserDto targetUser;

    private List<FeedbackDto> feedbacks;

    private LocalDateTime assessmentDate;

    private BigDecimal score;
}
