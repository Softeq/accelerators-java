/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.repository;

import com.softeq.accelerator.flyway.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * TUser related DB logic
 * <p/>
 * Created on 4/6/2020.
 * <p/>
 *
 * @author slapitsky
 */
public interface UserRepo extends CrudRepository<User, Integer> {

    @Query("select u "
        + " from User u  "
        + " where  "
        + "     (:firstName is null or u.firstName like %:firstName% )  "
        + " and (:lastName is null or u.lastName like %:lastName% )"
        + " and (:email is null or u.email like %:email% )")
    Page<User> search(@Param("firstName") String firstName,
                      @Param("lastName") String lastName,
                      @Param("email") String email,
                      Pageable pageable);

    @EntityGraph(attributePaths = {"assessments"})
    Optional<User> getById(Integer id);
}
