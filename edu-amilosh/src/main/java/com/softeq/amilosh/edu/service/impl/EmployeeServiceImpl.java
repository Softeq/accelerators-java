/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.amilosh.edu.service.impl;

import com.softeq.amilosh.edu.dto.EmployeeCreateDto;
import com.softeq.amilosh.edu.entity.Employee;
import com.softeq.amilosh.edu.entity.WorkPlace;
import com.softeq.amilosh.edu.mapper.EmployeeMapper;
import com.softeq.amilosh.edu.repository.EmployeeRepo;
import com.softeq.amilosh.edu.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepo employeeRepo, EmployeeMapper employeeMapper) {
        this.employeeRepo = employeeRepo;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public Employee create(EmployeeCreateDto dto) {
        Employee employee = employeeMapper.toEntity(dto);
        WorkPlace workPlace = new WorkPlace();
        workPlace.setNumber(dto.getPlaceNumber());
        workPlace.setEmployee(employee);
        employee.setWorkPlace(workPlace);
        return employeeRepo.save(employee);
    }
}
