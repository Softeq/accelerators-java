/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.mapper;

import com.softeq.accelerator.flyway.dto.AssessmentDto;
import com.softeq.accelerator.flyway.dto.CreateAssessmentDto;
import com.softeq.accelerator.flyway.entity.Assessment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Maps assessment related objects
 * <p/>
 * Created on 4/13/2020.
 * <p/>
 *
 * @author slapitsky
 */
@Mapper(componentModel = "spring")
public interface AssessmentMapper {

    Assessment toEntity(AssessmentDto dto);

    Assessment toEntity(CreateAssessmentDto dto);

    @Mapping(target = "targetUser", ignore = true)
    AssessmentDto toDto(Assessment assessment);
}
