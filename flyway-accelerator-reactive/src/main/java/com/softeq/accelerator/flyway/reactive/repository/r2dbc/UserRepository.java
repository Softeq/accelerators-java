/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.reactive.repository.r2dbc;

import com.softeq.accelerator.flyway.reactive.domain.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * User DB logic
 *
 * @author stitov
 */
@Repository
public interface UserRepository extends ReactiveCrudRepository<User, UUID> {

}
