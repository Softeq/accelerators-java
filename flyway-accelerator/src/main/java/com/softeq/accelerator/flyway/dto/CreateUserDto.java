package com.softeq.accelerator.flyway.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * User creation request representation for API
 * <p/>
 * Created on 4/8/2020.
 * <p/>
 *
 * @author slapitsky
 */
@Getter
@Setter
@ToString
@ApiModel
public class CreateUserDto {

    @NotNull
    @NotEmpty
    @ApiModelProperty(value = "email", example = "new.user@email.com")
    private String email;

    @ApiModelProperty(value = "firstName", example = "John")
    private String firstName;

    @ApiModelProperty(value = "lastName", example = "Smith")
    private String lastName;

}
