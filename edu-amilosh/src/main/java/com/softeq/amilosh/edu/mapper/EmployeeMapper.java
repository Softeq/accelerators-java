/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.amilosh.edu.mapper;

import com.softeq.amilosh.edu.dto.EmployeeCreateDto;
import com.softeq.amilosh.edu.dto.EmployeeDto;
import com.softeq.amilosh.edu.dto.WorkPlaceDto;
import com.softeq.amilosh.edu.entity.Employee;
import com.softeq.amilosh.edu.entity.WorkPlace;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

/**
 * Maps Employee related objects.
 * <p/>
 * Created on 2021-03-24
 * <p/>
 *
 * @author Alexander Milosh
 */
@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {

    Employee toEntity(EmployeeCreateDto dto);

    @Mapping(target = "workPlace", expression = "java( toDto(employee.getWorkPlace()) )")
    EmployeeDto toDto(Employee employee);

    WorkPlaceDto toDto(WorkPlace workPlace);
}
