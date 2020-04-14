/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Assessment creation request representation for API.
 * <p/>
 * Created on 4/13/2020.
 * <p/>
 *
 * @author slapitsky
 */
@Data
@ApiModel
public class CreateAssessmentDto {
    private LocalDateTime assessmentDate;
    private Integer userId;
}
