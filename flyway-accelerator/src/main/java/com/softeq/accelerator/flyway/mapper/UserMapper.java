/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.mapper;

import com.softeq.accelerator.flyway.dto.AssessmentDto;
import com.softeq.accelerator.flyway.dto.CreateUserDto;
import com.softeq.accelerator.flyway.dto.UserDto;
import com.softeq.accelerator.flyway.entity.Assessment;
import com.softeq.accelerator.flyway.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Maps user related objects
 * <p/>
 * Created on 4/6/2020.
 * <p/>
 *
 * @author slapitsky
 */
@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "email", source = "dto.email")
    @Mapping(target = "firstName", source = "dto.firstName")
    @Mapping(target = "lastName", source = "dto.lastName")
    public abstract User toEntity(UserDto dto);

    @Mapping(target = "email", source = "dto.email")
    @Mapping(target = "firstName", source = "dto.firstName")
    @Mapping(target = "lastName", source = "dto.lastName")
    public abstract User toEntity(CreateUserDto dto);

    @Mapping(target = "id", source = "user.id")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "assessments", expression = "java( toAssessmentDtoList(user.getAssessments()) )")
    public abstract UserDto toDto(User user);

    public abstract AssessmentDto toAssessmentDto(Assessment assessment);

    public List<AssessmentDto> toAssessmentDtoList(List<Assessment> assessments) {
        if (assessments == null) {
            return null;
        }
        return assessments.stream().map(this::toAssessmentDto).collect(Collectors.toList());
    }
}
