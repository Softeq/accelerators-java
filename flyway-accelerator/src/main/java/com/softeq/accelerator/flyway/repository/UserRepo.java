package com.softeq.accelerator.flyway.repository;

import com.softeq.accelerator.flyway.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * TUser related DB logic
 * <p/>
 * Created on 4/6/2020.
 * <p/>
 *
 * @author slapitsky
 */
public interface UserRepo extends CrudRepository<User, Integer> {
}
