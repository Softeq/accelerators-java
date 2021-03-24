/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.amilosh.edu.service.impl;

import com.softeq.amilosh.edu.entity.Computer;
import com.softeq.amilosh.edu.entity.WorkPlace;
import com.softeq.amilosh.edu.repository.WorkPlaceRepo;
import com.softeq.amilosh.edu.service.WorkPlaceService;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class WorkPlaceServiceImpl implements WorkPlaceService {

    private final WorkPlaceRepo workPlaceRepo;

    public WorkPlaceServiceImpl(WorkPlaceRepo workPlaceRepo) {
        this.workPlaceRepo = workPlaceRepo;
    }

    @Override
    public WorkPlace setComputer(Integer workPlaceId, Integer inventoryNumber) {
        WorkPlace workPlace = workPlaceRepo.findById(workPlaceId).orElse(null);
        if (isNull(workPlace)) {
            return null;
        }
        Computer computer = new Computer();
        computer.setInventoryNumber(inventoryNumber);
        computer.setWorkPlace(workPlace);
        workPlace.setComputer(computer);
        return workPlaceRepo.save(workPlace);
    }
}
