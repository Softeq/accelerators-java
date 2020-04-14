/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.service;

import com.softeq.accelerator.flyway.dto.CreateUserDto;
import com.softeq.accelerator.flyway.dto.SearchUserRequestDto;
import com.softeq.accelerator.flyway.dto.UserDto;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Users related business logic
 * <p/>
 * Created on 4/6/2020.
 * <p/>
 *
 * @author slapitsky
 */
public interface UserService {

    /**
     * Get all users
     *
     * @return users list
     */
    List<UserDto> getAll();

    /**
     * Get user by id
     *
     * @param id user id
     * @return user if found or throws ResourceNotFoundException
     */
    UserDto getById(Integer id);

    /**
     * Creates a user from request DTO
     *
     * @param request DTO with the new user fields
     * @return created user DTO
     */
    UserDto createUser(CreateUserDto request);

    /**
     * Searches users
     *
     * @param request searh request fields
     * @return
     */
    Page<UserDto> search(SearchUserRequestDto request);
}
