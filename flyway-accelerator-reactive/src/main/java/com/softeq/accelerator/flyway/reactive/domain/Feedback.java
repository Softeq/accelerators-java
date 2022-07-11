/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.reactive.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Feedback entity
 *
 * @author stitov
 */
@Table
@Getter
@Setter
public class Feedback {

    @Id
    private UUID id;

    private UUID userId;

    @Transient
    private User user;

    private UUID targetUserId;

    private LocalDateTime feedbackDate;

    private int score;

    private String comment;

}
