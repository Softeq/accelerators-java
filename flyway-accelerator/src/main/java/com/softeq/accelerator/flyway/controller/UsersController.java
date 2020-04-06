package com.softeq.accelerator.flyway.controller;

import com.softeq.accelerator.flyway.dto.UserDto;
import com.softeq.accelerator.flyway.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Users related logic.
 * <p/>
 * Created on 4/6/2020.
 * <p/>
 *
 * @author slapitsky
 */
@Slf4j
@RestController
@RequestMapping(value = "/users")
public class UsersController {

    private UserService userService;

    public UsersController(UserService userServiceInject) {
        this.userService = userServiceInject;
    }

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{userId}")
    public String getById(@PathVariable("userId") String userId) {
        return "0.01";
    }
}
