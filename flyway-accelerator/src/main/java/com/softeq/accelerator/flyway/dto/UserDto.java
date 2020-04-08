package com.softeq.accelerator.flyway.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * User representation for API
 * <p/>
 * Created on 4/6/2020.
 * <p/>
 *
 * @author slapitsky
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserDto {
    private Integer id;
    @NotNull
    @NotEmpty
    private String email;
    private String firstName;
    private String lastName;

    private List<AssessmentDto> assessments;
}
