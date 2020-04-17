/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.mapper;

import com.softeq.accelerator.flyway.dto.CreateFeedbackDto;
import com.softeq.accelerator.flyway.dto.FeedbackDto;
import com.softeq.accelerator.flyway.entity.Feedback;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Maps Feedback related objects.
 * <p/>
 * Created on 4/14/2020.
 * <p/>
 *
 * @author slapitsky
 */
@Mapper(componentModel = "spring")
public interface FeedbackMapper {

    @Mapping(target = "user", expression = "java(null)")
    @Mapping(target = "assessment", expression = "java(null)")
    @Mapping(target = "feedbackDate", source = "feedback.feedbackDate")
    @Mapping(target = "id", source = "feedback.id")
    FeedbackDto toDto(Feedback feedback);

    @Mapping(target = "feedbackDate", source = "dto.feedbackDate")
    Feedback toEntity(FeedbackDto dto);

    Feedback toEntity(CreateFeedbackDto dto);

}
