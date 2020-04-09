/*
 *
 *  * Developed by Softeq Development Corporation
 *  * http://www.softeq.com
 *
 */

package com.softeq.accelerator.flyway.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class FeedbackDto {

    private Integer id;

    private UserDto user;

    private AssessmentDto assessment;

    private LocalDateTime assesmentDate;

    private BigDecimal score;

    private String comment;

}
