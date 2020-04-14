/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.mapper;

import com.softeq.accelerator.flyway.dto.FeedbackDto;
import com.softeq.accelerator.flyway.entity.Feedback;
import org.mapstruct.Mapper;

/**
 * Maps Feedback related objects.
 * <p/>
 * Created on 4/14/2020.
 * <p/>
 *
 * @author slapitsky
 */
@Mapper(componentModel = "spring")
public abstract class FeedbackMapper {

    public abstract FeedbackDto toDto(Feedback feedback);

    public abstract Feedback toEntity(FeedbackDto dto);
}
