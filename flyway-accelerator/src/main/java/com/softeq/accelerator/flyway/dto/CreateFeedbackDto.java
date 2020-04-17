/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Feedback creation request representation for API.
 * <p/>
 * Created on 4/14/2020.
 * <p/>
 *
 * @author slapitsky
 */
@Data
@ApiModel
public class CreateFeedbackDto {
    private Integer assessmentId;
    private Integer userId;
    private LocalDateTime feedbackDate;
    private BigDecimal score;
    private String comment;
}
