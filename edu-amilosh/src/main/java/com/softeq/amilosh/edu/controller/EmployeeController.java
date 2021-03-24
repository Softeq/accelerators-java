/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.amilosh.edu.controller;

import com.softeq.amilosh.edu.dto.EmployeeCreateDto;
import com.softeq.amilosh.edu.dto.EmployeeDto;
import com.softeq.amilosh.edu.dto.ReplaceEmployeeDto;
import com.softeq.amilosh.edu.entity.Employee;
import com.softeq.amilosh.edu.mapper.EmployeeMapper;
import com.softeq.amilosh.edu.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles Employee related business logic.
 * <p/>
 * Created on 2021-03-24
 * <p/>
 *
 * @author Alexander Milosh
 */
@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto createEmployee(@RequestBody EmployeeCreateDto dto) {
        Employee employee = employeeService.create(dto);
        return employeeMapper.toDto(employee);
    }

    @PostMapping(value = "/replace", consumes = "application/json", produces = "application/json")
    public EmployeeDto replaceEmployee(@RequestBody ReplaceEmployeeDto dto) {
        Employee employee = employeeService.replace(dto);
        return employeeMapper.toDto(employee);
    }
}
