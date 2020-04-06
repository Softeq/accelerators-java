package com.softeq.accelerator.flyway.service;

import com.softeq.accelerator.flyway.dto.UserDto;

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

    List<UserDto> getAll();
}
