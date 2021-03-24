/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.amilosh.edu.dto;

/**
 * DTO to change work place for entity.
 * <p/>
 * Created on 2021-03-24
 * <p/>
 *
 * @author Alexander Milosh
 */
public class ReplaceEmployeeDto {

    private Integer employeeId;
    private Integer workPlaceNumber;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getWorkPlaceNumber() {
        return workPlaceNumber;
    }

    public void setWorkPlaceNumber(Integer workPlaceNumber) {
        this.workPlaceNumber = workPlaceNumber;
    }
}
