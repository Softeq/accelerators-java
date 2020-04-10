/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway;

import com.softeq.accelerator.flyway.dto.UserDto;
import com.softeq.accelerator.flyway.entity.Assessment;
import com.softeq.accelerator.flyway.entity.User;
import com.softeq.accelerator.flyway.mapper.UserMapper;
import com.softeq.accelerator.flyway.mapper.UserMapperImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

/**
 * Check User Mapper convertion logic
 * <p/>
 * Created on 4/10/2020.
 * <p/>
 *
 * @author slapitsky
 */
@RunWith(SpringRunner.class)
public class MapperTest {

    @Test
    public void mapperTest() {
        UserMapper mapper = new UserMapperImpl();

        User u = new User();
        u.setFirstName("f");
        u.setLastName("l");
        u.setEmail("ee@.ee.ee");

        Assessment a = new Assessment();
        LocalDateTime date = LocalDateTime.now();
        a.setAssesmentDate(date);
        u.setAssessments(Collections.singletonList(a));

        UserDto converted = mapper.toDto(u);
        assertEquals(u.getFirstName(), converted.getFirstName());
        assertEquals(u.getLastName(), converted.getLastName());
        assertEquals(u.getEmail(), converted.getEmail());
        assertEquals(u.getAssessments().size(), converted.getAssessments().size());
        assertEquals(u.getAssessments().get(0).getAssesmentDate(), converted.getAssessments().get(0).getAssesmentDate());
    }
}
