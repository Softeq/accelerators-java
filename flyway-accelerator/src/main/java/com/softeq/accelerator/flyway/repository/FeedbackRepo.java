/*
 *
 *  * Developed by Softeq Development Corporation
 *  * http://www.softeq.com
 *
 */

package com.softeq.accelerator.flyway.repository;

import com.softeq.accelerator.flyway.entity.Feedback;
import org.springframework.data.repository.CrudRepository;

/**
 * Feedback related DB logic
 * <p/>
 * Created on 4/8/2020.
 * <p/>
 *
 * @author slapitsky
 */
public interface FeedbackRepo extends CrudRepository<Feedback, Integer> {

}
