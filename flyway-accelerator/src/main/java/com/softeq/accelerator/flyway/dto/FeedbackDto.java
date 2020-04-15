/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Feedback representation for API
 * <p/>
 * Created on 4/8/2020.
 * <p/>
 *
 * @author slapitsky
 */
@Data
public class FeedbackDto {

    private Integer id;

    private UserDto user;

    private AssessmentDto assessment;

    private LocalDateTime feedbackDate;

    private BigDecimal score;

    private String comment;

}
