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
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static java.util.Objects.isNull;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final EmployeeMapper employeeMapper;
    private final WorkPlaceRepo workPlaceRepo;
    private EntityManager entityManager;

    public EmployeeServiceImpl(EmployeeRepo employeeRepo, EmployeeMapper employeeMapper,
                               WorkPlaceRepo workPlaceRepo, EntityManager entityManager) {
        this.employeeRepo = employeeRepo;
        this.employeeMapper = employeeMapper;
        this.workPlaceRepo = workPlaceRepo;
        this.entityManager = entityManager;
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
        removeWorkPlace(employee.getWorkPlace().getId());
        entityManager.detach(employee);
        //entityManager.clear();
        Employee employeeToReplace = employeeRepo.findById(dto.getEmployeeId()).orElse(null);
        if (isNull(employeeToReplace)) {
            return null;
        }
        WorkPlace newWorkPlace = new WorkPlace();
        newWorkPlace.setNumber(dto.getWorkPlaceNumber());
        newWorkPlace.setEmployee(employee);
        employeeToReplace.setWorkPlace(newWorkPlace);
        return employeeRepo.save(employee);
    }

    private void removeWorkPlace(Integer id) {
        var query = entityManager
                .createNativeQuery("delete from workplace w where w.id = :id", WorkPlace.class);
        query.setParameter("id", id);
        query.executeUpdate();
        entityManager.flush();
    }
}
