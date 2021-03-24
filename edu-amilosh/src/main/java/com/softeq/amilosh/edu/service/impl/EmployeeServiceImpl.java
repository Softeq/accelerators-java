/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.amilosh.edu.service.impl;

import com.softeq.amilosh.edu.dto.EmployeeCreateDto;
import com.softeq.amilosh.edu.entity.Employee;
import com.softeq.amilosh.edu.repository.EmployeeRepo;
import com.softeq.amilosh.edu.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepo employeeRepo;

    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public Employee create(EmployeeCreateDto dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        return employeeRepo.save(employee);
    }
}
