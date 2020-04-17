/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.mapper;

import com.softeq.accelerator.flyway.dto.AssessmentDto;
import com.softeq.accelerator.flyway.dto.CreateAssessmentDto;
import com.softeq.accelerator.flyway.dto.FeedbackDto;
import com.softeq.accelerator.flyway.entity.Assessment;
import com.softeq.accelerator.flyway.entity.Feedback;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public abstract Assessment toEntity(CreateAssessmentDto dto);

    @Mapping(target = "targetUser", expression = "java(null)")
    @Mapping(target = "feedbacks", expression = "java( toFeedbackDtoList(assessment.getFeedbacks()) )")
    public abstract AssessmentDto toDto(Assessment assessment);

    @Mapping(target = "user", expression = "java(null)")
    @Mapping(target = "assessment", expression = "java(null)")
    @Mapping(target = "feedbackDate", source = "feedback.feedbackDate")
    public abstract FeedbackDto toFeedbackDto(Feedback feedback);

    public List<FeedbackDto> toFeedbackDtoList(List<Feedback> feedbacks) {
        if (feedbacks == null) {
            return Collections.emptyList();
        }
        return feedbacks.stream().map(this::toFeedbackDto).collect(Collectors.toList());
    }
}
