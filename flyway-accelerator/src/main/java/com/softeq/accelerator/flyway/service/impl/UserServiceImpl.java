/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.service.impl;

import com.softeq.accelerator.flyway.dto.CreateUserDto;
import com.softeq.accelerator.flyway.dto.UserDto;
import com.softeq.accelerator.flyway.entity.User;
import com.softeq.accelerator.flyway.exception.ResourceNotFoundException;
import com.softeq.accelerator.flyway.mapper.UserMapper;
import com.softeq.accelerator.flyway.repository.UserRepo;
import com.softeq.accelerator.flyway.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;
    private UserMapper userMapper;

    public UserServiceImpl(UserRepo userRepoInject, UserMapper userMapperInject) {
        this.userRepo = userRepoInject;
        this.userMapper = userMapperInject;
    }

    @Override
    public List<UserDto> getAll() {
        return StreamSupport.stream(userRepo.findAll().spliterator(), false)
            .map(user -> userMapper.toShortDto(user))
            .collect(Collectors.toList());
    }

    @Override
    public UserDto getById(Integer id) {
        return userRepo.getById(id)
            .map(user -> userMapper.toDto(user))
            .orElseThrow(() -> new ResourceNotFoundException("User not found", null));
    }

    @Override
    public UserDto createUser(CreateUserDto request) {
        log.info("Create user started");
        User user = userMapper.toEntity(request);
        UserDto created = userMapper.toDto(userRepo.save(user));
        log.info("Create user finished");
        return created;
    }
}
