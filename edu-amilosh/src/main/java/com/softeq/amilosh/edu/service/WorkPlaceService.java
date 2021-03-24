/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.amilosh.edu.service;

import com.softeq.amilosh.edu.entity.WorkPlace;

/**
 * WorkPlace related business logic.
 * <p/>
 * Created on 2021-03-24
 * <p/>
 *
 * @author Alexander Milosh
 */
public interface WorkPlaceService {

    WorkPlace setComputer(Integer workPlaceId, Integer inventoryNumber);
}
