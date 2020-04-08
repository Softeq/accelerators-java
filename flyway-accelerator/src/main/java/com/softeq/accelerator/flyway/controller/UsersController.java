package com.softeq.accelerator.flyway.controller;

import com.softeq.accelerator.flyway.dto.CreateUserDto;
import com.softeq.accelerator.flyway.dto.UserDto;
import com.softeq.accelerator.flyway.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
@Api(value = "UsersController")
@RestController
@RequestMapping(value = "/users")
public class UsersController {

    private UserService userService;

    public UsersController(UserService userServiceInject) {
        this.userService = userServiceInject;
    }

    @GetMapping
    @ApiOperation(value = "View a list of all users.", response = UserDto.class, responseContainer = "List")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved users.")
    })
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok().body(userService.getAll());
    }

    @GetMapping("/{userId}")
    @ApiOperation(value = "Get user by id.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved user."),
        @ApiResponse(code = 400, message = "User not found.")
    })
    public ResponseEntity<UserDto> getById(@PathVariable("userId") @ApiParam(value = "userId", example = "42") Integer userId) {
        return ResponseEntity.ok().body(userService.getById(userId));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Create a user.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully created user.")
    })
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid CreateUserDto request) {
        return ResponseEntity.ok().body(userService.createUser(request));
    }
}
