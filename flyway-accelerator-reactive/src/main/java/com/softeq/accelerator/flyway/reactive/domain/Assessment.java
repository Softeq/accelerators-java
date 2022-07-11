/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.reactive.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Assessment entity
 *
 * @author stitov
 */
@Table
@Getter
@Setter
@Builder
public class Assessment {

    @Id
    private UUID id;

    private UUID userId;

    private float average;

    private int feedbacksCount;

    private LocalDateTime updateDate;

}
