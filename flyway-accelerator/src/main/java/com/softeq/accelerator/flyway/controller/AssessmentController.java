/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.controller;

import com.softeq.accelerator.flyway.dto.AssessmentDto;
import com.softeq.accelerator.flyway.dto.CreateAssessmentDto;
import com.softeq.accelerator.flyway.service.AssessmentService;
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
 * Assessments related logic.
 * <p/>
 * Created on 4/13/2020.
 * <p/>
 *
 * @author slapitsky
 */
@Slf4j
@Api(value = "AssessmentController")
@RestController
@RequestMapping(value = "/assessments")
public class AssessmentController {

    private AssessmentService assessmentService;

    public AssessmentController(AssessmentService assessmentServiceRef) {
        this.assessmentService = assessmentServiceRef;
    }

    @GetMapping
    @ApiOperation(value = "View a list of all assessments.", response = AssessmentDto.class, responseContainer = "List")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved assessments.")
    })
    public ResponseEntity<List<AssessmentDto>> getAll() {
        return ResponseEntity.ok().body(assessmentService.getAll());
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Create an assessment.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully created assessment.")
    })
    public ResponseEntity<AssessmentDto> createAssessment(@RequestBody @Valid CreateAssessmentDto request) {
        return ResponseEntity.ok().body(assessmentService.createAssessment(request));
    }
}
