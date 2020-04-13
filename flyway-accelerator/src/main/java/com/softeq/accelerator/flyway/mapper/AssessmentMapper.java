/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.mapper;

import com.softeq.accelerator.flyway.dto.AssessmentDto;
import com.softeq.accelerator.flyway.entity.Assessment;
import org.mapstruct.Mapper;

/**
 * Maps assessment related objects
 * <p/>
 * Created on 4/13/2020.
 * <p/>
 *
 * @author slapitsky
 */
@Mapper(componentModel = "spring")
public abstract class AssessmentMapper {

    public abstract Assessment toEntity(AssessmentDto dto);

    public abstract AssessmentDto toDto(Assessment assessment);
}
