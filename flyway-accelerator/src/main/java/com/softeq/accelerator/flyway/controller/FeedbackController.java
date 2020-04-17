/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.controller;

import com.softeq.accelerator.flyway.dto.CreateFeedbackDto;
import com.softeq.accelerator.flyway.dto.FeedbackDto;
import com.softeq.accelerator.flyway.service.FeedbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Feedback related logic.
 * <p/>
 * Created on 4/14/2020.
 * <p/>
 *
 * @author slapitsky
 */
@Slf4j
@Api(value = "FeedbackController")
@RestController
@RequestMapping(value = "/feedbacks")
public class FeedbackController {

    private FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackServiceInject) {
        this.feedbackService = feedbackServiceInject;
    }

    @GetMapping
    @ApiOperation(value = "View a list of all feedbacks.", response = FeedbackDto.class, responseContainer = "List")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved feedbacks.")
    })
    public ResponseEntity<List<FeedbackDto>> getAll() {
        return ResponseEntity.ok().body(feedbackService.getAll());
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Create a feedback.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully created feedback.")
    })
    public ResponseEntity<FeedbackDto> createFeedback(@RequestBody @Valid CreateFeedbackDto request) {
        return ResponseEntity.ok().body(feedbackService.createFeedback(request));
    }
}
