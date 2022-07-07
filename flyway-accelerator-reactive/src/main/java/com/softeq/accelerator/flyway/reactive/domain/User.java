/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.reactive.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

/**
 * Represents user from DB
 *
 * @author stitov
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NamedEntityGraph(
    name = "user-with-assessments",
    attributeNodes = {
        @NamedAttributeNode(value = "assessments", subgraph = "assessments-subgraph"),
    },
    subgraphs = {
        @NamedSubgraph(
            name = "assessments-subgraph",
            attributeNodes = {
                @NamedAttributeNode("feedbacks")
            }
        )
    }
)
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    private String email;

    private String firstName;

    private String lastName;

    @OneToMany(mappedBy = "targetUser", cascade = CascadeType.ALL)
    private List<Assessment> assessments;

}
