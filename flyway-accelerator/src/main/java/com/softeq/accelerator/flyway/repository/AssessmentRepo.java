/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.repository;

import com.softeq.accelerator.flyway.entity.Assessment;
import org.springframework.data.repository.CrudRepository;

/**
 * Assessment related DB logic
 * <p/>
 * Created on 4/8/2020.
 * <p/>
 *
 * @author slapitsky
 */
public interface AssessmentRepo extends CrudRepository<Assessment, Integer> {

}
