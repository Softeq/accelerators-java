/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.amilosh.edu.controller;

import com.softeq.amilosh.edu.dto.WorkPlaceDto;
import com.softeq.amilosh.edu.entity.WorkPlace;
import com.softeq.amilosh.edu.service.WorkPlaceService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles WorkPlace related business logic.
 * <p/>
 * Created on 2021-03-24
 * <p/>
 *
 * @author Alexander Milosh
 */
@RestController
@RequestMapping(value = "/workplace")
public class WorkPlaceController {

    private final WorkPlaceService workPlaceService;

    public WorkPlaceController(WorkPlaceService workPlaceService) {
        this.workPlaceService = workPlaceService;
    }

    @PutMapping(value = "/computer/{id}/{inventory}")
    public WorkPlaceDto setComputer(@PathVariable("id") Integer workPlaceId, @PathVariable("inventory") Integer inventoryNumber) {
        WorkPlace workPlace = workPlaceService.setComputer(workPlaceId, inventoryNumber);
        WorkPlaceDto dto = new WorkPlaceDto();
        dto.setId(workPlace.getId());
        dto.setNumber(workPlace.getNumber());
        return dto;
    }
}
