package com.softeq.accelerator.flyway.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Assessment representation for API
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
public class AssessmentDto {

    private Integer id;

    private UserDto targetUser;

    private LocalDateTime assesmentDate;

}
