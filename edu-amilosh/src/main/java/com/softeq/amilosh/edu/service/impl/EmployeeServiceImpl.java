/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.amilosh.edu.service.impl;

import com.softeq.amilosh.edu.dto.EmployeeCreateDto;
import com.softeq.amilosh.edu.dto.ReplaceEmployeeDto;
import com.softeq.amilosh.edu.entity.Employee;
import com.softeq.amilosh.edu.entity.WorkPlace;
import com.softeq.amilosh.edu.mapper.EmployeeMapper;
import com.softeq.amilosh.edu.repository.EmployeeRepo;
import com.softeq.amilosh.edu.repository.WorkPlaceRepo;
import com.softeq.amilosh.edu.service.EmployeeService;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final EmployeeMapper employeeMapper;
    private final WorkPlaceRepo workPlaceRepo;

    public EmployeeServiceImpl(EmployeeRepo employeeRepo, EmployeeMapper employeeMapper,
                               WorkPlaceRepo workPlaceRepo) {
        this.employeeRepo = employeeRepo;
        this.employeeMapper = employeeMapper;
        this.workPlaceRepo = workPlaceRepo;
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

    @Override
    public Employee replace(ReplaceEmployeeDto dto) {
        Employee employee = employeeRepo.findById(dto.getEmployeeId()).orElse(null);
        if (isNull(employee)) {
            return null;
        }
        WorkPlace oldWorkPlace = workPlaceRepo.findById(employee.getWorkPlace().getId()).orElse(null);
        if (isNull(oldWorkPlace)) {
            return null;
        }
        //oldWorkPlace.setEmployee(null);
        employee.setWorkPlace(null);
        WorkPlace newWorkPlace = new WorkPlace();
        newWorkPlace.setNumber(dto.getWorkPlaceNumber());
        newWorkPlace.setEmployee(employee);
        employee.setWorkPlace(newWorkPlace);
        return employeeRepo.save(employee);
    }
}
