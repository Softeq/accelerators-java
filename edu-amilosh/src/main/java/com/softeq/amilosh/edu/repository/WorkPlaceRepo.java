/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.amilosh.edu.repository;

import com.softeq.amilosh.edu.entity.WorkPlace;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * WorkPlace repository.
 * <p/>
 * Created on 2021-03-24
 * <p/>
 *
 * @author Alexander Milosh
 */
public interface WorkPlaceRepo extends JpaRepository<WorkPlace, Integer> {
}
