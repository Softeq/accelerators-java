/*
 *
 *  * Developed by Softeq Development Corporation
 *  * http://www.softeq.com
 *
 */

package com.softeq.accelerator.flyway.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents feedback from DB
 * <p/>
 * Created on 4/8/2020.
 * <p/>
 *
 * @author slapitsky
 */
@Entity
@Table(name = "feedback")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assessment_id")
    private Assessment assessment;

    @Column(name = "feedback_date")
    private LocalDateTime assesmentDate;

    @Column(name = "score")
    private BigDecimal score;

    @Column(name = "comment")
    private String comment;

}
