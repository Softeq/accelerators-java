package com.softeq.accelerator.flyway.service.impl;

import com.softeq.accelerator.flyway.dto.UserDto;
import com.softeq.accelerator.flyway.mapper.UserMapper;
import com.softeq.accelerator.flyway.repository.UserRepo;
import com.softeq.accelerator.flyway.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;
    private UserMapper userMapper;

    public UserServiceImpl(UserRepo userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDto> getAll() {
        return StreamSupport.stream(userRepo.findAll().spliterator(), false)
                .map(user -> userMapper.toDto(user))
                .collect(Collectors.toList());
    }
}
