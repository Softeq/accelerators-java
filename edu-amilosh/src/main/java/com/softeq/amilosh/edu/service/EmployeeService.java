/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.amilosh.edu.service;

import com.softeq.amilosh.edu.dto.EmployeeCreateDto;
import com.softeq.amilosh.edu.dto.ReplaceEmployeeDto;
import com.softeq.amilosh.edu.entity.Employee;

/**
 * Employee related business logic.
 * <p/>
 * Created on 2021-03-24
 * <p/>
 *
 * @author Alexander Milosh
 */
public interface EmployeeService {

    Employee create(EmployeeCreateDto dto);

    Employee replace(ReplaceEmployeeDto dto);
}
