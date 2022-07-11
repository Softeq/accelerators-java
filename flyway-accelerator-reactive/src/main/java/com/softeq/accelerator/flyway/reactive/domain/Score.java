/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.reactive.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Score entity
 *
 * @author stitov
 */
@Data
@Builder
@Document
public class Score {

    @Id
    private String id;

    private String userId;

    private String feedbackId;

    private int score;

}
