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

import java.util.UUID;

/**
 * User entity
 *
 * @author stitov
 */
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    private UUID id;

    private String email;

    private String firstName;

    private String lastName;

    @Transient
    private Assessment assessment;

}
